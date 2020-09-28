package com.superpharm.test1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

        wd.manage().deleteAllCookies();

        CommonMethods.delay(2000);
    }

    @Test
    public void superpharmOnline(){

        wd.navigate().to("https://shop.super-pharm.co.il/");
        CommonMethods.delay(10000);

        wd.findElement(By.xpath("//ul[@id='header-top-nav']//a[@href='/termsAndConditions']")).click();
    //    wd.findElement(By.cssSelector("div[id='navbar'] a[href='/termsAndConditions']")).click();

    /*User login*/
        String userName = DataSets.userDetails("Positive","email");
        String passWord = DataSets.userDetails("Positive","password");
        UserProfileMethods.logIn(wd, userName, passWord);


    /*Search and Add to cart*/
        int itemNumberForCart = CommonMethods.getRandomValue(1,3); //randomly chosen number of items to be added to cart
        String itemName;
        int itemPosition;

        System.out.println(itemNumberForCart + " items will be added to the cart");

        for(int i = 1; i <= itemNumberForCart; i++){
            itemName = DataSets.itemNameForSearch();
            itemPosition = CommonMethods.searchItemPosition(wd, itemName);
            CommonMethods.addItemToCart(wd, Integer.toString(itemPosition));
        }

    /*Open the cart and read items total amount and price*/
        UserProfileMethods.openCart(wd);
        CommonMethods.delay(500);

    /*Remove all the items from the cart*/
        CommonMethods.emptyCart(wd);
        CommonMethods.delay(1000);

    /*User logout*/
        UserProfileMethods.logOut(wd);
        CommonMethods.delay(2000);

    }

    @AfterMethod(enabled = true)
    public void tearDown(){
        wd.quit();
    }



}
