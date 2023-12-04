package aoe;

import com.listener.CustomTestNgListener;
import com.page.HomePage;
import com.page.VacanciesPage;
import com.util.CustomException;
import com.util.DataProvide;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.driver.ThreadLocalStorageDriver.getDriver;

@Listeners(CustomTestNgListener.class)
public class VacanciesTest extends BaseTest {

    private HomePage homePage;

    private VacanciesPage vacanciesPage;

    @BeforeMethod
    public void setPages() {
        homePage = new HomePage(getDriver());
        vacanciesPage = new VacanciesPage(getDriver());
    }

    @Test(dataProvider = "jobFilter-provider", dataProviderClass = DataProvide.class)
    @Description("Verify desired keyword is displayed in all filtrated jobs and" +
            "that first displayed job offering contains text for every section")
    public void filterJobsAndVerifyTextPresence(String keyWord) throws InterruptedException, CustomException {
        verifyAllElContains(
                homePage
                        .clickVacanciesDDLink()
                        .filterByKeyword(keyWord)
                        .listOfJobs(), keyWord);

        vacanciesPage
                .getFirstDisplayedJob()
                .verifyJobOfferingTextNonEmpty();
    }

}