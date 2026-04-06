package com.design.pattern.facade;

public class SecurityCodeCheck {
    public boolean isValidCode(int code) {
        // Let's say if the code is 4 digit then its valid
        // Sample 1234
        return (code + "").length() == 4;
    }
}
