package com.appium.serenity.MyTSA.runners;

import com.appium.serenity.MyTSA.steps.BeforeAndAfter;
import com.appium.serenity.MyTSA.steps.MyTsaSteps;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.logging.Logger;

import static com.appium.serenity.MyTSA.util.HelperMethods.readAndGrabProps;

@RunWith(SerenityRunner.class)
@WithTag("epic:android")
public class OnboardingTutorialTestAndroid {

    Logger logger = Logger.getLogger(OnboardingTutorialTestAndroid.class.getName());


    @Steps
    BeforeAndAfter beforeAndAfter;
    @Steps
    MyTsaSteps myTsaSteps;

    @Before
    public void setUp() {
        try {
            String os = readAndGrabProps("androidEnvironment");
            Serenity.setSessionVariable("environment").to(os);
            beforeAndAfter.startUp(os);
            logger.info("Environment: " + os);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        logger.info("----------------------closing driver-------------------------");
        beforeAndAfter.stopDriver();
    }

    @Test
    public void androidOnboardingTutorialTest() {
        //set up android emulator
        myTsaSteps.testSetUp();

        myTsaSteps.verifyGetStartedHeader();
        myTsaSteps.skipGetStarted();

        myTsaSteps.startSetupProcess();
        myTsaSteps.allowLocationServiceSetUp();
        myTsaSteps.disagreeToTermsAndNotificationAndroid();

        myTsaSteps.resetTheApp();

        myTsaSteps.verifyGetStartedHeader();
        myTsaSteps.skipGetStarted();
        myTsaSteps.startSetupProcess();
        myTsaSteps.doNotAllowLocationServices();
        myTsaSteps.agreeToTerms();

        myTsaSteps.resetTheApp();

        //view Onboarding tutorial
        myTsaSteps.verifyGetStartedHeader();
        //tap on get started
        myTsaSteps.viewOnboardingTutorial();
        //swipe through tutorial carousel and verify title
        myTsaSteps.swipeAndVerifyPages();
        //click on next at the top to exit tutorial and continue set up
        myTsaSteps.startSetupProcess();
        myTsaSteps.allowLocationServiceSetUp();
        myTsaSteps.agreeToTerms();

//        myTsaSteps.verifyMyAirportsPageOnAndroid();
//        myTsaSteps.checkPagesForCorrectnessAndroid();
    }
}
