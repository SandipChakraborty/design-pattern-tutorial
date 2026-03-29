package com.design.pattern.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/adapter")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    List<Student> getStudents() {
        return  studentService.getStudents();
    }
}
