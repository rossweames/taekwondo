/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package com.eames.taekwondo.handlers.pattern;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.eames.taekwondo.model.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.amazon.ask.request.Predicates.intentName;

/**
 * This is the handler for the 'pattern diagram' skill.
 *
 * TODO: Need unit tests for this class.
 */
public class PatternDiagramIntentHandler extends PatternIntentHandler {

    // Initialize the Log4j logger.
    private static final Logger logger = LogManager.getLogger(PatternDiagramIntentHandler.class);

    /**
     * Default Constructor
     */
    public PatternDiagramIntentHandler() {
        logger.debug("Constructing PatternDiagramIntentHandler");
    }

    /**
     * Determine whether this intent can handle the request.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return {@code True} if this intent can handle the request, {@code false} if not
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("PatternDiagramIntent"));
    }

    /**
     * Gets the answer speech text.
     *
     * @param pattern the {@link Pattern} to use
     * @return the speech text answer
     */
    @Override
    protected String getAnswer(Pattern pattern) {

        return new StringBuilder()
                .append("The ")
                .append(pattern.getPhoneticName())
                .append(" pattern has the ")
                .append(pattern.getDiagram().getPhoneticName())
                .append(" diagram.")
                .toString();
    }
}
