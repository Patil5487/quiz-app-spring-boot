package com.patil.Quizapp.Service;

import com.patil.Quizapp.Dao.QuestionDao;
import com.patil.Quizapp.Dao.QuizDao;
import com.patil.Quizapp.Model.Question;
import com.patil.Quizapp.Model.QuestionWrapper;
import com.patil.Quizapp.Model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questionList = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questionList);

        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for (Question question : questionFromDB) {
            QuestionWrapper questionWrapper = new QuestionWrapper(question.getId(),question.getQuestionTitle(),question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4());
            questionsForUser.add(questionWrapper);
        }

        return  new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }
}
