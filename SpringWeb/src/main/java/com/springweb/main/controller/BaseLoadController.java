package com.springweb.main.controller;

import com.springweb.main.service.LoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseLoadController {

    private LoaderService loaderService;

    @Autowired
    public BaseLoadController(LoaderService loaderService) {
        this.loaderService = loaderService;
    }

    @PostMapping("/load")
    public String loadData(@RequestParam String folder) {
        boolean result = loaderService.loadAllFromFiles(folder);
        return result ? "sucess" : "fail";
    }
}
