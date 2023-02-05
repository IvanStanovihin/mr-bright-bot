package com.easy.bright.bot.service;

import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * Сервис, для хранения и выдачи вопросов для теста
 */
@Service
@Data
public class QuestionsService {

    private static String QUESTION_1 = "Какой сегодня день недели?";
    private static String QUESTION_2 = "Как вас зовут?";
    private static String QUESTION_3 = "Название вашей школы?";
    private static String QUESTION_4 = "Название конкурса?";
    private static String QUESTION_5 = "Ваш любимый вид цветов?";

    public String getQuestion1(){
        return QUESTION_1;
    }

    public String getQuestion2(){
        return QUESTION_2;
    }

    public String getQuestion3(){
        return QUESTION_3;
    }

    public String getQuestion4(){
        return QUESTION_4;
    }

    public String getQuestion5(){
        return QUESTION_5;
    }
}
