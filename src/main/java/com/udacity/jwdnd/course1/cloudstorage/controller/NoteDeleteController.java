package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/deleteNote/{noteId}")
public class NoteDeleteController {
    private NoteService noteService;

    public NoteDeleteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public String deleteNote(RedirectAttributes redirectAttributes, @PathVariable("noteId")String noteId){
        noteService.deleteNote(Integer.parseInt(noteId));
        redirectAttributes.addFlashAttribute("success",true);
        redirectAttributes.addFlashAttribute("message","Successfully Deleted Note!");
        redirectAttributes.addFlashAttribute("activeTab","tab2");
        return "redirect:/home";
    }
}
