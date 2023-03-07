package com.easy.bright.bot.utils;

public enum BotStateEnum {

    DEFAULT(0),
    /**
     * Следующим сообщением ожидается ввод имени от пользователя
     */
    INPUT_NAME(1),
    INPUT_SCHOOL(2),
    INPUT_CLASS(3),
    INPUT_PHONE_NUMBER(4);


    private int stateNumber;

    private BotStateEnum(int stateNumber){
        this.stateNumber = stateNumber;
    }

    public int getValue(){
        return stateNumber;
    }
}
