package com.design.pattern.adapter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CollegeStudent implements Student {
    private String firstName;
    private String lastName;
    private String email;
}
