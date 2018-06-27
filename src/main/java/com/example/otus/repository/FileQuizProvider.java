package com.example.otus.repository;

import com.example.otus.model.Answer;
import com.example.otus.model.Quiz;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileQuizProvider implements QuizDataSource {

    private Resource filename;

    public void setFilename(Resource filename) {
        this.filename = filename;
    }

    @Override
    public List<Quiz> getQuizList() {

        List<String> rows;

        try {
            rows = Files.readAllLines(Paths.get(filename.getURI()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return rows.stream()
                .map(row -> {

                    String[] cols = row.split(";");

                    List<Answer> answers = Arrays.stream(cols)
                            .skip(1)
                            .map(col -> {
                                String[] ans = col.split("~");
                                return new Answer()
                                        .setAnswer(ans[0])
                                        .setCorrect(ans[1].equals("t"));
                            })
                            .collect(Collectors.toList());

                    return new Quiz()
                            .setQuestion(cols[0])
                            .setAnswers(answers);
                })
                .collect(Collectors.toList());

    }
}
