package com.arpit.QuizService.controller;

import com.arpit.QuizService.dto.QuizDTO;
import com.arpit.QuizService.entity.QuestionWrapper;
import com.arpit.QuizService.entity.Response;
import com.arpit.QuizService.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;


    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO) {
        return quizService.createQuiz(quizDTO.getCategory(), quizDTO.getNoOfQuestions(), quizDTO.getTitle());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@PathVariable int id) {
        return quizService.getQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> response) {
        return quizService.submitQuiz(id, response);
    }
}
