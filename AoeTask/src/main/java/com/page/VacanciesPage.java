package com.page;

import com.util.CustomException;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class VacanciesPage extends BasePage {

    @FindBy(id = "filter-keyword")
    WebElement tBoxFilterKeyword;

    @FindBy(xpath = "//caption[text()='AOE GmbH ']/ancestor::table" +
            "//tr[contains(@class,'filter-list-item') and not(contains(@style, 'none'))]/td/a")
    List<WebElement> jobList;

    public VacanciesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Type text in filter keyword {0}")
    public VacanciesPage filterByKeyword(String keyWord) {
        tBoxFilterKeyword.sendKeys(keyWord);
        return this;
    }

    @Step("Click on first job")
    public VacineRolePage getFirstDisplayedJob() throws InterruptedException, CustomException {
        if (!jobList.isEmpty() && jobList.size() > 0) {
            scrollToYPosition(jobList.get(0), 15);
            waitUntilElementClickableAndClick(jobList.get(0), 10);
            return new VacineRolePage(driver);
        }
        else {
            throw new CustomException("Filtrated job list is empty");
        }
    }

    @Step("Get text of all displayed jobs")
    public List<String> listOfJobs() {
        return jobList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

}
