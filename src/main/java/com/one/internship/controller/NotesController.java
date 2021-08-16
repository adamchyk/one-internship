package com.one.internship.controller;

import com.one.internship.entity.Category;
import com.one.internship.entity.Note;
import com.one.internship.entity.User;
import com.one.internship.model.MessageResponse;
import com.one.internship.model.NoteInfo;
import com.one.internship.repository.CategoryRepository;
import com.one.internship.repository.NoteRepository;
import com.one.internship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    public List<NoteInfo> getNotes(@RequestParam(required = false) Integer categoryId,
                                   @RequestParam(required = false) String noteContains,
                                   Principal principal) {
        List<NoteInfo> noteInfos = new ArrayList<>();
        List<Note> noteList = noteRepository.findAll();
        for (int i = 0; i < noteList.size(); i++) {
            NoteInfo noteInfo = new NoteInfo();
            Note n = noteList.get(i);
            noteInfo.setId(n.getNoteId());
            noteInfo.setNote(n.getNote());
            noteInfo.setCategoryId(n.getCategory().getCategoryId());
            noteInfos.add(noteInfo);
        }
        return noteInfos;
    }

    @PostMapping("/notes")
    public ResponseEntity<MessageResponse> addNotes(@RequestBody NoteInfo req, Principal principal) {
        Note newNote = new Note();
        newNote.setNote(req.getNote());
        User u = userRepository.findByUsername(principal.getName()).get();
        newNote.setOwner(u);

        Category c = categoryRepository.findById(req.getCategoryId()).get();
        if (!c.getOwner().getId().equals(u.getId())) {
            return ResponseEntity.ok(new MessageResponse("This is not your category"));
        }

        newNote.setCategory(c);
        noteRepository.save(newNote);
        return ResponseEntity.ok(new MessageResponse("Note added"));

    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<MessageResponse> deleteNotes(@PathVariable("id") Integer noteId, Principal principal) {
        Note n = noteRepository.findById(noteId).get();
        User u = userRepository.findByUsername(principal.getName()).get();
        if (!n.getOwner().getId().equals(u.getId())){
            return ResponseEntity.ok(new MessageResponse("This is not your note"));
        }
        noteRepository.deleteById(noteId);
        return ResponseEntity.ok(new MessageResponse("Note deleted"));
    }

}
