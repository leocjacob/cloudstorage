package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class NoteTests extends Configuration{
    LoginAndSignUpBehaviour loginAndSignUpBehaviour;
    HomePage homePage;
    @BeforeEach
    public void setup(){
        homePage = new HomePage(driver);
    }
    @Test
    public void createNote(){
        createNote("leocj-note");
    }

    public void createNote(String username){
        String noteTitle = "my note title";
        String notedescription = "my description";
        loginAndSignUpBehaviour = new LoginSignup(driver,baseUrl,webDriverWait);
        loginAndSignUpBehaviour.doSignup(username);
        loginAndSignUpBehaviour.doLogin(username);
        webDriverWait.until(ExpectedConditions.titleContains("Home"));
        homePage.clicknotesButton();
        webDriverWait.until(ExpectedConditions.visibilityOf(homePage.getAddNewNoteButton()));
        homePage.clickaddNewNoteButton();
        webDriverWait.until(ExpectedConditions.visibilityOf(homePage.getNoteTitleInput()));
        homePage.enternoteTitle(noteTitle);
        homePage.enternoteDescription(notedescription);
        homePage.clicksubmitnoteButton();
        WebElement messageElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-body")));
        Assertions.assertTrue(messageElement.getText().contains("Note Created Successfully!"));
        webDriverWait.until(ExpectedConditions.visibilityOf(homePage.getUserTable()));
        List<WebElement> tableData = homePage.getTableRowColumns(0, homePage.getUserTable());
        Assertions.assertTrue(tableData.get(1).getText().equals(noteTitle));
        Assertions.assertTrue(tableData.get(2).getText().equals(notedescription));
    }
    @Test
    public void editNote(){
        String noteTitle = "my new note title";
        String notedescription = "my new description";
        createNote("leocj-note-1");
        List<WebElement> tableData = homePage.getTableRowColumns(0, homePage.getUserTable());
        WebElement editButton = tableData.get(0).findElement(By.tagName("button"));
        editButton.click();
        webDriverWait.until(ExpectedConditions.visibilityOf(homePage.getNoteTitleInput()));
        homePage.enternoteTitle(noteTitle);
        homePage.enternoteDescription(notedescription);
        homePage.clicksubmitnoteButton();
        WebElement messageElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-body")));
        Assertions.assertTrue(messageElement.getText().contains("Note Updated Successfully!"));
        webDriverWait.until(ExpectedConditions.visibilityOf(homePage.getUserTable()));
        tableData = homePage.getTableRowColumns(0, homePage.getUserTable());
        Assertions.assertTrue(tableData.get(1).getText().equals(noteTitle));
        Assertions.assertTrue(tableData.get(2).getText().equals(notedescription));
    }
    @Test
    public void deleteNote(){
        createNote("leocj-note-2");
        List<WebElement> tableData = homePage.getTableRowColumns(0, homePage.getUserTable());
        WebElement deleteLink = tableData.get(0).findElement(By.tagName("a"));
        deleteLink.click();
        tableData = homePage.getTableRowColumns(0, homePage.getUserTable());
        Assertions.assertTrue(tableData == null);
    }

}
