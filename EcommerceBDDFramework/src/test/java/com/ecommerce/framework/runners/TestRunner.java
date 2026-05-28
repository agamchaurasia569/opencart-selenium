package com.ecommerce.framework.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.ecommerce.framework.stepdefinitions", "com.ecommerce.framework.hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/report.html",
                "json:target/cucumber-reports/report.json",
                "summary"
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
