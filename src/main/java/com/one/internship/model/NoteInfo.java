package com.one.internship.model;


public class NoteInfo {

    private int id;
    private String note;

    public CategoryInfo getCategory() {
        return category;
    }

    public void setCategory(CategoryInfo category) {
        this.category = category;
    }

    private CategoryInfo category;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
