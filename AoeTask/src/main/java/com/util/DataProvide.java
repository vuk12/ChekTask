package com.util;

import org.testng.annotations.DataProvider;

public class DataProvide {

    @DataProvider(name = "jobFilter-provider")
    public Object[][] jobFilterProvider() {
        return new Object[][]{
                {"frontend"},
//                {"tuturutu"},
//                {"office"},
        };
    }
}
