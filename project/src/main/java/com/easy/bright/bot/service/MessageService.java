package com.easy.bright.bot.service;

import com.easy.bright.bot.model.BotUser;
import com.easy.bright.bot.repository.BotUserRepository;
import com.easy.bright.bot.utils.BotState;
import com.easy.bright.bot.utils.BotStateEnum;
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
    private BotUserService botUserService;
    private SendMessageService sendMessageService;

    public MessageService(BotUserRepository botUserRepository, SendMessageService sendMessageService,
                          BotUserService botUserService){
        this.botUserRepository = botUserRepository;
        this.sendMessageService = sendMessageService;
        this.botUserService = botUserService;
    }

    public SendMessage getClassInfoMessage(){
        return sendMessageService.getClassInfoMessage();
    }

    public SendMessage getNameInfoMessage(){
        BotState.botState = BotStateEnum.INPUT_NAME;
        return sendMessageService.getNameInfoMessage();
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
        return botUserService.registerUser(update);
    }


    /**
     * Пользователь вводит имя. Имя добавляется в БД к информации о пользователе.
     * Пользователю возвращается сообщение с просьбой ввести информацию о школе
     */
    public SendMessage processEnteredName(Update update){
        botUserService.addInputName(update);
        BotState.botState = BotStateEnum.INPUT_SCHOOL;
        return sendMessageService.getSchoolInfoMessage();
    }

    /**
     * Пользователь вводит школу. Школа добавляется в Бд к информации о пользователе.
     * Пользователю возвращается сообщение с просьбой ввести класс.
     */
    public SendMessage processEnteredSchool(Update update){
        botUserService.addSchool(update);
        BotState.botState = BotStateEnum.INPUT_CLASS;
        return sendMessageService.getClassInfoMessage();
    }

    /**
     * Пользователь выбирает класс, нажатием на одну из кнопок.
     * Класс добавляется в Бд к информации о пользователе.
     * Пользователю возвращается сообщение с просьбой ввести номер телефона.
     */
    public SendMessage processEnteredClass(Long chatId, String userClass){
        botUserService.addClass(chatId, userClass);
        BotState.botState = BotStateEnum.INPUT_PHONE_NUMBER;
        return sendMessageService.getPhoneInfoMessage();
    }

    /**
     * Пользователь вводит номер телефона. Телефон добавляется в Бд к информации о пользователе.
     * Пользователю возвращается сообщение об успешном завершении регистрации
     */
    public SendMessage processEnteredPhone(Update update){
        botUserService.addPhone(update);
        return sendMessageService.getCompleteRegistrationMessage();
    }
}
