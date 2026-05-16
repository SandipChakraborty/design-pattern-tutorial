package com.design.pattern.adapter;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    List<Student> getStudents() {
        List<Student> students = new ArrayList<>();

        CollegeStudent collegeStudent = new CollegeStudent("Bob", "Marley", "bob.marley@mit.com");

        SchoolStudent schoolStudent = new SchoolStudent("Alice", "Hopper", "alice.hopper@academia.edu");

        students.add(collegeStudent);

        // This will not work as SchoolStudent does not implement Student interface
        // students.add(schoolStudent);

        // Here comes the adapter pattern into play
        // As SchoolStudentAdapter implements Student interface, it can be added to the list
        // SchoolStudentAdapter uses SchoolStudent object to create a similar object which implements Student interface
        students.add(new SchoolStudentAdapter(schoolStudent));


        return students;
    }
}
