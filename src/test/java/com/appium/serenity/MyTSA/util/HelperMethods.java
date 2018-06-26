package com.appium.serenity.MyTSA.util;

import com.appium.serenity.MyTSA.steps.BeforeAndAfter;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.util.Properties;

public class HelperMethods {

    public static String readAndGrabProps(String prop) {
        String env = null;
        try {
            Properties props = new Properties();
            String propFileName = "/test.properties";

            props.load(HelperMethods.class.getResourceAsStream(propFileName));

            env = props.getProperty(prop);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return env;
    }

}
