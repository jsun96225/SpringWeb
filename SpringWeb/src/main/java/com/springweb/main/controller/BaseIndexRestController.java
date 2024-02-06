package com.springweb.main.controller;

import com.springweb.main.model.RequestItem;
import org.springframework.web.bind.annotation.*;

//get, post, put, delete

@RestController
public class BaseIndexRestController {

    @GetMapping("/get")
    public String get() {
        return "get method";
    }

    @PostMapping("/post1")
    public String post1() {
        return "postmapping";
    }

    //request
    //
    @PostMapping("/post2")
    public String post2(@RequestParam Long id) {
        return "postmapping 2" + id;
    }

    @PostMapping("/post3/{id}")
    public String post3(@PathVariable Long id) {

        return "postmapping 3" + id;
    }

    @PostMapping("/post4")
    public String post4(@RequestBody RequestItem requestItem) {
        return "postmapping 4" + requestItem;
    }
}
