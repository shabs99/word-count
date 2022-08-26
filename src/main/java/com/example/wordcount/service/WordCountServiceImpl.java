package com.example.wordcount.service;

import com.example.wordcount.model.WordCountResponse;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;


@Service
public class WordCountServiceImpl implements WordCountService{

    @SneakyThrows
    private Long countOccurrence(String words, String word) {
        long occurrence =Arrays.stream(words.split("[ ,]+")).filter(s -> s.equals(word)).count();
        return occurrence;
    }

    @SneakyThrows
    private String readFile(MultipartFile file){
        return new String(file.getBytes());
    }

    @Override
    public WordCountResponse count(MultipartFile file, String searchWord) {
        String words = readFile(file);
        words = words.replaceAll("[\\s\\.]", " ");
        words = words.replace("\uFEFF", "");
        words = words.replace("\n", "");
        Long occurrence = countOccurrence(words, searchWord);
        return new WordCountResponse(file.getOriginalFilename(), searchWord, occurrence);
    }
}
