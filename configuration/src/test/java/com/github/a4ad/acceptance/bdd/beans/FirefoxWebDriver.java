package com.github.a4ad.acceptance.bdd.beans;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

@Slf4j
public class FirefoxWebDriver extends EventFiringWebDriver {

    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            webdriver.close();
        }
    };
    private static final WebDriver webdriver;

    static {
        try {
            System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");

            FirefoxOptions capabilities = new FirefoxOptions();
            webdriver = new FirefoxDriver(capabilities);

            Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public FirefoxWebDriver() {
        super(webdriver);
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            log.info("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
    }
}
