package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private FileService fileService;
    private NoteService noteService;
    private CredentialService credentialService;

    private UserService userService;

    public HomeController(FileService fileService, NoteService noteService, CredentialService credentialService, UserService userService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.userService = userService;
    }


    @GetMapping
    public String getHome(Model model, Authentication authentication){
        Integer userID = userService.getUser(authentication.getName()).getUserid();
        model.addAttribute("files", this.fileService.getFiles(userID));
        model.addAttribute("notes", this.noteService.getNotes(userID));
        model.addAttribute("credentials", this.credentialService.getCredentials(userID));
        return "home";
    }
}
