package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/createUpdateNote")
public class NoteController {

    private NoteService noteService;
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @PostMapping
    public String createnote(Authentication authentication, @RequestParam("noteId") String noteId,@RequestParam("noteTitle") String noteTitle , @RequestParam("noteDescription") String noteDescription, RedirectAttributes redirectAttributes){
        if(noteId==""){
            noteService.insertNote(noteTitle,noteDescription,authentication.getName());
            redirectAttributes.addFlashAttribute("success",true);
            redirectAttributes.addFlashAttribute("message","Note Created Successfully!");
        }else{
            noteService.updateNote(Integer.parseInt(noteId),noteTitle,noteDescription);
            redirectAttributes.addFlashAttribute("success",true);
            redirectAttributes.addFlashAttribute("message","Note Updated Successfully!");
        }
        redirectAttributes.addFlashAttribute("activeTab","tab2");
        return "redirect:/home";
    }

}
