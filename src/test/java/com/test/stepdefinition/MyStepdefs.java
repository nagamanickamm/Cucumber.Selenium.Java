package com.test.stepdefinition;

import com.framework.utils.driver.WebSiteBase;
import com.framework.utils.pages.AboutUsPage;
import com.framework.utils.pages.HomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Map;

public class MyStepdefs extends WebSiteBase {

    private HomePage homePage;
    private AboutUsPage aboutUsPage;

    @Before()
    public void setup() {
        driverInit();
        homePage = PageFactory.initElements(driver, HomePage.class);
        aboutUsPage = PageFactory.initElements(driver, AboutUsPage.class);
    }

    @After()
    public void cleanup() {
        driver.quit();
    }

    @Given("Im on Playtech homepage")
    public void imOnPlaytechHomepage() {
        homePage.verifyPageTitle(driver);
    }

    @When("I see Age Verification Alert with heading as {string}")
    public void iSeeAgeVerificationAlertWithHeadingAs(String heading) {
        homePage.verifyAgeVerificationPopup(driver, heading);
    }

    @And("I enter my age and submit")
    public void iSelectBelowDOBInTheDropDownAndSubmit(DataTable dataTable) {
        List<Map<String, String>> dob = dataTable.asMaps();
        homePage.submitYourDateOfBirth(dob.get(0));
    }

    @Then("I see the error message {string}")
    public void iSeeTheErrorMessage(String error) {
        homePage.checkErrorMessage(driver,error);
    }

    @And("I see message on the Alert {string}")
    public void iSeeMessageOnTheAlert(String message) {
        homePage.verifyAgeVerificationMessage(driver, message);
    }

    @Then("I can enter into the homepage now")
    public void iCanEnterIntoTheHomepageNow() {
        homePage.verifyUserEnteredHomepage(driver);
    }

    @And("I see content on the Alert {string}")
    public void iSeeContentOnTheAlert(String content) {
        homePage.verifyAlertContent(driver, content);
    }

    @And("I see responsible Gambling message on the Alert {string}")
    public void iSeeResponsibleGamblingMessageOnTheAlert(String content) {
        homePage.verifyAlertContent2(driver, content);
    }

    @When("I go to AboutUs page")
    public void iGoToAboutUsPage() {
        homePage.gotoAboutUsPage(driver);
    }

    @Then("Iam at AboutUs page")
    public void iamAtAboutUsPage() {
        aboutUsPage.verifyPageTitle(driver);
        aboutUsPage.verifyAboutUsPage(driver);
    }

    @When("I go to the company stats section")
    public void iGoToTheCompanyStatsSection() {
        aboutUsPage.gotoStats(driver);
    }

    @Then("I see {string} i.e {string} as {int}")
    public void iSeeAs(String key, String text, int value) {
        aboutUsPage.verifyCompanyStats(driver, key, text, value);
    }
}
