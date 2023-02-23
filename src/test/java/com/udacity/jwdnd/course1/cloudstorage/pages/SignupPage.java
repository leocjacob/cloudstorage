package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    @FindBy(id="login-link")
    private WebElement loginLink;
    @FindBy(id="page-heading")
    private WebElement pageHeadingText;
    @FindBy(id="inputFirstName")
    private WebElement firstNameInput;
    @FindBy(id="inputLastName")
    private WebElement lastNameInput;
    @FindBy(id="inputUsername")
    private WebElement userNameInput;
    @FindBy(id="inputPassword")
    private WebElement passwordInput;
    @FindBy(id="buttonSignUp")
    private WebElement signupButton;



    @FindBy(id="error-message")
    private WebElement errorMessage;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickLoginLink(){
        loginLink.click();
    }
    public String getHeadingText(){
        return pageHeadingText.getText();
    }
    public void enterfirstName(String firstName){
        firstNameInput.sendKeys(firstName);
    }
    public void enterLastName(String lastName){
        lastNameInput.sendKeys(lastName);
    }
    public void enterUserName(String userName){
        userNameInput.sendKeys(userName);
    }
    public void enterPassword(String password){
        passwordInput.sendKeys(password);
    }
    public void clickSignupButton(){
        signupButton.click();
    }
    public WebElement getErrorMessage() {
        return errorMessage;
    }

}
