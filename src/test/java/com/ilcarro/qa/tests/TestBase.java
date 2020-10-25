package com.ilcarro.qa.tests;

import com.ilcarro.qa.framework.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

public class TestBase {

    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
Logger logger= LoggerFactory.getLogger(TestBase.class);
    @BeforeSuite
    public void setUp(){
        app.init();
        // login("aa@bb109.com", "1Aaaaaaaa");

    }

@BeforeMethod
public void startTest(){
        logger.info("Start test");
}

@AfterMethod
public void stopTest(){
        logger.info("Stop test");
}
    @AfterSuite(enabled = true)
    public void tearDown(){
        app.stop();
    }


}
