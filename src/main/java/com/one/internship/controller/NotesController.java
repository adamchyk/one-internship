package com.one.internship.controller;

import com.one.internship.entity.Category;
import com.one.internship.entity.Note;
import com.one.internship.entity.User;
import com.one.internship.model.CategoryInfo;
import com.one.internship.model.MessageResponse;
import com.one.internship.model.NoteInfo;
import com.one.internship.repository.CategoryRepository;
import com.one.internship.repository.NoteRepository;
import com.one.internship.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    @Autowired
    private EntityManager em;

    @GetMapping("/notes")
    public List<NoteInfo> getNotes(@RequestParam(required = false) Integer categoryId,
                                   @RequestParam(required = false) String noteContains,
                                   Principal principal) {
        List<NoteInfo> noteInfos = new ArrayList<>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Note> query = cb.createQuery(Note.class);
        Root<Note> noteRoot = query.from(Note.class);
        List<Predicate> predicates = new ArrayList<Predicate>();

        // filter 1
        User currentUser = userRepository.findByUsername(principal.getName()).get();
        predicates.add(cb.equal(noteRoot.get("owner").get("id"), currentUser.getId()));

        // filter 2
        if (categoryId != null) {
            predicates.add(cb.equal(noteRoot.get("category").get("categoryId"), categoryId));
        }

        // filter 3
        if (noteContains != null && !noteContains.isBlank()) {
            predicates.add(cb.and(cb.like(noteRoot.get("note"), '%' + noteContains + '%')));
        }
        query.select(noteRoot).where(predicates.toArray(new Predicate[0]));

        // add filtration by noteContains
        List<Note> noteList = em.createQuery(query).getResultList();

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
        if (!n.getOwner().getId().equals(u.getId())) {
            return ResponseEntity.ok(new MessageResponse("This is not your note"));
        }
        noteRepository.deleteById(noteId);
        return ResponseEntity.ok(new MessageResponse("Note deleted"));
    }

}
