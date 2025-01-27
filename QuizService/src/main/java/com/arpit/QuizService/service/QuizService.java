package com.arpit.QuizService.service;


import com.arpit.QuizService.entity.QuestionWrapper;
import com.arpit.QuizService.entity.Quiz;
import com.arpit.QuizService.entity.Response;
import com.arpit.QuizService.feign.QuizInterface;
import com.arpit.QuizService.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizInterface quizInterface;
    private final ModelMapper modelMapper;


    public ResponseEntity<String> createQuiz(String category, int numOfQuestions, String title) {

        List<Integer> questions = quizInterface.getQuestionForQuiz(category, numOfQuestions).getBody();
        Quiz quiz =modelMapper.map(questions, Quiz.class);
        quiz.setCategory(category);
        quiz.setTitle(title);
        quiz.setQuestionsIds(questions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestions(int quizId) {

        Quiz quiz = quizRepository.findById(quizId).get();
        List<Integer> queIds = quiz.getQuestionsIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsById(queIds);


        return questions;
    }

    public ResponseEntity<Integer> submitQuiz(int id, List<Response> response) {
        ResponseEntity<Integer> right = quizInterface.submitQuiz(response);
        return right;
    }
}
