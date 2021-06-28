package com.example.JeopardyAPI.controller;

import com.example.JeopardyAPI.helper.CSVHelper;
import com.example.JeopardyAPI.model.Question;
import com.example.JeopardyAPI.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.LongSummaryStatistics;

@Controller
@RequestMapping(value = "/api/csv/v1", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.POST,
        RequestMethod.DELETE})
public class CSVController {

    @Autowired
    CSVService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file){
        String message="";

        if (CSVHelper.hasCSVFormat(file)){
            fileService.save(file);

            try{
                fileService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body("\" message \": \" " + message +"\"");
            } catch (Exception e) {
                message = "Could not upload file: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("\" message \": \" " + message +"\"");
            }
        }
        message = "Please upload as CSV file.";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\" message \": \" " + message +"\"");
    }

    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        try {
            List<Question> questions = fileService.getAllQuestions();

            if (questions.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Question>>(questions, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getQuestionByValue/{value}")
    public ResponseEntity<List<Question>> getQuestionByValue(@PathVariable Integer value){
        try {
            List<Question> questions = fileService.getQuestionByValue(value);

            if (questions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Question>>(questions, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getQuestionByCategory/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        try {
            List<Question> questions = fileService.getQuestionByCategory(category);

            if (questions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Question>>(questions, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getQuestionByRound/{round}")
    public ResponseEntity<List<Question>> getQuestionByRound(@PathVariable String round){
        try {
            List<Question> questions = fileService.getQuestionByRound(round);

            if (questions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Question>>(questions, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getQuestionByRoundAndCategory/{round}/{category}")
    public ResponseEntity<List<Question>> getQuestionByRound(@PathVariable String round, @PathVariable String category){
        try {
            List<Question> questions = fileService.getQuestionByRoundAndCategory(round, category);

            if (questions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Question>>(questions, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        try {
            fileService.deleteId(id);
            String response = "Successfully Deleted";
            return new ResponseEntity<String>(response, HttpStatus.OK);
        } catch (Exception e){
            String response2 = "Operation Unsuccessful";
            return new ResponseEntity<String>(response2, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        try{
            fileService.addQuestion(question);

            String response = "Question added";
            return new ResponseEntity<String>(response, HttpStatus.OK);
        } catch (Exception e){
            String response2 = "Unable to Add Question";
            return new ResponseEntity<String>(response2, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getQuestionById/{id}")
    public ResponseEntity<List<Question>> getQuestionById(@PathVariable int id){
        try {
            List<Question> questions = fileService.getQuestionById(Long.valueOf(id));

            if (questions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Question>>(questions, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
