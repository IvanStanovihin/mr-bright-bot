package com.easy.bright.bot.service;

import com.easy.bright.bot.repository.BotUserRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Сервис для логики связанной с пользователями бота, проходящими тестирование
 */
@Service
@NoArgsConstructor
public class BotUserService {

    private BotUserRepository botUserRepository;

    public BotUserService(BotUserRepository botUserRepository){
        this.botUserRepository = botUserRepository;
    }
}
