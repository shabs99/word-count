package com.example.wordcount.service;

import com.example.wordcount.model.WordCountResponse;
import org.springframework.web.multipart.MultipartFile;

public interface WordCountService {
    WordCountResponse count(MultipartFile file, String word);
}
