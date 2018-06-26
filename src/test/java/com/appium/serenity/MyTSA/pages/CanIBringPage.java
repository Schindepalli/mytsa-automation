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

public class CanIBringPage extends PageObject {

    public AppiumDriver driver;

    public CanIBringPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, 30, TimeUnit.SECONDS), this);
    }


    @AndroidFindBy(id = "item_text_view")
    private MobileElement searchBar;

    @AndroidFindBy(accessibility = "Travel Checklist Link")
    private MobileElement travelChecklist;

    @AndroidFindBy(accessibility = "Identification Link")
    private MobileElement identification;

    @AndroidFindBy(accessibility = "Liquids Rule Link")
    private MobileElement liquidsRule;

    @AndroidFindBy(accessibility = "Disabilities and Medical Conditions Link")
    private MobileElement disabilitiesAndMedicalConditions;

    @AndroidFindBy(accessibility = "Military Travel Link")
    private MobileElement militaryTravel;

    @AndroidFindBy(accessibility = "Travel with Children Link")
    private MobileElement travelWithChildren;

    @iOSFindBy(accessibility = "Can I bring ? Heading Level 1")
    private MobileElement canIBringHeader;
}
