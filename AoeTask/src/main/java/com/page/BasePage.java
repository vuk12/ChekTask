package com.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public static WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }

    public void waitUntilElementVisible(WebElement element, int timeOutSeconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilElementVisibleAndClick(WebElement element, int timeOutSeconds) {
        waitUntilElementVisible(element, timeOutSeconds);
        element.click();
    }

    public void waitUntilElementNotVisible(WebElement element, int timeOutSeconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutSeconds));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitUntilElementClickable(WebElement element, int timeOutSeconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilElementClickableAndClick(WebElement element, int timeOutSeconds) {
        waitUntilElementClickable(element, timeOutSeconds);
        element.click();
    }

    protected void scrollToElement(WebElement elem) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            js.executeScript("arguments[0].scrollIntoView();", elem);
        } catch (Exception e) {
            e.printStackTrace();
            int y = elem.getLocation().getY();
            js.executeScript("window.scrollBy(0," + y + ")", "");
        }
    }

    protected void scrollToYPosition(WebElement elem, int yMargin) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            int y = elem.getLocation().getY() + yMargin;
            js.executeScript("window.scrollBy(0," + y + ")", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Not best solution but wont work without it, and do not want hardcoded coordinates
        Thread.sleep(300);
    }

    protected void mouseHoverOnElement(WebElement elem) {
        Actions actions = new Actions(driver);
        actions.moveToElement(elem).build().perform();
    }

}
