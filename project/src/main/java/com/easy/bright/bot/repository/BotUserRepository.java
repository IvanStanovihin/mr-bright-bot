package com.easy.bright.bot.repository;

import com.easy.bright.bot.model.BotUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotUserRepository extends CrudRepository<BotUser, Long> {

    public BotUser findByChatId(Long chatId);
}
