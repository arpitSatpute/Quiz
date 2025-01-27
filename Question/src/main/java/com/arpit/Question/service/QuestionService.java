package com.arpit.Question.service;

import com.arpit.Question.DTO.QuestionDTO;
import com.arpit.Question.entity.Question;
import com.arpit.Question.entity.QuestionWrapper;
import com.arpit.Question.entity.Response;
import com.arpit.Question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class QuestionService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);

    private final QuestionRepository questionRepository;
    private final ModelMapper modelmapper;



    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    public Question addQuestion(QuestionDTO questionDTO) {


        Question question = modelmapper.map(questionDTO, Question.class);

        Question savedQuestion = questionRepository.save(question);
        return savedQuestion;
    }


    public List<Question> getQuestionByCategory(String category) {
        return questionRepository.findQuestionByCategory(category);
    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String category, Integer numOfQuestions) {
        List<Integer> questionIds = questionRepository.findRandomQuestionsByCategory(category, numOfQuestions);
        return new ResponseEntity<>(questionIds, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsById(List<Integer> questionIds) {
       List<QuestionWrapper> questionWrappers = new ArrayList<>();
       List<Question> questions = new ArrayList<>();

       for(Integer id: questionIds) {
           questions.add(questionRepository.findById(id).get());
       }

       for(Question question : questions) {
           QuestionWrapper wrapper = modelmapper.map(question, QuestionWrapper.class);
           questionWrappers.add(wrapper);
       }

        return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> submitQuiz(List<Response> response) {
        int right = 0;
        int i = 0;
        for(Response res : response) {
            Question question = questionRepository.findById(res.getId()).get();
            if(res.getResponse().equals(question.getRightAnswer())) {
                right++;
            }

        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
