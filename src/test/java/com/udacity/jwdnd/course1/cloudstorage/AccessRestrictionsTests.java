package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccessRestrictionsTests extends Configuration{
    private HomePage homePage;
    private LoginAndSignUpBehaviour loginAndSignUpBehaviour;
    @BeforeEach
    public void setup(){
        homePage = new HomePage(driver);
    }

    @Test
    public void unauthorizedAccess(){
        driver.get(baseUrl + "/login");
        webDriverWait.until(ExpectedConditions.titleContains("Login"));
        driver.get(baseUrl + "/signup");
        webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));
        try {
            driver.get(baseUrl + "/home");
            webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));
        }catch(TimeoutException e){
            System.out.println("Unauthorized user cannot access home page.");
        }
    }
    @Test
    public void authorizedAccess(){
        String username = "leocj-auth";
        loginAndSignUpBehaviour = new LoginSignup(driver,baseUrl,webDriverWait);
        loginAndSignUpBehaviour.doSignup(username);
        loginAndSignUpBehaviour.doLogin(username);
        webDriverWait.until(ExpectedConditions.titleContains("Home"));
        System.out.println("Accessed home page");
        homePage.clickLogoutButton();
        System.out.println("Logged Out");
        try {
            driver.get(baseUrl + "/home");
            webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));
        }catch(TimeoutException e){
            System.out.println("Unauthorized user cannot access home page.");
        }
    }
}
