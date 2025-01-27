package com.arpit.QuizService.feign;

import com.arpit.QuizService.entity.QuestionWrapper;
import com.arpit.QuizService.entity.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient("QUESTION")
public interface QuizInterface {

    @GetMapping("/questions/generate")
    ResponseEntity<List<Integer>> getQuestionForQuiz (@RequestParam String category, @RequestParam Integer numOfQuestions);

    @PostMapping("/questions/getQuestions")
    ResponseEntity<List<QuestionWrapper>> getQuestionsById(@RequestBody List<Integer> questionIds);


    @PostMapping("/questions/submit")
    ResponseEntity<Integer> submitQuiz(@RequestBody List<Response> response);


}
