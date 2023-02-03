package com.easy.bright.bot.telegram;

import com.easy.bright.bot.configuration.AppConfig;
import com.easy.bright.bot.service.CommandHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class BotLogic extends TelegramLongPollingBot {

    private AppConfig appConfig;
    private CommandHandlerService commandHandlerService;

    public BotLogic(AppConfig appConfig, CommandHandlerService commandHandlerService){
        this.appConfig = appConfig;
        this.commandHandlerService = commandHandlerService;
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()){
            sendResponse(update.getMessage().getChatId(), commandHandlerService.getUnrecognizedCommandResponse());
        }

        String messageText = update.getMessage().getText();
        switch(messageText){
            case "/start":
                String startCommandResponse = commandHandlerService.handleStartCommand();
                sendResponse(update.getMessage().getChatId(), startCommandResponse);
                break;
            case "/help":
                break;
            default:
                sendResponse(update.getMessage().getChatId(), commandHandlerService.getUnrecognizedCommandResponse());

        }
    }

    private void sendResponse(long chatId, String text){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        try{
            execute(message);
        }catch(TelegramApiException ex){
            log.error("Error occurred while sending message for user. ChatId: " + chatId + ".\n Exception: " + ex);
        }
    }

    @Override
    public String getBotToken() {
        return appConfig.getBotToken();
    }


    @Override
    public String getBotUsername() {
        return appConfig.getBotName();
    }
}
