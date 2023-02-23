package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(className = "display-5")
    private WebElement headingText;
    @FindBy(id = "inputUsername")
    private WebElement usernameInput;
    @FindBy(id = "inputPassword")
    private WebElement passwordInput;
    @FindBy(id="login-button")
    private WebElement loginButton;
    @FindBy(id="signupLink")
    private WebElement signupLink;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getHeadingText(){
        return headingText.getText();
    }
    public void enterUserName(String username){
        usernameInput.sendKeys(username);
    }
    public void enterPassword(String password){
        passwordInput.sendKeys(password);
    }
    public void clickLoginButton(){
        loginButton.click();
    }
    public void clickSignupLink(){
        signupLink.click();
    }

    public WebElement getUsernameInput() {
        return usernameInput;
    }

    public WebElement getPasswordInput() {
        return passwordInput;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getSignupLink() {
        return signupLink;
    }
    public WebElement getHeadingTextElement(){
        return headingText;
    }
}
