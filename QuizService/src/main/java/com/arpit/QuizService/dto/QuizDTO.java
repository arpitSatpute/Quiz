package com.arpit.QuizService.dto;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {

    private String category;
    private String title;
    private Integer noOfQuestions;


}
