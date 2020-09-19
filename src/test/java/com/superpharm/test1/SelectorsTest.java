package com.superpharm.test1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SelectorsTest {
    WebDriver wd;


    @BeforeMethod
    public void setUp(){
        wd = new ChromeDriver();
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        wd.navigate().to("https://shop.super-pharm.co.il/");
        wd.manage().deleteAllCookies();

        CommonMethods.delay(2000);
    }

    @Test
    public void superpharmOnline(){
        String userName = "sher130820@gmail.com";
        String passWord = "J20i12s13";

        UserProfileMethods.logIn(wd, userName, passWord);

        CommonMethods.delay(2000);

        UserProfileMethods.logOut(wd);

        CommonMethods.delay(2000);

    }

    @AfterMethod(enabled = false)
    public void tearDown(){
        wd.quit();
    }



}
