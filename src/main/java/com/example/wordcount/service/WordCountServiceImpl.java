package com.example.wordcount.service;

import com.example.wordcount.model.WordCountResponse;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
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

    @SneakyThrows
    private String xmlFileReader(String xmlString) {
        final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        final DocumentBuilder db = dbf.newDocumentBuilder();
        final ByteArrayInputStream bis = new ByteArrayInputStream(xmlString.getBytes());
        final Document doc = db.parse(bis);
        final NodeList nodeList = doc.getElementsByTagName("*");
        Node node;
        StringBuffer extractedXML= new StringBuffer();
        node = nodeList.item(0);
        extractedXML.append(node.getTextContent());
        return extractedXML.toString();
    }

    @Override
    public WordCountResponse count(MultipartFile file, String searchWord) {
        String words = readFile(file);
        String ext= file.getContentType();
        if(ext.contains("xml")){
            words = xmlFileReader(words);
        }
        words = words.replaceAll("[\\s\\.]", " ");
        words = words.replace("\uFEFF", "");
        words = words.replace("\n", "");
        Long occurrence = countOccurrence(words, searchWord);
        return new WordCountResponse(file.getOriginalFilename(), searchWord, occurrence);
    }
}
