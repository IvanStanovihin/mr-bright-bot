package com.easy.bright.bot.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

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

   public void processQuestion1(Update update, String userAnswer){

   }

    public void processQuestion2(Update update, String userAnswer){

    }

    public void processQuestion3(Update update, String userAnswer){

    }

    public void processQuestion4(Update update, String userAnswer){

    }

    private boolean isAnswerCorrect(String userAnswer, String correctAnswer){
        return userAnswer.toLowerCase(Locale.ROOT).equals(correctAnswer.toLowerCase(Locale.ROOT));
    }

}
