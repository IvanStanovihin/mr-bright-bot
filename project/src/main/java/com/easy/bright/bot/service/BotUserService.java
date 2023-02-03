package com.easy.bright.bot.service;

import com.easy.bright.bot.repository.BotUserRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class BotUserService {

    private BotUserRepository botUserRepository;

    public BotUserService(BotUserRepository botUserRepository){
        this.botUserRepository = botUserRepository;
    }
}
