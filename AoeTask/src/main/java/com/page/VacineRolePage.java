package com.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class VacineRolePage extends BasePage {
    @FindBy(css = ".ce-bodytext")
    List<WebElement> jobOfferings;

    @FindBy(id = "c9186")
    WebElement applyNow;

    public VacineRolePage(WebDriver driver) throws InterruptedException {
        super(driver);
        PageFactory.initElements(driver, this);
        scrollToApplyNow();
    }

    @Step("Get list of job Offerings text")
    public List<String> listOfJobOfferingContent() {
        return jobOfferings.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Scroll to Apply now element")
    public VacineRolePage scrollToApplyNow() throws InterruptedException {
        scrollToYPosition(applyNow, 40);
        return this;
    }

    @Step("Verify all sections of job offerings contains any text")
    public void verifyJobOfferingTextNonEmpty() {
        Assert.assertTrue(listOfJobOfferingContent().stream().allMatch(Predicate.not(String::isBlank))
                , "Some of text sections in job Offerings are not populated");
    }
}
