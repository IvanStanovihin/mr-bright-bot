package com.easy.bright.bot.utils;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BotStateService {

    public Map<Long, BotStateEnum>botStateStorage;

    public BotStateService(){
        this.botStateStorage = new ConcurrentHashMap<>();
    }

    public void changeState(long chatId, BotStateEnum botState){
        botStateStorage.put(chatId, botState);
    }

    public BotStateEnum getBotState(long chatId){
        return botStateStorage.get(chatId);
    }
}
