package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class CredentialService {
    private CredentialMapper credentialMapper;
    private UserService userService;
    private EncryptionService encryptionService;
    private Credential credential;

    public CredentialService(CredentialMapper credentialMapper, UserService userService, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }


    public Credential getCredential(Integer credentialid){
        return credentialMapper.getCredential(credentialid);
    }
    public Credential[] getCredentials(Integer userid){
        return credentialMapper.getCredentials(userid);
    }
    public int insertCredential(String url, String username, String password, Authentication authentication){
        String encodedKey = getEncodedKey();
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);
        Integer userID = userService.getUser(authentication.getName()).getUserid();
        credential = new Credential();
        credential.setUrl(url);
        credential.setUsername(username);
        credential.setPassword(encryptedPassword);
        credential.setUserid(userID);
        credential.setKey(encodedKey);
        return credentialMapper.insertCredential(credential);
    }
    public void deleteCredential(Integer credentialid){
        credentialMapper.deleteCredential(credentialid);
    }
    public void updateCredential(String credentialid,String url, String username, String password, Authentication authentication){
        String encodedKey = getEncodedKey();
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);
        Integer userID = userService.getUser(authentication.getName()).getUserid();
        credential = new Credential();
        credential.setCredentialid(Integer.parseInt(credentialid));
        credential.setUrl(url);
        credential.setUsername(username);
        credential.setPassword(encryptedPassword);
        credential.setUserid(userID);
        credential.setKey(encodedKey);
        credentialMapper.updateCredential(credential);
    }
    private String getEncodedKey(){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }

}
