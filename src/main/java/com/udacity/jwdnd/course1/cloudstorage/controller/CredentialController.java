package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.SecureRandom;
import java.util.Base64;

@Controller
@RequestMapping("/createUpdateCredential")
public class CredentialController {
    private CredentialService credentialService;
    private UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping
    public String createUpdate(Authentication  authentication, @RequestParam("credentialId") String credentialId, @RequestParam("url") String url, @RequestParam("username") String username, @RequestParam("password") String password, RedirectAttributes redirectAttributes){

        if(credentialId != ""){
            credentialService.updateCredential(credentialId,url,username,password,authentication);
            redirectAttributes.addFlashAttribute("success",true);
            redirectAttributes.addFlashAttribute("message","Credential Updated Successfully!");
        }else{
            credentialService.insertCredential(url,username,password,authentication);
            redirectAttributes.addFlashAttribute("success",true);
            redirectAttributes.addFlashAttribute("message","Credential Created Successfully!");
        }
        redirectAttributes.addFlashAttribute("activeTab","tab3");
        return"redirect:/home";
    }
}
