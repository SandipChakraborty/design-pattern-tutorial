package com.design.pattern.singleton;

import lombok.Getter;

public class MySingleTonClass {
    @Getter
    public static MySingleTonClass instance = new MySingleTonClass();
    private MySingleTonClass() {}
}
