package com.example.otus.service;

import com.example.otus.model.Answer;
import com.example.otus.model.Quiz;
import com.example.otus.repository.QuizSource;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
        AtomicInteger score = new AtomicInteger();

        quizzes.forEach(quiz -> {
            communicator.write(quiz.getQuestion());
            List<Answer> answers = quiz.getAnswers();
            for (int i = 0; i < answers.size(); i++) {
                communicator.write((i + 1) + ": " + answers.get(i).getAnswer());
            }
            communicator.write("Input number of answer seems right");

            String answerInput = communicator.read();
            int answerNumber = Integer.valueOf(answerInput);
            if (answers.get(answerNumber - 1).isCorrect()) score.getAndIncrement();
            communicator.write("");
        });

        communicator.write(name + " " + surName);
        communicator.write("Your score is " + score + " of " + quizzes.size());
    }
}
