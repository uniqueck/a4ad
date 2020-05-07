package com.github.a4ad.acceptance.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Ignore;
import org.junit.runner.RunWith;

@Ignore
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/com/github/a4ad/acceptance/bdd",
                plugin = {"pretty", "html:target/cucumber"},
                extraGlue = "")
public class CucumberRunnerTest {
}
