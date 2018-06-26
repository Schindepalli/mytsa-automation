package com.appium.serenity.MyTSA.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class preCheckPage extends PageObject {

    public AppiumDriver driver;

    public preCheckPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, 30, TimeUnit.SECONDS), this);
    }

    @iOSFindBy(accessibility = "T S A Pre✓. Heading Level 1")
    private MobileElement preCheckheader;

    @AndroidFindBy(id = "precheck_apply_button")
    private MobileElement applyButton;

    @AndroidFindBy(accessibility = "View Participating Airports Link")
    private MobileElement viewAirports;

    @AndroidFindBy(accessibility = "View Participating Airlines Link")
    private MobileElement viewAirlines;

    @AndroidFindBy(id = "details_button")
    private MobileElement learnMore;
}
