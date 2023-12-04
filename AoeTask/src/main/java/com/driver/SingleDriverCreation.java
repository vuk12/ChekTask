package com.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Supplier;

public class SingleDriverCreation {
    private static final SingleDriverCreation instance = new SingleDriverCreation();
    public static Properties prop;
    private final Map<String, Supplier<WebDriver>> driverMap = new HashMap<>();
    private final Supplier<WebDriver> chrome = () -> WebDriverManager.chromedriver().create();
    private final Supplier<WebDriver> firefox = () -> WebDriverManager.firefoxdriver().create();
    private final Supplier<WebDriver> edge = () -> WebDriverManager.edgedriver().create();

    {
        driverMap.put("chrome", chrome);
        driverMap.put("firefox", firefox);
        driverMap.put("edge", edge);
    }

    public static SingleDriverCreation getInstance() {
        return instance;
    }

    public String getBrowserName() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "\\src\\main\\resources\\app.properties");
        prop.load(fis);

        return prop.getProperty("browser");
    }

    public synchronized WebDriver getDriver() throws IOException {
        return driverMap.get(getBrowserName()).get();
    }

}
