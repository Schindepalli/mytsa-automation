package com.appium.serenity.MyTSA.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class MyProfilePage extends PageObject {

    public AppiumDriver driver;

    public MyProfilePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, 30, TimeUnit.SECONDS), this);
    }

    @AndroidFindBy(id = "preferences_known_traveler_number")
    private MobileElement travelerNumber;

    @AndroidFindBy(id = "mockLocationSwitch")
    private MobileElement mockLocation;

    @AndroidFindBy(id = "ask_tsa_launch_tutorial")
    private MobileElement launchTutorial;

    @AndroidFindBy(id = "ask_tsa_about_wait_times")
    private MobileElement aboutWaitTimes;

    @AndroidFindBy(id = "ask_tsa_app_version_value")
    private MobileElement appVersion;

    @iOSFindBy(accessibility = "My PROFILE. Heading level 1")
    private MobileElement myProfileHeader;
}
