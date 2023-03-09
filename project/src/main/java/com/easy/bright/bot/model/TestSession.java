package com.easy.bright.bot.model;

import java.util.List;

public class TestSession {

    /**
     * Длительность сессии тестирования. Количество минут,
     * отведённое на прохождение теста, в миллисекундах
     */
    private final long SESSION_DURATION = 1_800_000;

    /**
     * Время, когда пользователь начал проходить тест. В миллисекундах
     */
    private long startAt;
    private List<Question> question;
    private BotUser testedUser;

    /**
     * Метод для проверки не истекла ли текущая сессия
     */
    public boolean isActive(){
        return (System.currentTimeMillis() - startAt) <= SESSION_DURATION;
    }

}
