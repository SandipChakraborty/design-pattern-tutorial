package com.design.pattern.adapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SchoolStudentAdapter implements Student {

    private final SchoolStudent student;

    @Override
    public String getFirstName() {
        return student.getName();
    }

    @Override
    public String getLastName() {
        return student.getSurname();
    }

    @Override
    public String getEmail() {
        return student.getEmailAddress();
    }

    public String toString(){
        return student.toString();
    }
}
