package com.easy.bright.bot.service;

import com.easy.bright.bot.model.BotUser;
import com.easy.bright.bot.repository.BotUserRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Сервис для логики связанной с пользователями бота, проходящими тестирование
 */
@Service
@Data
public class BotUserService {

    private BotUserRepository botUserRepository;

    public BotUserService(BotUserRepository botUserRepository){
        this.botUserRepository = botUserRepository;
    }

    public boolean addInputName(Update update){
        Long chatId = update.getMessage().getChatId();
        BotUser userForUpdate = botUserRepository.findByChatId(chatId);
        userForUpdate.setInputName(update.getMessage().getText());
        botUserRepository.save(userForUpdate);
        return true;
    }

    public boolean addSchool(Update update){
        Long chatId = update.getMessage().getChatId();
        BotUser userForUpdate = botUserRepository.findByChatId(chatId);
        userForUpdate.setSchoolName(update.getMessage().getText());
        botUserRepository.save(userForUpdate);
        return true;
    }

    public boolean addClass(Long chatId, String userClass){
        BotUser userForUpdate = botUserRepository.findByChatId(chatId);
        userForUpdate.setSchoolClass(userClass);
        botUserRepository.save(userForUpdate);
        return true;
    }

    public boolean addPhone(Update update){
        Long chatId = update.getMessage().getChatId();
        BotUser userForUpdate = botUserRepository.findByChatId(chatId);
        userForUpdate.setPhoneNumber(update.getMessage().getText());
        botUserRepository.save(userForUpdate);
        return true;
    }

    public boolean registerUser(Update update) {
        var chat = update.getMessage().getChat();
        if (botUserRepository.findByChatId(update.getMessage().getChatId()) == null) {
            BotUser newUser = new BotUser();
            newUser
                    .setFirstName(chat.getFirstName())
                    .setLastName(chat.getLastName())
                    .setTelegramName(chat.getUserName())
                    .setChatId(update.getMessage().getChatId())
                    .setRegisteredAt(new Timestamp(new Date().getTime()));
            botUserRepository.save(newUser);
            System.out.println("User success registered: " + newUser);
            return true;
        }else {
            return false;
        }
    }
}
