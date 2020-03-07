package com.github.a4ad.adapters.web.ui.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@ExtendWith({ScreenshotOnFailureExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexControllerLiveTest {

    @LocalServerPort
    private int localServerPort;

    @Container
    private BrowserWebDriverContainer container = new BrowserWebDriverContainer().withCapabilities(new ChromeOptions());


    @Test
    void testIndexPage() throws Exception {
        container.getWebDriver().get("http://172.17.0.1:" + localServerPort + "/");
        WebElement h5 = container.getWebDriver().findElementById("pendingDeploymentsTableTitle");
        assertNotNull(h5);
        assertEquals("Scheduled & Pending Deployments", h5.getText());
    }


}