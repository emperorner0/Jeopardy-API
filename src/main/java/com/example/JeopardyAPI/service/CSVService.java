package com.example.JeopardyAPI.service;

import com.example.JeopardyAPI.helper.CSVHelper;
import com.example.JeopardyAPI.model.Question;
import com.example.JeopardyAPI.repository.QuestionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class CSVService {

    @Autowired
    QuestionsRepo repository;

    public void save(MultipartFile file){
        try {
            List<Question> questions = CSVHelper.csvToQuestion(file.getInputStream());
            repository.saveAll(questions);
        } catch (IOException e){
            throw new RuntimeException("Failed to store questions from CSV: " + e.getMessage());
        }
    }
    public List<Question> getAllQuestions(){
        return repository.findAll();
    }

    public List<Question> getQuestionByValue(Integer value){
        return repository.findAll().stream()
                .filter(s->s.getValue(value).equals(value))
                .collect(Collectors.toList());
    }

    public List<Question> getQuestionByCategory(String category){
        return repository.findAll().stream()
                .filter(s->s.getCategory().contains(category.toUpperCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    public List<Question> getQuestionByRound(String round){
        return repository.findAll().stream()
                .filter(s->s.getRound().toUpperCase(Locale.ROOT).contains(round.toUpperCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    public List<Question> getQuestionByRoundAndCategory(String round, String category){
        return repository.findAll().stream()
                .filter(s->s.getRound().toUpperCase(Locale.ROOT).contains(round.toUpperCase(Locale.ROOT)))
                .filter(s->s.getCategory().toUpperCase(Locale.ROOT).contains(category.toUpperCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    public void deleteId(Long id){
        repository.deleteById(id);
    }

    public void addQuestion(Question question){
        repository.save(question);
    }
}
