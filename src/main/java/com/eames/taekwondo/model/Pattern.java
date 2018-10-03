package com.eames.taekwondo.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

/**
 * This class is the base class for all json.
 */
public class Pattern extends SkillEntity {

    /**
     * The JSON file folder and extension.
     */
    private static final String JSON_EXTENSION = ".json";

    /**
     * The JSON file keys.
     */
    private static final String JSON_HISTORY_KEY = "history";

    // Initialize the Log4j logger.
    private static final Logger logger = LogManager.getLogger(Pattern.class);

    /**
     * The TKD belt level where this pattern is learned
     */
    private final Belt beltLevel;

    /**
     * The pattern diagram.
     */
    private final PatternDiagram patternDiagram;

    /**
     * The number of movements in the pattern
     */
    private final int movementCount;

    /**
     * The pattern starting position
     */
    private Stance startingPosition;

    /**
     * The pattern history paragraph
     * Lazily loaded
     */
    private String history;

    /**
     * The pattern movements
     * Lazily loaded
     */
    private Movement[] movements;

    /**
     * Constructor
     *
     * @param key the pattern key name
     * @param displayName the display name
     * @param phoneticName the phonetic name
     * @param beltLevel the TKD belt level that teaches this pattern
     * @param patternDiagram the pattern diagram
     * @param movementCount the number of movements in the pattern
     */
    protected Pattern(String key, String displayName, String phoneticName, Belt beltLevel,
                      PatternDiagram patternDiagram, int movementCount, Stance startingPosition) {

        // Call the base class constructor.
        super(key, displayName, phoneticName);

        // Set the attributes.
        this.beltLevel = beltLevel;
        this.patternDiagram = patternDiagram;
        this.startingPosition = startingPosition;
        this.movementCount = movementCount;
    }

    /**
     * Gets the pattern diagram.
     *
     * @return the pattern diagram
     */
    public PatternDiagram getDiagram() {
        return patternDiagram;
    }

    /**
     * Gets the pattern belt level.
     *
     * @return the pattern belt level
     */
    public Belt getBeltLevel() {
        return beltLevel;
    }

    /**
     * Gets the pattern's movement count.
     *
     * @return the movement count
     */
    public int getMovementCount()
    {
        return movementCount;
    }

    /**
     * Gets the starting position.
     *
     * @return the pattern starting position
     */
    public Stance getStartingPosition() {
        return startingPosition;
    }

    /**
     * Gets the pattern history.
     * Loads the history paragraph from the pattern's JSON file the first time it is requested.
     * The JSON file is named using the pattern's key.
     *
     * @return the pattern history
     */
    public String getHistory() {

        // The history paragraph has already been loaded, so return it.
        if (history != null) {

            logger.debug(new StringBuilder()
                    .append("The history for ")
                    .append(getDisplayName())
                    .append(" has already been loaded.")
                    .toString());

            return history;
        }

        // Read and return the pattern history.
        return loadJSONElement(JSON_HISTORY_KEY);
    }

    /**
     * Loads the given element from the pattern's JSON file.
     * The JSON file is named using the pattern's key.
     *
     * @param key the key of the JSON element to load
     * @return the element's value
     */
    private String loadJSONElement(String key) {

        // Initialize the value.
        String value = "";

        // Create a JSON parser.
        JSONParser jsonParser = new JSONParser();

        // Load the specified element from the pattern's JSON file.
        try {

            // Get an input stream for the pattern's json file.
            // (Returns null if the file was not found.)
            InputStream inputStream = getClass().getClassLoader()
                    .getResourceAsStream(getKey() + JSON_EXTENSION);
            if (inputStream != null) {

                // Read the input stream into a string.
                // Throws: IOException
                String jsonString = readStreamIntoString(inputStream);

                // Read the file and parse it into JSON.
                // Throws: IOException, ParseException
                JSONObject jsonObject =  (JSONObject) jsonParser.parse(jsonString);

                // Get the specified element.
                String readValue = (String) jsonObject.get(key);
                if (readValue != null) {

                    // Save the value.
                    value = readValue;

                    logger.debug(new StringBuilder()
                            .append("Successfully read the '")
                            .append(key)
                            .append("' element for ")
                            .append(getDisplayName())
                            .append(": ")
                            .append(value)
                            .toString());
                }

                // There is no element.
                else {

                    logger.error(new StringBuilder()
                            .append("Failed to read the '")
                            .append(key)
                            .append("' element for ")
                            .append(getDisplayName())
                            .append(": The file did not contain the specified element.")
                            .toString());
                }
            }

            else {

                logger.error(new StringBuilder()
                        .append("Failed to read the '")
                        .append(key)
                        .append("' element for ")
                        .append(getDisplayName())
                        .append(": The file could not be found.")
                        .toString());
            }

        } catch (ParseException | IOException ex) {

            logger.error(new StringBuilder()
                    .append("Failed to read the '")
                    .append(key)
                    .append("' element for ")
                    .append(getDisplayName())
                    .append(": ")
                    .append(ex.getMessage())
                    .toString());
        }

        // Return the newly loaded element.
        return value;
    }

    /**
     * Reads the given input stream into a string.
     *
     * @param inputStream the {@link InputStream} to process
     * @return a string representation of the input stream
     * @throws IOException if an error occurs
     */
    private String readStreamIntoString(InputStream inputStream) throws IOException {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Charset.defaultCharset()))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

    // TODO: Need movement operations.
    // TODO: Needs to load the movements from the pattern's JSON file.

    /*
     * Overridden {@link Object} operations
     */

    /**
     * Call the parent {@link SkillEntity} object's operation.
     *
     * @return the instance's hash code
     */
    @Override
    public int hashCode() { return super.hashCode(); }
}
