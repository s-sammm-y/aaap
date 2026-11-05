package com.aaap.Service.Abstractions;

import com.aaap.Model.Models.Question;

import java.util.List;

public interface IQAGameService {
    List<Question> getQuestions();

    boolean validateAnswer(Question question, List<String> selectedAnswers);
}
