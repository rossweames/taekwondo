/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package com.eames.taekwondo.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.eames.taekwondo.handlers.pattern.utilities.IntentUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

/**
 * This is the handler for the 'help' command.
 *
 * TODO: Need unit tests for this class.
 */
public class HelpIntentHandler extends IntentHandler {

    // Initialize the Log4j logger.
    private static final Logger logger = LogManager.getLogger(HelpIntentHandler.class);

    /**
     * Default Constructor
     */
    public HelpIntentHandler() {
        logger.debug("Constructing HelpIntentHandler");
    }

    /**
     * Determine whether this intent can handle the request.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return {@code True} if this intent can handle the request, {@code false} if not
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.HelpIntent"));
    }

    /**
     * Gets called by the skill framework when there is something for this intent to do.
     * Creates an Alexa skill card containing the help information.
     * Keeps the session open.
     *
     * @param input the {@link HandlerInput} request object to process
     * @return a response containing the help speech text
     */
    @Override
    public Optional<Response> handle(HandlerInput input) {

        logger.debug(new StringBuilder()
                .append("HelpIntentHandler (")
                .append(IntentUtilities.getRequestClassName(input))
                .append("): dialogState=")
                .append(IntentUtilities.getDialogState(input).toString())
                .toString());

        // TODO: Need to set the help information string.

        String speechText = "You can ask for help!";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("TaeKwon-Do - Help", speechText)
                .withShouldEndSession(false)
                .build();
    }
}
