package com.example.wordcount.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordCountResponse {

    private String fileName;
    private String word;
    private long occuarence;
}
