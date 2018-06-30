package com.example.otus.repository;

import com.example.otus.model.Answer;
import com.example.otus.model.Quiz;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@Service("bundleQuizSource")
public class BundleQuizSource implements QuizSource {

    private MessageSource messageSource;
    private Locale locale;

    public BundleQuizSource(@Qualifier("quizMessageSource") MessageSource messageSource,
                            @Qualifier("temporalLocale") Locale locale) {
        this.messageSource = messageSource;
        this.locale = locale;
    }

    @Override
    public List<Quiz> getQuizList() {
        List<Quiz> result = new LinkedList<>();
        for (int quizIndex = 1; quizIndex <=5; quizIndex ++) {
            List<Answer> answers = new LinkedList<>();
            for (int answerIndex = 1; answerIndex <= 2; answerIndex ++) {
                Answer answer = new Answer()
                        .setAnswer(messageSource.getMessage(
                                String.format("quiz.%s.answer.%s", quizIndex, answerIndex),
                                null,
                                locale
                        ))
                        .setCorrect(
                                messageSource.getMessage(
                                        String.format("quiz.%s.answer.%s.correct", quizIndex, answerIndex),
                                        null,
                                        locale
                                ).equals("true")
                        );
                answers.add(answer);
            }
            String quizKey = String.format("quiz.%s.question", Integer.toString(quizIndex));
            Quiz quiz = new Quiz()
                    .setQuestion(messageSource.getMessage(quizKey, null, locale))
                    .setAnswers(answers);
            result.add(quiz);
        }
        return result;
    }
}
