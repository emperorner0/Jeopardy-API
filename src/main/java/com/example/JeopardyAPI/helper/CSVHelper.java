package com.example.JeopardyAPI.helper;

import com.example.JeopardyAPI.model.Question;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVHelper {

    public static String TYPE = "text/csv";
    static String[] HEADERS = {"Show Number", "Round", "Category", "Value", "Question", "Answer"};

    public static boolean hasCSVFormat(MultipartFile file){
        return TYPE.equals(file.getContentType());
    }

    public static List<Question> csvToQuestion(InputStream is){
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.RFC4180.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());){

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            List<Question> questionList = new ArrayList<>();
            for (CSVRecord strings : csvRecords) {
                Question parse = Question.parse(strings);
                questionList.add(parse);
            }
            return questionList;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());

        }
    }

}
