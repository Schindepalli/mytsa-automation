package com.appium.serenity.MyTSA.steps;


import com.appium.serenity.MyTSA.pages.*;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import static com.appium.serenity.MyTSA.steps.BeforeAndAfter.driver;

public class MyTsaSteps extends ScenarioSteps {

    GetStartedPage getStartedPage;
    ReportWaitTimesPage reportWaitTimesPage;
    GetNotifiedPage getNotifiedPage;
    TermsOfServicePage termsOfServicePage;
    MyAirportsPage myAirportsPage;
    GetStartedTutorialPage getStartedTutorialPage;

    @Step
    public void testSetUp() {
        getStartedPage = new GetStartedPage(driver);
        reportWaitTimesPage = new ReportWaitTimesPage(driver);
        getNotifiedPage = new GetNotifiedPage(driver);
        termsOfServicePage = new TermsOfServicePage(driver);
        myAirportsPage = new MyAirportsPage(driver);
        getStartedTutorialPage = new GetStartedTutorialPage(driver);
    }

    @Step
    public void verifyGetStartedHeader() {
        getStartedPage.isGetStartedBtnDisplayed();
    }

    @Step
    public void skipGetStarted() {
        getStartedPage.clickSkipButton();
    }

    @Step
    public void allowLocationServiceSetUp() {
        reportWaitTimesPage.isReportWaitTimesPageDisplayed();
        reportWaitTimesPage.clickYesPlease();
        reportWaitTimesPage.allowLocationServices();
    }

    @Step
    public void agreeToTerms() {
        termsOfServicePage.clickAgreeButton();
    }

    @Step
    public void startSetupProcess() {
        getNotifiedPage.isGetNotifiedPageDisplayed();
        getNotifiedPage.clickNext();
    }

    @Step
    public void verifyMyAirportsPageOnAndroid() {
        myAirportsPage.verifyHeaderIsDisplayedAndroid();
    }

    @Step
    public void verifyMyAirportsPageOnIos() {
        myAirportsPage.verifyCorrectHeaderIsDisplayediOS();
    }

    @Step
    public void checkPagesForCorrectnessAndroid() {
        myAirportsPage.verifyAllExpectedTabsAreDisplayed();
    }

    @Step
    public void checkPagesForCorrectnessIOS() {
        myAirportsPage.verifyAllExpectedTabsAreDisplayed();
        myAirportsPage.verifyTabNavigation();
    }

    @Step
    public void agreeToTermsAndNotification() {
        termsOfServicePage.clickAgreeButton();
    }

    @Step
    public void disagreeToTermsAndNotificationIOS() {
        termsOfServicePage.isTermsPageDisplayed();
        termsOfServicePage.clickOkAfterDeclining();
    }

    @Step
    public void viewOnboardingTutorial() {
        getStartedPage.clickGetStarted();
    }

    @Step
    public void swipeAndVerifyPages() {
        getStartedTutorialPage.swipeThroughPages("Pack smart", "Plan for your trip", "Inform fellow travelers");
    }

    @Step
    public void resetTheApp() {
        driver.resetApp();
    }

    @Step
    public void disagreeToTermsAndNotificationAndroid() {
        termsOfServicePage.isTermsPageDisplayed();
        termsOfServicePage.declineTerms();
        termsOfServicePage.clickOkAndCloseApp();
    }

    @Step
    public void doNotAllowLocationServices() {
        reportWaitTimesPage.doNotAllow();
    }

    @Step
    public void resetTheIOSApp() {
        driver.installApp("/Users/schindepalli/Documents/Projects/mytsa-automation-test/src/test/resources/testapps/MyTSA.app");
        driver.launchApp();
    }
}
