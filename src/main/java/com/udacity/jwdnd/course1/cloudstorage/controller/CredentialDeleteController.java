package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/deleteCredential/{credentialId}")
public class CredentialDeleteController {
    private CredentialService credentialService;

    public CredentialDeleteController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @GetMapping
    public String deleteCredential(@PathVariable("credentialId") String credentialId, RedirectAttributes redirectAttributes){
        credentialService.deleteCredential(Integer.parseInt(credentialId));
        redirectAttributes.addFlashAttribute("success",true);
        redirectAttributes.addFlashAttribute("message","Successfully Deleted Note!");
        redirectAttributes.addFlashAttribute("activeTab","tab3");
        return "redirect:/home";
    }
}
