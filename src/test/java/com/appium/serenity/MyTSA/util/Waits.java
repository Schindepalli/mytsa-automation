package com.appium.serenity.MyTSA.util;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class Waits {

    static Logger logger = Logger.getLogger(Waits.class.getName());

    public static MobileElement waitForElement(MobileElement element){
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        logger.info("Waiting for element"+element);
        wait.until(ExpectedConditions.visibilityOf(element));
        logger.info("element found");
        return element;
    }

    public static MobileElement waitForElementToBeClickable(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        logger.info("Waiting for element"+element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        logger.info("element found");
        return element;
    }
}
