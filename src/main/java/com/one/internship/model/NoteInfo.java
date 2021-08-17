package com.one.internship.model;


import javax.validation.constraints.Size;

public class NoteInfo {

    private Integer id;

    @Size(min = 1, max = 4000)
    private String note;

    @Size(min = 2, max = 20)
    private String categoryName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
