package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/upload")
public class FileUploadController {
    private UserService userService;
    private FileService fileService;

    public FileUploadController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }


    @PostMapping
    public String uploadFile(@RequestParam("fileUpload")MultipartFile multipartFile, Authentication authentication, RedirectAttributes  redirectAttributes){
        String userName = authentication.getName();
        Integer userID = userService.getUser(authentication.getName()).getUserid();
        String[] files = fileService.getFiles(userID);
        List<String> fileList = Arrays.asList(files);
        String fileName = multipartFile.getOriginalFilename();
        boolean fileNameFound = fileList.stream().anyMatch(s -> s.equals(fileName));
        if(fileNameFound){
            redirectAttributes.addFlashAttribute("message","File already exist!");
        }else{
            fileService.inserFile(multipartFile,userName);
            redirectAttributes.addFlashAttribute("success",true);
            redirectAttributes.addFlashAttribute("message","File Uploaded Successfully!");
        }
        redirectAttributes.addFlashAttribute("activeTab","tab1");
        return "redirect:/home";
    }
}
