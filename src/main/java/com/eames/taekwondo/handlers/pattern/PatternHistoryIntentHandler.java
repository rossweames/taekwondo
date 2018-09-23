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
import com.amazon.ask.model.Response;
import com.eames.taekwondo.model.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class PatternHistoryIntentHandler extends PatternIntentHandler {

    // Initialize the Log4j logger.
    private static final Logger logger = LogManager.getLogger(PatternHistoryIntentHandler.class);

    /**
     * Default Constructor
     */
    public PatternHistoryIntentHandler() {
        logger.debug("Constructing PatternHistoryIntentHandler");
    }

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("PatternHistoryIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Pattern History";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("TaeKwon-Do - Pattern History", speechText)
                .withShouldEndSession(false)
                .build();
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
                .append(" pattern has the following history: ")
                .append(pattern.getHistory())
                .toString();
    }
}
