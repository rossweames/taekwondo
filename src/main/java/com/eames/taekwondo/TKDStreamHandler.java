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
                        new CurrentPatternIntentHandler(),
                        new CurrentPatternStepIntentHandler(),
                        new PatternDiagramIntentHandler(),
                        new PatternHistoryIntentHandler(),
                        new PatternInfoIntentHandler(),
                        new PatternPracticeLevelIntentHandler(),
                        new PatternStartingPositionIntentHandler(),
                        new PatternStepCountIntentHandler(),
                        new PatternStepDetailsIntentHandler(),
                        new SelectPatternIntentHandler(),
                        new TeachPatternIntentHandler())
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
