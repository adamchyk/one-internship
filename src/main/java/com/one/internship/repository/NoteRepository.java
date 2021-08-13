package com.one.internship.repository;

import com.one.internship.entity.Note;
import com.one.internship.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note>findByNoteContainingIgnoreCase(String note);
}
