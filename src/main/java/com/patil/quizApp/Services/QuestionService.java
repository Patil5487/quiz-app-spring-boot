package com.patil.quizApp.Services;

import com.patil.quizApp.Model.Question;
import com.patil.quizApp.Dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;


    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<Question>> getAllQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategoryIgnoreCase(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }


    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }

    public void updateQuestion(Question question, Long id) {
        Question existingQuestion = questionDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        if (question.getCategory() != null) {
            existingQuestion.setCategory(question.getCategory());
        }
        if (question.getDifficultyLevel() != null) {
            existingQuestion.setDifficultyLevel(question.getDifficultyLevel());
        }
        if (question.getQuestion() != null) {
            existingQuestion.setQuestion(question.getQuestion());
        }
        if (question.getOption1() != null) {
            existingQuestion.setOption1(question.getOption1());
        }
        if (question.getOption2() != null) {
            existingQuestion.setOption2(question.getOption2());
        }
        if (question.getOption3() != null) {
            existingQuestion.setOption3(question.getOption3());
        }
        if (question.getOption4() != null) {
            existingQuestion.setOption4(question.getOption4());
        }
        if (question.getCorrectAnswer() != null) {
            existingQuestion.setCorrectAnswer(question.getCorrectAnswer());
        }
        questionDao.save(existingQuestion);
    }

    public void deleteQuestion(long id) {
        Question existingQuestion = questionDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        questionDao.delete(existingQuestion);
    }
}
