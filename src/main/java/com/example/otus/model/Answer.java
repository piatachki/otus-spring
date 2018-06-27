package com.example.otus.model;

public class Answer {

    private String answer;
    private boolean correct;

    public String getAnswer() {
        return answer;
    }

    public Answer setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    public boolean isCorrect() {
        return correct;
    }

    public Answer setCorrect(boolean correct) {
        this.correct = correct;
        return this;
    }
}
