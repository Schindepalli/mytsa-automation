package com.appium.serenity.MyTSA.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.JavascriptExecutor;

import java.util.HashMap;


public class Gestures {

    public static void swipeIOS(AppiumDriver driver) {
        JavascriptExecutor js = driver;
        HashMap<String, String> swipeObject = new HashMap<String, String>();
        swipeObject.put("direction", "left");
        js.executeScript("mobile: swipe", swipeObject);
    }

    public static void swipeAndroid(AppiumDriver driver) {
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(-352, 0))
                .moveTo(PointOption.point(352,0))
                .release().perform();
    }

}