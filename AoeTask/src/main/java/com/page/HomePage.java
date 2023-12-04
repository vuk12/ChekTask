package com.page;

import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    @FindBy(id = "onetrust-accept-btn-handler")
    WebElement cookie;

    @FindBy(css = "a[data-qa='nav_main_6']")
    WebElement careersLink;

    @FindBy(css = "a[data-qa='nav_main_6_4']")
    WebElement vacanciesDDLink;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        acceptCookie();
    }

    @Step("Click careers link")
    public HomePage acceptCookie() {
        try {
            waitUntilElementVisibleAndClick(cookie,4);
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
        }
        return this;
    }

    @Step("Mouse hover over careers link")
    public HomePage hoverOverCareersLink() {
        waitUntilElementVisible(careersLink,3);
        mouseHoverOnElement(careersLink);
        return this;
    }

    @Step("Click vacancies drop down link")
    public VacanciesPage clickVacanciesDDLink() {
        hoverOverCareersLink();
        waitUntilElementVisibleAndClick(vacanciesDDLink, 3);
        return new VacanciesPage(driver);
    }

}
