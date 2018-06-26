package com.appium.serenity.MyTSA.steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

public class BeforeAndAfter {
    protected static AppiumDriver driver;

    public void startUp(String os) throws Exception{
        driver = new AppiumDriver(new URL("http://127.0.0.1:8888/wd/hub"), capabilities(os));
    }

    public DesiredCapabilities capabilities(String os) {

        DesiredCapabilities caps = new DesiredCapabilities();

        if (os.equalsIgnoreCase("android")) {
            File file = new File("src/test/resources/testapps/MyTSA-Test.apk");
            String apkpath = file.getAbsolutePath();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            caps.setCapability(AndroidMobileCapabilityType.AVD, "Pixel2XL");
//            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel2XL");
            caps.setCapability(MobileCapabilityType.APP, apkpath);
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1");
        } else if (os.equalsIgnoreCase("ios")){
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");
//            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            caps.setCapability(MobileCapabilityType.APP, "/Users/schindepalli/Documents/Projects/mytsa-automation-test/src/test/resources/testapps/MyTSA.app");
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.4");
        }
        return caps;
    }

    public void stopDriver() {
            driver.quit();
    }
}
