package com.example.wordcount.controller;

import com.example.wordcount.model.WordCountResponse;
import com.example.wordcount.service.WordCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/word-count")
public class WordCountController {

    @Autowired
    private WordCountService wordCountService;

    @PostMapping("/count/{searchWord}")
    public ResponseEntity<WordCountResponse> count(@RequestParam("file") MultipartFile file,
                                                   @PathVariable("searchWord") String searchWord){
        WordCountResponse response =wordCountService.count(file,searchWord);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
