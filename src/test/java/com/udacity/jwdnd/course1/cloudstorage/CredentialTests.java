package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CredentialTests extends Configuration{
    private HomePage homePage;
    private LoginAndSignUpBehaviour loginAndSignUpBehaviour;

    @BeforeEach
    public void setup(){
        this.homePage = new HomePage(driver);
    }
    @Test
    public void createCredential(){
        createCredential("leocj-cred");
    }
    public void createCredential(String username){
        String url = "www.google.com";
        String cred_username = "leocj";
        String password = "1234567890";
        loginAndSignUpBehaviour = new LoginSignup(driver,baseUrl,webDriverWait);
        loginAndSignUpBehaviour.doSignup(username);
        loginAndSignUpBehaviour.doLogin(username);
        webDriverWait.until(ExpectedConditions.titleContains("Home"));
        homePage.clickcredentialsButton();
        webDriverWait.until(ExpectedConditions.visibilityOf(homePage.getAddNewCredentialButton()));
        homePage.clickaddNewCredentialButton();
        webDriverWait.until(ExpectedConditions.visibilityOf(homePage.getCredentialurlInput()));
        homePage.entercredentialurl(url);
        homePage.entercredentialUsername(cred_username);
        homePage.entercredentialPassword(password);
        homePage.clickcredentialSubmitButton();
        WebElement messageElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-body")));
        Assertions.assertTrue(messageElement.getText().contains("Credential Created Successfully!"));
        webDriverWait.until(ExpectedConditions.visibilityOf(homePage.getCredentialTable()));
        List<WebElement> tableData = homePage.getTableRowColumns(0, homePage.getCredentialTable());
        Assertions.assertTrue(tableData.get(1).getText().equals(url));
        Assertions.assertTrue(tableData.get(2).getText().equals(cred_username));
        Assertions.assertFalse(tableData.get(3).getText().equals(password));
    }
    @Test
    public void editCredential(){
        String url = "www.bing.com";
        String username = "leocjacob";
        String password = "qwerty";
        String old_password = "1234567890";
        createCredential("leocj-cred-1");
        List<WebElement> tableData = homePage.getTableRowColumns(0, homePage.getCredentialTable());
        WebElement editButton = tableData.get(0).findElement(By.tagName("button"));
        editButton.click();
        webDriverWait.until(ExpectedConditions.visibilityOf(homePage.getCredentialurlInput()));
        Assertions.assertTrue(homePage.getCredentialPasswordInput().getAttribute("value").equals(old_password));
        homePage.entercredentialurl(url);
        homePage.entercredentialUsername(username);
        homePage.entercredentialPassword(password);
        homePage.clickcredentialSubmitButton();
        WebElement messageElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-body")));
        Assertions.assertTrue(messageElement.getText().contains("Credential Updated Successfully!"));
        webDriverWait.until(ExpectedConditions.visibilityOf(homePage.getCredentialTable()));
        tableData = homePage.getTableRowColumns(0, homePage.getCredentialTable());
        Assertions.assertTrue(tableData.get(1).getText().equals(url));
        Assertions.assertTrue(tableData.get(2).getText().equals(username));
        Assertions.assertFalse(tableData.get(3).getText().equals(password));
    }
    @Test
    public void deleteCredential(){
        createCredential("leocj-cred-2");
        List<WebElement> tableData = homePage.getTableRowColumns(0, homePage.getCredentialTable());
        WebElement deleteLink = tableData.get(0).findElement(By.tagName("a"));
        deleteLink.click();
        tableData = homePage.getTableRowColumns(0, homePage.getCredentialTable());
        Assertions.assertTrue(tableData == null);
    }
}
