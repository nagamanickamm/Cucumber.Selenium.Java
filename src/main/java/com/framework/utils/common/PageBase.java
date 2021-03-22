package com.framework.utils.common;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageBase {
    public void waitForTitleMatch(WebDriver driver, String title) {
        new WebDriverWait(driver, 10L).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().equalsIgnoreCase(title);
            }
        });
    }

    public void waitForElementDisplayed(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return element.isDisplayed();
            }
        });
    }

    public void waitForElementNotDisplayed(WebDriver driver, String css) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                return (executor.executeScript("return document.querySelector('" + css + "')") == null);
            }
        });
    }

    public void waitForElementClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void jsClick(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void jsScroll(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
        waitForElementClickable(driver, element);
        waitForElementDisplayed(driver, element);
    }

    public void verifyText(WebDriver driver, String expectedText, WebElement element) {
        Assert.assertEquals(expectedText, element.getText().trim());
    }

    public void delay(long milliSecond) {
        try {
            Thread.sleep(milliSecond);
        } catch (Exception e) {
        }
    }

    public boolean waitForJStoLoad(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    JavascriptExecutor executor = (JavascriptExecutor) driver;
                    return ((Long) executor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                return executor.executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    public boolean waitForTextInElementEquals(WebDriver driver, WebElement elm, String text) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        ExpectedCondition<Boolean> textMatch = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    String elementText = elm.getText();
                    return elementText.equals(text);
                } catch (StaleElementReferenceException var3) {
                    return null;
                }
            }

            public String toString() {
                return String.format("text ('%s') to be present in element %s", text, elm);
            }
        };
        return wait.until(textMatch);
    }

    public void AnimationsReady(WebDriver driver) {
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                return executor.executeScript("return $(\"animated\").length.toString()")
                        .toString().equals("0");
            }
        };
    }
}
