package com.eames.taekwondo.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private static final String JSON_START_KEY = "start";
    private static final String JSON_MOVEMENTS_KEY = "movements";
    private static final String JSON_FINISH_KEY = "finish";
    private static final String JSON_SHORT_DESCRIPTION_KEY = "shortDescription";
    private static final String JSON_FULL_DESCRIPTION_KEY = "fullDescription";

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
     *
     * Contains the start and finish movements as well.
     * The [0] element is the start movement.
     * The [1]..[n] elements contain the pattern movements.
     * The [n+1] element is the finish movement.
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
    Pattern(String key, String displayName, String phoneticName, Belt beltLevel,
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

        // The history paragraph has not been loaded, so load it.
        if (history == null)
            history = (String) loadJSONElement(JSON_HISTORY_KEY);

        // The history paragraph has already been loaded.
        else
            logger.debug(new StringBuilder()
                    .append("The history for ")
                    .append(getDisplayName())
                    .append(" has already been loaded.")
                    .toString());

        // Return the history paragraph.
        return history;
    }

    /**
     * Gets the pattern nth movement.
     * Loads the movements from the pattern's JSON file the first time any of them are requested.
     * The JSON file is named using the pattern's key.
     *
     * @return the pattern nth movement
     */
    public Movement getNthMovement(int idx) {

        // The movements have not already been loaded, so load them.
        if (movements == null)
            movements = loadMovements();

        // The movements have already been loaded.
        else
            logger.debug(new StringBuilder()
                    .append("The movements for ")
                    .append(getDisplayName())
                    .append(" have already been loaded.")
                    .toString());

        // The index is out of range, so return null.
        if ((idx < 0) || (idx > (movementCount + 1))) {

            logger.error(new StringBuilder()
                    .append("Invalid movement index for ")
                    .append(getDisplayName())
                    .append(" (")
                    .append(idx)
                    .append(").")
                    .toString());

            return null;
        }

        // There are no movements.
        else if ((movements == null) || (movements.length < (movementCount + 1))) {

            logger.error(new StringBuilder()
                    .append("The movements for ")
                    .append(getDisplayName())
                    .append(" could not be loaded.")
                    .toString());

            return null;
        }

        // Return the specified movement.
        return movements[idx];
    }

    /**
     * Gets the pattern start movement.
     * Loads the movements from the pattern's JSON file the first time any of them are requested.
     * The JSON file is named using the pattern's key.
     *
     * @return the pattern start movement
     */
    public Movement getStartMovement() {

        // Return the start movement.
        return getNthMovement(0);
    }

    /**
     * Gets the pattern finish movement.
     * Loads the movements from the pattern's JSON file the first time any of them are requested.
     * The JSON file is named using the pattern's key.
     *
     * @return the pattern finish movement
     */
    public Movement getFinishMovement() {

        // Return the start movement.
        return getNthMovement(movementCount + 1);
    }

    /**
     * Clears the movement array.
     */
    public void clearMovements() {

        // Clear the movements array.
        movements = null;

        logger.debug(new StringBuilder()
                .append("Successfully cleared the movements for ")
                .append(getDisplayName())
                .append(".")
                .toString());
    }

    /**
     * Loads the {@link Movement}s for this pattern.
     * Loads the start and finish movements as well.
     *
     * @return the full array of movements
     */
    private Movement[] loadMovements() {

        // The Movement array to return
        List movementList = new ArrayList(movementCount + 2);

        // Load the start movement and add it to the list.
        JSONObject jsonStart = (JSONObject) loadJSONElement(JSON_START_KEY);
        movementList.add(createMovement(jsonStart));

        // Load the movements and add them to the list.
        JSONArray jsonMovementArray = (JSONArray) loadJSONElement(JSON_MOVEMENTS_KEY);
        jsonMovementArray.stream().forEachOrdered(e -> movementList.add(createMovement((JSONObject) e)));

        // Load the finish movement and add it to the list.
        JSONObject jsonFinish = (JSONObject) loadJSONElement(JSON_FINISH_KEY);
        movementList.add(createMovement(jsonFinish));

        // Convert the list to an array and return it.
        Movement[] movementArray = new Movement[movementCount + 2];
        movementList.toArray(movementArray);
        return movementArray;
    }

    /**
     * Loads the given element from the pattern's JSON file.
     * The JSON file is named using the pattern's key.
     *
     * @param key the key of the JSON element to load
     * @return an object representing the JSON element
     */
    private Object loadJSONElement(String key) {

        // Initialize the object to be read.
        Object readObject = null;

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

                // Get and return the specified element as an object.
                readObject = jsonObject.get(key);
                if (readObject != null) {

                    logger.debug(new StringBuilder()
                            .append("Successfully read the '")
                            .append(key)
                            .append("' element for ")
                            .append(getDisplayName())
                            .append(": ")
                            .append(readObject.toString())
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

            // Could not open the file input stream.
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
        return readObject;
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

    /**
     * Creates a new {@link Movement} from the given element {@link Map}.
     *
     * @param element the element to use (a map)
     * @return the newly created movement
     */
    private Movement createMovement(JSONObject element) {

        return new Movement((String) element.get(JSON_SHORT_DESCRIPTION_KEY),
                (String) element.get(JSON_FULL_DESCRIPTION_KEY));
    }

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
