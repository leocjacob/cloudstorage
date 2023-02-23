package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {
    @FindBy(id="logout-button")
    private WebElement logoutButton;
    @FindBy(id="nav-files-tab")
    private WebElement filesButton;
    @FindBy(id="nav-notes-tab")
    private WebElement notesButton;
    @FindBy(id="nav-credentials-tab")
    private WebElement credentialsButton;
    @FindBy(id="fileUpload")
    private WebElement fileuploadButton;
    @FindBy(id = "uploadButton")
    private WebElement uploadButton;
    @FindBy(id="add-note")
    private WebElement addNewNoteButton;
    @FindBy(id="note-title")
    private WebElement noteTitleInput;
    @FindBy(id="note-description")
    private WebElement noteDescriptionInput;
    @FindBy(id="submit-note")
    private WebElement submitnoteButton;
    @FindBy(id="add-new-cred")
    private WebElement addNewCredentialButton;
    @FindBy(id="credential-url")
    private WebElement credentialurlInput;
    @FindBy(id="credential-username")
    private WebElement credentialUsernameInput;
    @FindBy(id="credential-password")
    private WebElement credentialPasswordInput;
    @FindBy(id="credential-submit")
    private WebElement credentialSubmitButton;
    @FindBy(id="userTable")
    private WebElement userTable;
    @FindBy(id="fileTable")
    private WebElement fileTable;

    @FindBy(id="credentialTable")
    private WebElement credentialTable;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickLogoutButton(){
        logoutButton.click();
    }
    public void clickfilesButton(){
        filesButton.click();
    }
    public void clicknotesButton(){
        notesButton.click();
    }
    public void clickcredentialsButton(){
        credentialsButton.click();
    }
    public void clickfileuploadButton(){
        fileuploadButton.click();
    }
    public void clickuploadButton(){
        uploadButton.click();
    }
    public void clickaddNewNoteButton(){
        addNewNoteButton.click();
    }
    public void enternoteTitle(String title){
        noteTitleInput.clear();
        noteTitleInput.sendKeys(title);
    }
    public void enternoteDescription(String description){
        noteDescriptionInput.clear();
        noteDescriptionInput.sendKeys(description);
    }
    public void clicksubmitnoteButton(){
        submitnoteButton.click();
    }
    public void clickaddNewCredentialButton(){
        addNewCredentialButton.click();
    }
    public void entercredentialurl(String url){
        credentialurlInput.clear();
        credentialurlInput.sendKeys(url);
    }
    public void entercredentialUsername(String username){
        credentialUsernameInput.clear();
        credentialUsernameInput.sendKeys(username);
    }
    public void entercredentialPassword(String password){
        credentialPasswordInput.clear();
        credentialPasswordInput.sendKeys(password);
    }
    public void clickcredentialSubmitButton(){
        credentialSubmitButton.click();
    }

    public WebElement getLogoutButton() {
        return logoutButton;
    }

    public WebElement getFilesButton() {
        return filesButton;
    }

    public WebElement getNotesButton() {
        return notesButton;
    }

    public WebElement getCredentialsButton() {
        return credentialsButton;
    }

    public WebElement getFileuploadButton() {
        return fileuploadButton;
    }

    public WebElement getUploadButton() {
        return uploadButton;
    }

    public WebElement getAddNewNoteButton() {
        return addNewNoteButton;
    }

    public WebElement getNoteTitleInput() {
        return noteTitleInput;
    }

    public WebElement getNoteDescriptionInput() {
        return noteDescriptionInput;
    }

    public WebElement getSubmitnoteButton() {
        return submitnoteButton;
    }

    public WebElement getAddNewCredentialButton() {
        return addNewCredentialButton;
    }

    public WebElement getCredentialurlInput() {
        return credentialurlInput;
    }

    public WebElement getCredentialUsernameInput() {
        return credentialUsernameInput;
    }

    public WebElement getCredentialPasswordInput() {
        return credentialPasswordInput;
    }

    public WebElement getCredentialSubmitButton() {
        return credentialSubmitButton;
    }
    public WebElement getUserTable() {
        return userTable;
    }

    public WebElement getFileTable() {
        return fileTable;
    }

    public WebElement getCredentialTable() {
        return credentialTable;
    }

    public List<WebElement> getTableRowColumns(int row_num ,WebElement table){
        row_num +=1;
        List<WebElement> tablerows = table.findElements(By.tagName("tr"));
        if(tablerows.size() == row_num){
            return null;
        }else {
            WebElement row = tablerows.get(row_num);
            return row.findElements(By.tagName("td"));
        }
    }
}
