package com;

import com.google.common.base.Preconditions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class DesktopTestBase {


    protected static final Logger LOG = LoggerFactory.getLogger(DesktopTestBase.class);
    private static WebDriver webDriver;

    @BeforeEach
    public void createAndConfigureWebDriver(TestInfo testInfo) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        chromeOptions.addArguments("--headless");
        chromeOptions.setCapability("browserName", "chrome");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        chromeOptions.setCapability("enableVNC", true);
        chromeOptions.merge(desiredCapabilities);

        try {
            webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        LOG.info(
                "Starting test ["
                        + getTestMethodName(testInfo));

        getWebDriver().manage().deleteAllCookies();
        getWebDriver().manage().window().setSize(new Dimension(1920, 1400));
        Preconditions.checkNotNull(getWebDriver(), "Failed to set up the WebDriver");
    }

    protected String getTestMethodName(TestInfo testInfo) {
        if (testInfo.getTestMethod().isPresent()) {
            return testInfo.getTestMethod().get().getName();
        } else {
            return "";
        }
    }

    @AfterEach
    public void tearDownWebDriver(TestInfo testInfo) {
        String testMethodName = getTestMethodName(testInfo);
        LOG.info("Finishing test [" + testMethodName + "].");
        getWebDriver().manage().deleteAllCookies();
        getWebDriver().quit();
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
}
