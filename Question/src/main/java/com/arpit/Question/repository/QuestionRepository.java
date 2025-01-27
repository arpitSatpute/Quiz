package com.arpit.Question.repository;

import com.arpit.Question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findQuestionByCategory(String category);

    @Query(value = "SELECT question.id FROM question WHERE category=:category ORDER BY RANDOM() LIMIT :numOfQuestions", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, Integer numOfQuestions);

}
