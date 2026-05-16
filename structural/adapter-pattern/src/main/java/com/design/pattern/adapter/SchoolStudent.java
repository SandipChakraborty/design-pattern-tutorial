package com.design.pattern.adapter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString()
@NoArgsConstructor
@AllArgsConstructor
public class SchoolStudent {
    private String name;
    private String surname;
    private String emailAddress;
}
