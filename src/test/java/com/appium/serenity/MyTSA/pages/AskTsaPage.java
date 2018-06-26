package com.appium.serenity.MyTSA.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class AskTsaPage extends PageObject {

    protected AppiumDriver driver;

    public AskTsaPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, 30, TimeUnit.SECONDS), this);
    }

    @iOSFindBy(accessibility = "Ask T S A. Heading Level 1")
    private MobileElement askTSAheader;

    @AndroidFindBy(id = "ask_tsa_contact_center_phone")
    private MobileElement contactPhone;

    @AndroidFindBy(id = "ask_tsa_contact_center_email")
    private MobileElement contactEmail;

    @AndroidFindBy(id = "ask_tsa_cares_phone")
    private MobileElement caresPhone;

    @AndroidFindBy(id = "ask_tsa_website_link")
    private MobileElement tsaWebsite;

    @AndroidFindBy(id = "item_twitter")
    private MobileElement askTsaTwitter;
}
