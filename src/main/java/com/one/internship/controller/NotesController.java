package com.one.internship.controller;

import com.one.internship.model.Note;
import com.one.internship.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class NotesController {

    List<Note> notesList = new ArrayList<>();

    @GetMapping("/notes")
    public List<Note> getNotes() {
        return notesList;
    }

    @PostMapping("/notes")
    public void addNotes(@RequestBody Note note){
        note.setNoteId((long) (notesList.size() + 1));
        notesList.add(note);
    }

    @DeleteMapping("/notes/{id}")
    public void deleteUser(@PathVariable("id") Long noteId) {
        Iterator<Note> notesList = getNotes().listIterator();
        while (notesList.hasNext()) {
            Note note = notesList.next();
            if (note.getNoteId().equals(noteId)) {
                notesList.remove();
                break;
            }
        }
    }

}
