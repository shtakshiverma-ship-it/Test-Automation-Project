package automation.assignment.stepdefs;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

import automation.assignment.framework.config.browser.BrowserManager;
import com.codeborne.selenide.Condition;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;
import java.time.Duration;
import org.testng.Assert;

public class validateCarDutyStep {
  private BrowserManager browser = new BrowserManager(); // manually instantiate

  private long waitDuration = 10000;

  // Locators
  protected String btnCheckOnline = "//a[contains(normalize-space(text()),'Check online')]";
  protected String lblPageTitle = "//h1[@id='skip']";
  protected String lblCalculatorType = "//h2[normalize-space(text='Motor vehicle registration duty calculator')]";
  protected String rdoPassengerVehicle;
  protected String txtVehicleAmount ="//input[@id='purchasePrice']";
  protected String btnCalculateAmount="//button[normalize-space()='Calculate']";
  protected String modalTitleCalculation="//h4[normalize-space()='Calculation']";
  protected String lblRgistrationPassengeVeichle="//td[contains(normalize-space(text()),'Is this registration for a passenger vehicle?')]/following-sibling::td";
  protected String lblPurchasePrice ="//td[contains(normalize-space(text()),'Purchase price or value')]/following-sibling::td";
  protected String lblDutyPayable ="//td[contains(normalize-space(text()),'Duty payable')]/following-sibling::td";
  protected String btnClose="//button[normalize-space()='Close']";
  @Given("User access {string} URL")
  public void userAccessURL(String dutyURL) throws Exception {
    browser.openUrl(dutyURL);
  }

  @When("User clicks Online Button")
  public void userClicksOnlineButton() {
    $(byXpath(btnCheckOnline))
        .shouldBe(Condition.visible, Duration.ofSeconds(waitDuration))
        .click();
  }

  @Then("open page {string} showing {string}")
  public void openPageShowing(String expectedPageName, String expectedCalculatorType) {
    // Asserting Page Title and Calcultator Title
   // System.out.println("Page title"+$(byXpath(lblPageTitle)).shouldHave(Condition.visible, Duration.ofSeconds(waitDuration)).getText());
    String actualPageName = $(byXpath(lblPageTitle)).shouldHave(Condition.visible, Duration.ofSeconds(waitDuration)).getText();
    String actualCalctype= $(byXpath(lblCalculatorType)).shouldHave(Condition.visible, Duration.ofSeconds(waitDuration)).getText();
    Assert.assertEquals(expectedPageName,actualPageName, "Page title is incorrect");
    Assert.assertEquals(expectedCalculatorType,actualCalctype, "Calculator Type is incorrect");

  }

  @When("Select {string}")
  public void select(String passengerVehicleOption) {
    if (passengerVehicleOption.equalsIgnoreCase("yes"))
    {
      rdoPassengerVehicle="//label[@for='passenger_Y']";
    }
    else
    {
      rdoPassengerVehicle="//label[@for='passenger_N']";
    }
    $(byXpath(rdoPassengerVehicle)).shouldBe(Condition.visible,Duration.ofSeconds(waitDuration)).click();
  }

  @And("Enter {string}")
  public void enter(String vehicleAmount) {
    $(byXpath(txtVehicleAmount)).shouldHave(Condition.visible,Duration.ofSeconds(waitDuration)).setValue(vehicleAmount);
  }

  @And("Click Calculate")
  public void clickCalculate() {
    $(byXpath(btnCalculateAmount)).shouldBe(Condition.visible,Duration.ofSeconds(waitDuration)).click();


  }

  @Then("Pop up window renders the Calculated Duty as per the {string} and {string}")
  public void popUpWindowRendersTheCalculatedDutyAsPerTheAnd(String passengerVehicleOption, String vehicleAmount) {

    String vehicleAmountwithDecimal = new BigDecimal(vehicleAmount).setScale(2).toPlainString();
    Assert.assertTrue($(byXpath(modalTitleCalculation)).shouldBe(Condition.visible, Duration.ofSeconds(waitDuration)).exists());
    String actualPassengerVehicle =$(byXpath(lblRgistrationPassengeVeichle)).shouldBe(Condition.visible,Duration.ofSeconds(waitDuration)).getText();
    String actualPurchasePrice= $(byXpath(lblPurchasePrice)).shouldBe(Condition.visible,Duration.ofSeconds(waitDuration)).getText();
    actualPurchasePrice=actualPurchasePrice.replaceAll("[$,]", "");
    Assert.assertEquals(actualPassengerVehicle,passengerVehicleOption,"Passenger Veichle option is incorrect");
    Assert.assertEquals(actualPurchasePrice,vehicleAmountwithDecimal,"Veichle Amount showing incorrect");
    Assert.assertTrue($(byXpath(lblDutyPayable)).shouldBe(Condition.visible,Duration.ofSeconds(waitDuration)).isDisplayed());
    System.out.println("Duty Payable :"+ $(byXpath(lblDutyPayable)).shouldBe(Condition.visible,Duration.ofSeconds(waitDuration)).getText());
    $(byXpath(btnClose)).shouldBe(Condition.visible,Duration.ofSeconds(waitDuration)).click();

  }



}
