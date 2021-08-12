package com.one.internship.controller;

import com.one.internship.entity.Category;
import com.one.internship.entity.Note;
import com.one.internship.entity.User;
import com.one.internship.model.NoteInfo;
import com.one.internship.repository.CategoryRepository;
import com.one.internship.repository.NoteRepository;
import com.one.internship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NotesController {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/notes")
    public List<NoteInfo> getNotes() {
        List<NoteInfo> noteInfos = new ArrayList<>();
        List<Note> noteList = noteRepository.findAll();
        for (int i = 0; i < noteList.size(); i++){
            NoteInfo noteInfo = new NoteInfo();
            Note n = noteList.get(i);
            noteInfo.setId(n.getNoteId());
            noteInfo.setNote(n.getNote());
            noteInfo.setCategoryId(n.getCategory().getCategoryId());
            noteInfo.setOwnerId(n.getOwner().getId());
            noteInfos.add(noteInfo);
        }
        return noteInfos;
    }

    @PostMapping("/notes")
    public void addNotes(@RequestBody NoteInfo req) {
        Note newNote = new Note();
        newNote.setNote(req.getNote());
        User u = userRepository.findById(req.getOwnerId()).get();
        newNote.setOwner(u);
        Category c = categoryRepository.findById(req.getCategoryId()).get();
        newNote.setCategory(c);
        noteRepository.save(newNote);
    }

    @DeleteMapping("/notes/{id}")
    public void deleteNotes(@PathVariable("id") Integer noteId) {
        noteRepository.deleteById(noteId);
    }

}
