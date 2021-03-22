package com.framework.utils.pages;

import com.framework.utils.common.PageBase;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AboutUsPage extends PageBase {

    public String pageTitle = "Company Overview Playtech";

    //_______________________Contents/WebElements____________________________
    @FindBy(tagName = "h1")
    private WebElement heading;

    @FindBy(css = ".container-fluid.flex-target")
    private WebElement companyStatsSection;

    @FindBy(css = ".stats-area-54019:nth-child(1) div:nth-child(1)")
    private WebElement employeeCount;

    @FindBy(css = ".stats-area-54019:nth-child(1) div:nth-child(2)")
    private WebElement employeeStatText;

    @FindBy(css = ".stats-area-54019:nth-child(2) div:nth-child(1)")
    private WebElement locationCount;

    @FindBy(css = ".stats-area-54019:nth-child(2) div:nth-child(2)")
    private WebElement locationStatText;

    @FindBy(css = ".stats-area-54019:nth-child(3) div:nth-child(1)")
    private WebElement licenseCount;

    @FindBy(css = ".stats-area-54019:nth-child(3) div:nth-child(2)")
    private WebElement licenseStatText;

    @FindBy(css = ".stats-area-54019:nth-child(4) div:nth-child(1)")
    private WebElement regulatoryCount;

    @FindBy(css = ".stats-area-54019:nth-child(4) div:nth-child(2)")
    private WebElement regulatoryStatText;

    private HashMap<String, List<WebElement>> stats;

    public void setStats() {
        stats = new HashMap<>();
        stats.put("Employee stats", Arrays.asList(employeeCount, employeeStatText));
        stats.put("Location stats", Arrays.asList(locationCount, locationStatText));
        stats.put("Global stats", Arrays.asList(licenseCount, licenseStatText));
        stats.put("Regulated stats", Arrays.asList(regulatoryCount, regulatoryStatText));
    }


    //_______________________Actions/Methods____________________________

    public void verifyPageTitle(WebDriver driver) {
        waitForTitleMatch(driver, pageTitle);
    }

    public void verifyAboutUsPage(WebDriver driver) {
        Assert.assertTrue(waitForTextInElementEquals(driver, heading, "About Us"));
    }

    public void gotoStats(WebDriver driver) {
        jsScroll(driver, companyStatsSection);
        setStats();
    }

    public void verifyCompanyStats(WebDriver driver, String key, String statName, int statCount) {
        Assert.assertTrue(waitForTextInElementEquals(driver, stats.get(key).get(1), statName));
        int count = Integer.parseInt(stats.get(key).get(0).getAttribute("data-odometer-count"));
        Assert.assertEquals(statCount, count);
    }
}
