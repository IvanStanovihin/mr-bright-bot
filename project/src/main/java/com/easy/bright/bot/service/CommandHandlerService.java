package com.easy.bright.bot.service;

import lombok.Data;
import org.springframework.stereotype.Service;

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
