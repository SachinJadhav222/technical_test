package com.turing.niallbunting;

import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/Test-Reports/Test-Report.html"},
        features = "classpath:featureFiles",
        glue = "classpath:",
        format = {"pretty", "html:target/cucumberReports/Destination"},
        tags = "@api"
       )
public class CucumberTestRunner {
       @AfterClass
       public static void teardown() {
              Reporter.loadXMLConfig(new File("src/test/resources/extent-report-config.xml"));
              Reporter.setSystemInfo("QA Analyst :", System.getProperty("user.name"));
              Reporter.setSystemInfo("Operating System :", "Window");
              Reporter.setSystemInfo("Test Tool :", "REST Assured");
              Reporter.setTestRunnerOutput("Test output message");
       }
}
