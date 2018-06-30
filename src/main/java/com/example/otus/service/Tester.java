package com.example.otus.service;

import com.example.otus.model.Answer;
import com.example.otus.model.Quiz;
import com.example.otus.repository.QuizSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class Tester {

    final private QuizSource quizSource;
    final private CommunicationBridge communicator;
    final private Locale locale;
    final private MessageSource messageSource;

    public Tester(@Qualifier("bundleQuizSource") QuizSource quizSource,
                  @Qualifier("consoleBridge") CommunicationBridge communicationInterface,
                  @Qualifier("temporalLocale") Locale locale,
                  @Qualifier("interfaceMessageSource") MessageSource messageSource) {
        this.quizSource = quizSource;
        this.communicator = communicationInterface;
        this.locale = locale;
        this.messageSource = messageSource;
    }

    public void doTest() {

        communicator.write(messageSource.getMessage("prompt.name", null, locale));
        String name = communicator.read();

        communicator.write(messageSource.getMessage("prompt.surname", null, locale));
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
