package com.easy.bright.bot.telegram;

import com.easy.bright.bot.model.TestSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestSessionManager extends Thread{

    private Map<Long, TestSession> sessionsStorage;

    public TestSessionManager(){
        this.sessionsStorage = new ConcurrentHashMap<>();
    }

    @Override
    public void run() {
        System.out.println("TestSessionManager started");
        sessionsStorage.entrySet().removeIf(s -> !s.getValue().isActive());
        try {
            Thread.sleep(5_000);
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }
    }

    public TestSession getSession(Long chatId){
        return sessionsStorage.get(chatId);
    }
}
