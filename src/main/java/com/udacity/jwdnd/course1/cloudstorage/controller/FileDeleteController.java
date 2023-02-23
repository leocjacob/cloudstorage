package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/delete/{fileName}")
public class FileDeleteController {
    private FileService fileService;

    public FileDeleteController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public String handleFileDelete(@PathVariable String fileName,RedirectAttributes redirectAttributes){
        fileService.deleteFile(fileName);
        redirectAttributes.addFlashAttribute("success",true);
        redirectAttributes.addFlashAttribute("message","File Deleted Successfully!");
        redirectAttributes.addFlashAttribute("activeTab","tab1");
        return "redirect:/home";
    }
}
