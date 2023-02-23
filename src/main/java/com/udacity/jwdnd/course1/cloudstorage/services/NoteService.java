package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;


@Service
public class NoteService {

    private NoteMapper noteMapper;
    private UserService userService;

    private Note note;

    public NoteService(NoteMapper noteMapper, UserService userService) {
        this.noteMapper = noteMapper;
        this.userService = userService;
    }


    public Note getNote(Integer noteId){
        return noteMapper.getNote(noteId);
    }

    public Note[] getNotes(Integer userId){
        return noteMapper.getNotes(userId);
    }
    public Integer insertNote(String title, String description, String userName){
        Integer userId = userService.getUser(userName).getUserid();
        note = new Note();
        note.setNotetitle(title);
        note.setNotedescription(description);
        note.setUserid(userId);
        return noteMapper.insertNote(note);
    }
    public void deleteNote(Integer noteId){
        noteMapper.deleteNote(noteId);
    }
    public void updateNote(Integer noteId, String title, String description){
        noteMapper.updateNote(noteId,title,description);
    }

}
