package com.easy.bright.bot.service;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Сервис для хранения правильных ответов, и сравнения с ответами пользователей
 */
@Service
@Data
public class AnswersService {

    private final String ANSWER_1 = "Пятница";
    private final String ANSWER_2 = "Иннокентий";
    private final String ANSWER_3 = "Easy School";
    private final String ANSWER_4 = "Mr-Bright";
    private final String ANSWER_5 = "Тюльпаны";

    public boolean isQuestion1Correct(String answerQuestion1){
        return isAnswerCorrect(answerQuestion1, ANSWER_1);
    }

    public boolean isQuestion2Correct(String answerQuestion2){
        return isAnswerCorrect(answerQuestion2, ANSWER_2);
    }

    public boolean isQuestion3Correct(String answerQuestion3){
        return isAnswerCorrect(answerQuestion3, ANSWER_3);
    }

    public boolean isQuestion4Correct(String answerQuestion4){
        return isAnswerCorrect(answerQuestion4, ANSWER_4);
    }

    public boolean isQuestion5Correct(String answerQuestion5){
        return isAnswerCorrect(answerQuestion5, ANSWER_5);
    }

    private boolean isAnswerCorrect(String userAnswer, String correctAnswer){
        return userAnswer.toLowerCase(Locale.ROOT).equals(correctAnswer.toLowerCase(Locale.ROOT));
    }

}
