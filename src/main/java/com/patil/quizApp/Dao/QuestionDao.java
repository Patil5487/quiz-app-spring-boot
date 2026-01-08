package com.patil.quizApp.Dao;

import com.patil.quizApp.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionDao extends JpaRepository<Question, Long> {



    List<Question> findByCategoryIgnoreCase(String category);

    Optional<Question> findById(int id);
}
