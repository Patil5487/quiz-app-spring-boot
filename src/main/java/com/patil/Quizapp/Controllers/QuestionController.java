package com.patil.Quizapp.Controllers;

import com.patil.Quizapp.Model.Question;
import com.patil.Quizapp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }
    
    @GetMapping("get/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable int id){
        return questionService.getQuestionById(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable int id){
        return questionService.deleteQuestionById(id);
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<String> updateQuestionById(@PathVariable int id, @RequestBody Question question){
        return questionService.updateQuestionById(id,question);
    }
}
