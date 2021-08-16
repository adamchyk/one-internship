package com.one.internship.model;


import org.hibernate.validator.constraints.Length;

public class NoteInfo {

    private int id;

    @Length(min = 1, max = 4000)
    private String note;

    @Length(min = 2, max = 20)
    private String categoryName;


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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
