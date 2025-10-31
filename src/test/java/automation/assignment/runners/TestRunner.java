package automation.assignment.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/automation/assignment/features", // path to your .feature files
    glue = "automation.assignment.stepdefs", // package for step defs
    plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"},
    monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {
  // no code needed here
}
