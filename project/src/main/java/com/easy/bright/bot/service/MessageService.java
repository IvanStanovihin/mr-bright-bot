package com.easy.bright.bot.service;

import com.easy.bright.bot.repository.BotUserRepository;
import com.easy.bright.bot.utils.BotStateService;
import com.easy.bright.bot.utils.BotStateEnum;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@Data
public class MessageService {

    private BotUserRepository botUserRepository;
    private BotUserService botUserService;
    private SendMessageService sendMessageService;
    private BotStateService botStateService;

    public MessageService(BotUserRepository botUserRepository, SendMessageService sendMessageService,
                          BotUserService botUserService, BotStateService botStateService){
        this.botUserRepository = botUserRepository;
        this.sendMessageService = sendMessageService;
        this.botUserService = botUserService;
        this.botStateService = botStateService;
    }

    public SendMessage getClassInfoMessage(){
        return sendMessageService.getClassInfoMessage();
    }

    public SendMessage getNameInfoMessage(long chatId){
        botStateService.changeState(chatId, BotStateEnum.INPUT_NAME);
        return sendMessageService.getNameInfoMessage();
    }

    public SendMessage getStartSessionInfo(){
        return sendMessageService.getStartSessionInfo();
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
        botStateService.changeState(update.getMessage().getChatId(), BotStateEnum.INPUT_SCHOOL);
        return sendMessageService.getSchoolInfoMessage();
    }

    /**
     * Пользователь вводит школу. Школа добавляется в Бд к информации о пользователе.
     * Пользователю возвращается сообщение с просьбой ввести класс.
     */
    public SendMessage processEnteredSchool(Update update){
        botUserService.addSchool(update);
        botStateService.changeState(update.getMessage().getChatId(), BotStateEnum.INPUT_CLASS);
        return sendMessageService.getClassInfoMessage();
    }

    /**
     * Пользователь выбирает класс, нажатием на одну из кнопок.
     * Класс добавляется в Бд к информации о пользователе.
     * Пользователю возвращается сообщение с просьбой ввести номер телефона.
     */
    public SendMessage processEnteredClass(Long chatId, String userClass){
        botUserService.addClass(chatId, userClass);
        botStateService.changeState(chatId, BotStateEnum.INPUT_PHONE_NUMBER);
        return sendMessageService.getPhoneInfoMessage();
    }

    /**
     * Пользователь вводит номер телефона. Телефон добавляется в Бд к информации о пользователе.
     * Пользователю возвращается сообщение об успешном завершении регистрации
     */
    public SendMessage processEnteredPhone(Update update){
        botUserService.addPhone(update);
        botStateService.changeState(update.getMessage().getChatId(), BotStateEnum.START_TEST_SESSION);
        return sendMessageService.getStartSessionInfo();
    }
}
