package com.example.wordcount.controller;

import com.example.wordcount.model.WordCountResponse;
import com.example.wordcount.service.WordCountService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.FileInputStream;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = WordCountController.class)
@ContextConfiguration
class WordCountControllerTest {
    @MockBean
    private WordCountService wordCountService;
    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    void count() {

        String path = "src/test/java/com/example/wordcount/billing.txt";

        WordCountResponse wordCountResponse = new WordCountResponse("billing.txt","test",5);

        MockMultipartFile file = new MockMultipartFile("billing.txt", "billing.txt","text/plain",new FileInputStream(path));
        when(wordCountService.count(file, "test")).thenReturn(wordCountResponse);
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/word-count/count/test")
                .file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andReturn();
    }

}