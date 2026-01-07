package com.patil.Quizapp.Service;

import com.patil.Quizapp.Dao.QuestionDao;
import com.patil.Quizapp.Model.Question;
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
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_GATEWAY);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category){
        try{
            return new ResponseEntity<>(questionDao.findByCategoryIgnoreCase(category), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_GATEWAY);
    }

    public ResponseEntity<String> addQuestion(Question question) {
//        Question addingQuestion =
//        String category = question.getCategory();
//        String questionTitle = question.getQuestionTitle();
//        String option1 = question.getOption1();
//        String option2 = question.getOption2();
//        String option3  = question.getOption3();
//        String option4  = question.getOption4();
//        String rightAnswer = question.getRightAnswer();
//        String difficultyLevel = question.getDifficultyLevel();
//
//        addingQuestion.setCategory(category);
//        addingQuestion.setQuestionTitle(questionTitle);
//        addingQuestion.setOption1(option1);
//        addingQuestion.setOption2(option2);
//        addingQuestion.setOption3(option3);
//        addingQuestion.setOption4(option4);
//        addingQuestion.setRightAnswer(rightAnswer);
//        addingQuestion.setDifficultyLevel(difficultyLevel);

        questionDao.save(question);
        try{
            return new ResponseEntity<>("Question added successfully",HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Question added successfully",HttpStatus.INTERNAL_SERVER_ERROR);
    }






    public ResponseEntity<Question> getQuestionById(int id) {
        try{
            Optional<Question> q = questionDao.findById(id);
            if(q.isPresent()){
                return new ResponseEntity<>(q.get(),HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





    public ResponseEntity<String> deleteQuestionById(int id) {

        try {
            if (!questionDao.existsById(id)) {
                return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
            }
            questionDao.deleteById(id);
            return new ResponseEntity<>("Question deleted successfully", HttpStatus.NOT_FOUND);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }


    public ResponseEntity<String> updateQuestionById(int id, Question question) {
        Optional<Question> q = questionDao.findById(id);
        if(q.isEmpty()){
            return new ResponseEntity<>("Question Not Found",HttpStatus.NOT_FOUND);
        }
        Question q1 = q.get();

//        q1.setCategory(question.getCategory());
//        q1.setQuestionTitle(question.getQuestionTitle());
//        q1.setOption1(question.getOption1());
//        q1.setOption2(question.getOption2());
//        q1.setOption3(question.getOption3());
//        q1.setOption4(question.getOption4());
//        q1.setRightAnswer(question.getRightAnswer());
//        q1.setDifficultyLevel(question.getDifficultyLevel());

//        BeanUtils.copyProperties(question,q1);


        if(question.getCategory() != null) q1.setCategory(question.getCategory());
        if(question.getQuestionTitle() != null) q1.setQuestionTitle(question.getQuestionTitle());
        if(question.getOption1() != null) q1.setOption1(question.getOption1());
        if(question.getOption2() != null) q1.setOption2(question.getOption2());
        if(question.getOption3() != null) q1.setOption3(question.getOption3());
        if(question.getOption4() != null) q1.setOption4(question.getOption4());
        if(question.getRightAnswer() != null) q1.setRightAnswer(question.getRightAnswer());
        if(question.getDifficultyLevel() != null) q1.setDifficultyLevel(question.getDifficultyLevel());

        questionDao.save(q1);
        try{
            return new ResponseEntity<>("Question updated successfully",HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed to update",HttpStatus.BAD_GATEWAY);
    }
}
