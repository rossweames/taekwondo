/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package com.eames.taekwondo;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.eames.taekwondo.handlers.*;
import com.eames.taekwondo.handlers.pattern.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is the skill stream handler.
 * It registers all the skill intents.
 *
 * TODO: Needs unit tests.
 */
public class TKDStreamHandler extends SkillStreamHandler {

    // Initialize the Log4j logger.
    private static final Logger logger = LogManager.getLogger(TKDStreamHandler.class);

    /**
     * The skill id
     */
    static private final String SKILL_ID = "amzn1.ask.skill.1d1acb6b-7736-432d-a527-8844ae6ba422";

    /**
     * Gets called by the Alexa Skill framework to register handlers for this skill.
     *
     * @return a {@link Skill} with this skill's id and its backend handlers
     */
    private static Skill getSkill() {
        return Skills.standard()
                .withSkillId(SKILL_ID)
                .addRequestHandlers(
                        new CancelIntentHandler(),
                        new FallbackIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler(),
                        new StopIntentHandler(),

//                        new BeltPatternIntentHandler(),
                        new PatternDiagramIntentHandler(),
                        new PatternHistoryIntentHandler(),
                        new PatternInfoIntentHandler(),
                        new PatternPracticeLevelIntentHandler(),
                        new PatternStartingPositionIntentHandler(),
                        new PatternStepCountIntentHandler(),
                        new SelectPatternIntentHandler())
                .build();
    }

    /**
     * Constructor
     */
    public TKDStreamHandler() {

        // Call the base class constructor.
        super(getSkill());

        logger.debug("Constructing TKDStreamHandler");
    }
}
