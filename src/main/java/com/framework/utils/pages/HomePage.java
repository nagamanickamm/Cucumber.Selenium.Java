package com.framework.utils.pages;

import com.framework.utils.common.PageBase;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

public class HomePage extends PageBase {

    public String pageTitle = "Playtech - the source of success";

    //_______________________Contents/WebElements____________________________

    @FindBy(css = "#age-verification .modal.fade")
    private WebElement ageVerificationPopup;

    @FindBy(css = "#age-verification div h3")
    private WebElement ageVerificationPopupHeading;

    @FindBy(css = "#age-verification div p:nth-child(2)")
    private WebElement ageVerificationContent1;

    @FindBy(css = "#age-verification div p:nth-child(3)")
    private WebElement ageVerificationContent2;

    @FindBy(css = "#age-verification div h3:nth-child(4)")
    private WebElement ageVerificationPopupHeading2;

    @FindBy(name = "day")
    private WebElement dayDropDown;

    @FindBy(name = "month")
    private WebElement monthDropDown;

    @FindBy(name = "year")
    private WebElement yearDropDown;

    @FindBy(css = ".btn.btn-default.submit")
    private WebElement submitButton;

    @FindBy(className = "age-error")
    private WebElement ageError;

    @FindBy(id = "banner-slider-wrapper")
    private WebElement homePageBanner;

    @FindBy(id = "trigger")
    private WebElement menu;

    @FindBy(css = "a[href=\"/about-us\"]")
    private WebElement aboutUs;


    //_______________________Actions/Methods____________________________

    public void verifyPageTitle(WebDriver driver) {
        waitForTitleMatch(driver, pageTitle);
    }

    public void verifyAgeVerificationPopup(WebDriver driver, String alertHeading) {
        waitForElementDisplayed(driver, ageVerificationPopup);
        verifyText(driver, alertHeading, ageVerificationPopupHeading);
    }

    public void verifyAgeVerificationMessage(WebDriver driver, String alertMessage) {
        verifyText(driver, alertMessage, ageVerificationPopupHeading2);
    }

    public void verifyAlertContent(WebDriver driver, String messageProp) {
        verifyText(driver, System.getProperty(messageProp), ageVerificationContent1);
    }

    public void verifyAlertContent2(WebDriver driver, String messageProp) {
        verifyText(driver, System.getProperty(messageProp), ageVerificationContent2);
    }

    public void submitYourDateOfBirth(Map<String, String> dob) {
        dayDropDown.sendKeys(dob.get("Day"));
        monthDropDown.sendKeys(dob.get("Month"));
        yearDropDown.sendKeys(dob.get("Year"));
        submitButton.click();
    }

    public void checkErrorMessage(WebDriver driver, String error) {
        verifyText(driver, error, ageError);
    }

    public void verifyUserEnteredHomepage(WebDriver driver) {
        waitForElementNotDisplayed(driver, "body.modal-open");
        waitForElementDisplayed(driver, homePageBanner);
        Assert.assertFalse(ageVerificationPopup.isDisplayed());
        Assert.assertTrue(homePageBanner.isDisplayed());
    }

    public void gotoAboutUsPage(WebDriver driver) {
        waitForElementClickable(driver, menu);
        menu.click();
        aboutUs.click();
    }
}
