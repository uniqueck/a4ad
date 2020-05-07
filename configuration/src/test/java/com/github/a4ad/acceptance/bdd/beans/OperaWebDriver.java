package com.github.a4ad.acceptance.bdd.beans;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

@Slf4j
public class OperaWebDriver extends EventFiringWebDriver {

    private static final WebDriver webdriver;

    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            webdriver.close();
        }
    };

    static {
        System.setProperty("webdriver.opera.driver", "src/test/resources/operadriver");

        OperaOptions capabilities = new OperaOptions();
        webdriver = new OperaDriver(capabilities);

        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public OperaWebDriver() {
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
