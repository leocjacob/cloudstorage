package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginSignup implements LoginAndSignUpBehaviour{
    private LoginPage loginPage;
    private SignupPage signupPage;
    private HomePage homePage;
    String password;
    String firstName;
    String lastName;
    WebDriver driver;
    String baseUrl;
    WebDriverWait webDriverWait;

    public LoginSignup(WebDriver driver, String baseUrl , WebDriverWait webDriverWait) {
        this.baseUrl = baseUrl;
        this.webDriverWait=webDriverWait;
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
        this.signupPage = new SignupPage(driver);
        this.homePage = new HomePage(driver);
        this.password = "1234567890";
        this.firstName="leo";
        this.lastName="jacob";
    }
    @Override
    public void doLogin(String username){
        driver.get(baseUrl + "/login");
        webDriverWait.until(ExpectedConditions.titleContains("Login"));
        loginPage.enterUserName(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }

    @Override
    public void doSignup(String username) {
        driver.get(baseUrl + "/signup");
        webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));
        signupPage.enterfirstName(firstName);
        signupPage.enterLastName(lastName);
        signupPage.enterUserName(username);
        signupPage.enterPassword(password);
        signupPage.clickSignupButton();
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(signupPage.getErrorMessage()));
            Assertions.assertFalse(signupPage.getErrorMessage().getText().contains("There was an error signing you up. Please try again."));
            Assertions.assertFalse(signupPage.getErrorMessage().getText().contains("The username already exists."));

        }catch (Exception e){
            System.out.println("Sign up success.");
        }
    }
}
