package com.easy.bright.bot.service;

import com.easy.bright.bot.model.BotUser;
import com.easy.bright.bot.repository.BotUserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.sql.Timestamp;
import java.util.Date;

@Service
@Data
public class MessageService {

    private BotUserRepository botUserRepository;
    private SendMessageService sendMessageService;

    public MessageService(BotUserRepository botUserRepository, SendMessageService sendMessageService){
        this.botUserRepository = botUserRepository;
        this.sendMessageService = sendMessageService;
    }

    public SendMessage handleStartCommand(Update update){
        SendMessage startMessage = new SendMessage();
        if (registerUser(update)){
            startMessage = sendMessageService.getStartMessage();
        }else{
            startMessage.setText("Извини, но ты больше не можешь пройти тестирование. Ты потратил свои попытки!");
        }
        return startMessage;
    }


    private boolean registerUser(Update update){
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
