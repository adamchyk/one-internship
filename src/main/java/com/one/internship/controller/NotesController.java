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

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
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
                                   @RequestParam(required = false) Integer page,
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
        query.select(noteRoot)
                .where(predicates.toArray(new Predicate[0]));

        int pageSize = 5;
        int offset = (page != null) ? pageSize * (page - 1) : 0;

        List<Note> noteList = em.createQuery(query)
                .setFirstResult(offset)
                .setMaxResults(pageSize)
                .getResultList();

        for (int i = 0; i < noteList.size(); i++) {
            NoteInfo noteInfo = new NoteInfo();
            Note n = noteList.get(i);
            noteInfo.setId(n.getNoteId());
            noteInfo.setNote(n.getNote());
            noteInfo.setCategoryName(n.getCategory().getName());
            noteInfos.add(noteInfo);
        }
        return noteInfos;
    }


    @PostMapping("/notes")
    public ResponseEntity<MessageResponse> addNotes(@Valid @RequestBody NoteInfo req, Principal principal) {
        User u = userRepository.findByUsername(principal.getName()).get();

        Note newNote = new Note();
        if (req.getId() != null) {
            newNote = noteRepository.findById(req.getId()).get();
            if (!newNote.getOwner().getId().equals(u.getId())) {
                return ResponseEntity.badRequest().body(new MessageResponse("This is not your note."));
            }
        }

        newNote.setNote(req.getNote());
        newNote.setOwner(u);

        Category c = categoryRepository.findByOwnerIdAndName(u.getId(), req.getCategoryName());
        if (c == null) {
            c = new Category();
            c.setOwner(u);
            c.setName(req.getCategoryName());
            c = categoryRepository.save(c);
        } else if (!c.getOwner().getId().equals(u.getId())) {
            return ResponseEntity.badRequest().body(new MessageResponse("This is not your category"));
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
            return ResponseEntity.badRequest().body(new MessageResponse("This is not your note"));
        }
        noteRepository.deleteById(noteId);

        int count = noteRepository.countAllByOwnerIdAndCategoryCategoryId(u.getId(), n.getCategory().getCategoryId());
        if (count == 0) {
            categoryRepository.deleteById(n.getCategory().getCategoryId());
        }

        return ResponseEntity.ok(new MessageResponse("Note deleted"));
    }

}
