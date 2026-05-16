package com.design.pattern.singleton.controller;

import com.design.pattern.singleton.MySingleTonClass;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/singleton")
@RequiredArgsConstructor
public class SingletonController {
    @GetMapping
    public List<String> getInstances(@RequestParam int size) {
        List<String>  instances = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            instances.add(MySingleTonClass.getInstance().toString());
        }
        return instances;
    }
}
