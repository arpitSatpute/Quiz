package com.arpit.Question.controller;

import com.arpit.Question.DTO.QuestionDTO;
import com.arpit.Question.entity.Question;
import com.arpit.Question.entity.QuestionWrapper;
import com.arpit.Question.entity.Response;
import com.arpit.Question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final Environment environment;



    @GetMapping
    public ResponseEntity<List<Question>> getQuestions() {
        List<Question> questions = questionService.getQuestions();

        return ResponseEntity.ok(questions);
    }

    @PostMapping
    public ResponseEntity<Question> addQuestion(@RequestBody QuestionDTO questionDTO) {
        Question question = questionService.addQuestion(questionDTO);
        return ResponseEntity.ok(question);
    }
    @GetMapping("/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        List<Question> questions = questionService.getQuestionByCategory(category);
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz (@RequestParam String category, @RequestParam Integer numOfQuestions){
        return questionService.getQuestionForQuiz(category, numOfQuestions);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(@RequestBody List<Integer> questionIds){
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsById(questionIds);
    }

    @PostMapping("/submit")
    public ResponseEntity<Integer> submitQuiz(@RequestBody List<Response> response) {
        return questionService.submitQuiz(response);
    }
}
