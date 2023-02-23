package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/decrypt-password")
public class DecryptCredentialController {
    private CredentialService credentialService;
    private EncryptionService encryptionService;

    public DecryptCredentialController(CredentialService credentialService, EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping
    @ResponseBody
    public String getCredential(@RequestParam("credentialId") String credentialId){
        Integer credentialID = Integer.parseInt(credentialId);
        Credential credential = credentialService.getCredential(credentialID);
        return encryptionService.decryptValue(credential.getPassword(),credential.getKey());
    }
}
