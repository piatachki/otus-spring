package com.example.otus.model;

import java.util.List;

public class Quiz {

    private String question;
    private List<Answer> answers;

    public String getQuestion() {
        return question;
    }

    public Quiz setQuestion(String question) {
        this.question = question;
        return this;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public Quiz setAnswers(List<Answer> answers) {
        this.answers = answers;
        return this;
    }
}
