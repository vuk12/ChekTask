package com.driver;

import org.openqa.selenium.WebDriver;

public class ThreadLocalStorageDriver {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driver) {
        ThreadLocalStorageDriver.driver.set(driver);
    }

}