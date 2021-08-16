package com.one.internship.model;

import org.hibernate.validator.constraints.Length;

public class CreateCategoryRequest {

    @Length(min = 3, max = 20)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
