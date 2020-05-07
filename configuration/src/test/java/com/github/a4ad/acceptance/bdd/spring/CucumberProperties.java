package com.github.a4ad.acceptance.bdd.spring;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:cucumber.properties")
public class CucumberProperties {

    @Value("${selenium.screenshotOnFailure}")
    private String screenshotOnFailure;

    @Bean("screenshotOnFailure")
    public boolean takeScreenshotOnFailure() {
        return Boolean.parseBoolean(screenshotOnFailure);
    }

    @Value("${selenium.screenshotDestinationFolder}")
    private String screenshotDestinationFolder;

    @Bean("screenshotDestinationFolder")
    public String getScreenshotDestinationFolder() {
        return screenshotDestinationFolder;
    }

    @Value("${selenium.webbrowser}")
    private String webbrowser;

    @Bean("webbrowser")
    public String getWebbrowser() {
        return webbrowser;
    }
}
