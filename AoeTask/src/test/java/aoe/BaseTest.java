package aoe;

import com.driver.SingleDriverCreation;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static com.driver.ThreadLocalStorageDriver.getDriver;
import static com.driver.ThreadLocalStorageDriver.setDriver;
import static com.util.Constants.BASE_URL;

public class BaseTest {

    @BeforeMethod
    public void before() throws IOException {
        setDriver(SingleDriverCreation.getInstance().getDriver());
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();
        getDriver().get(BASE_URL);
    }

    @AfterMethod
    public void after() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }

    public void verifyAllElContains(List<String> listOfStrings, String keyWord) {
        Assert.assertTrue(listOfStrings.stream().allMatch(x -> x.toLowerCase().contains(keyWord)),
                "Some of jobs do not contain keyword on which the are filtrated");
    }

}
