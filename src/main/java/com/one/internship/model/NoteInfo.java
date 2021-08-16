package com.one.internship.model;


import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotNull;
public class NoteInfo {

    private int id;

    @NotNull
    @Length(min = 1, max = 4000)
    private String note;

    @NotNull
    private Integer categoryId;


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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return getName();
    }
}
