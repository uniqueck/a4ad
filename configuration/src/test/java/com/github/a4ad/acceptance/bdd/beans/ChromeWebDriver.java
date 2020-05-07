package com.github.a4ad.acceptance.bdd.beans;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

@Slf4j
public class ChromeWebDriver extends EventFiringWebDriver {

    private static final WebDriver webdriver;

    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            webdriver.close();
        }
    };

    static {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");

        ChromeOptions capabilities = new ChromeOptions();
        capabilities.addArguments("test-type");
        capabilities.addArguments("start-maximized");
        capabilities.addArguments("--js-flags=--expose-gc");
        capabilities.setExperimentalOption("useAutomationExtension", false);
        capabilities.addArguments("--enable-precise-memory-info");
        capabilities.addArguments("--disable-popup-blocking");
        capabilities.addArguments("--disable-default-apps");
        capabilities.addArguments("--enable-automation");
        capabilities.addArguments("test-type=browser");
        capabilities.addArguments("disable-infobars");
        capabilities.addArguments("disable-extensions");
        webdriver = new ChromeDriver(capabilities);

        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public ChromeWebDriver() {
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
