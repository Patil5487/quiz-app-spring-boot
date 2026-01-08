package com.patil.quizApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quiz_questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String category;

    @Column(name = "difficulty_level", length = 20)
    private String difficultyLevel;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(length = 255)
    private String option1;

    @Column(length = 255)
    private String option2;

    @Column(length = 255)
    private String option3;

    @Column(length = 255)
    private String option4;

    @Column(name = "correct_answer", length = 255)
    private String correctAnswer;

}