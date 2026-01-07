package com.patil.Quizapp.Repo;

import com.patil.Quizapp.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer> {

}
