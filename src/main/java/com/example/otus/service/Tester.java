package com.example.otus.service;

import com.example.otus.model.Answer;
import com.example.otus.model.Quiz;
import com.example.otus.repository.QuizSource;

import java.util.List;

public class Tester {

    final private QuizSource quizSource;
    final private CommunicationBridge communicator;

    public Tester(QuizSource quizSource, CommunicationBridge communicationInterface) {
        this.quizSource = quizSource;
        this.communicator = communicationInterface;
    }

    public void doTest() {

        communicator.write("Input your name:");
        String name = communicator.read();

        communicator.write("Input your surname");
        String surName = communicator.read();

        List<Quiz> quizzes = quizSource.getQuizList();

        int score = quizzes.stream()
                .mapToInt(quiz -> {
                    communicator.write(quiz.getQuestion());
                    List<Answer> answers = quiz.getAnswers();
                    for (int i = 0; i < answers.size(); i++) {
                        communicator.write((i + 1) + ": " + answers.get(i).getAnswer());
                    }
                    communicator.write("Input number of answer seems right");

                    String answerInput = communicator.read();
                    int answerNumber = Integer.valueOf(answerInput);
                    communicator.write("");
                    if (answers.get(answerNumber - 1).isCorrect()) return 1;
                    return 0;
                })
                .sum();

        communicator.write(name + " " + surName);
        communicator.write("Your score is " + score + " of " + quizzes.size());
    }
}
