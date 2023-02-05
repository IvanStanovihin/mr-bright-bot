package com.easy.bright.bot.service;

import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * Сервис для обработки команд, поступающих из чата с пользователем
 */
@Data
@Service
public class CommandHandlerService {

    public CommandHandlerService(){

    }

    public String handleStartCommand(){
        return "Hello!";
    }

    public String getUnrecognizedCommandResponse(){
        return "Sorry command unrecognized!";
    }
}
