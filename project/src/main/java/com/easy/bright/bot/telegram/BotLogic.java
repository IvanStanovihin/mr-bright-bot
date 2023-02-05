package com.easy.bright.bot.telegram;

import com.easy.bright.bot.configuration.AppConfig;
import com.easy.bright.bot.service.CommandHandlerService;
import com.easy.bright.bot.service.TestService;
import com.easy.bright.bot.utils.BotConstants;
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
    private TestService testService;
    private CommandHandlerService commandHandlerService;

    public BotLogic(AppConfig appConfig, CommandHandlerService commandHandlerService, TestService testService){
        this.appConfig = appConfig;
        this.commandHandlerService = commandHandlerService;
        this.testService = testService;
    }


    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasCallbackQuery()){
            String callBackData = update.getCallbackQuery().getData();
            switch(callBackData) {
                case BotConstants.QUESTION_1_ANSWER_1_ID:
                    sendTextResponse(update.getCallbackQuery().getMessage().getChatId(), "Вы выбрали 1 вариант ответа!");
                    break;
                case BotConstants.QUESTION_1_ANSWER_2_ID:
                    sendTextResponse(update.getCallbackQuery().getMessage().getChatId(), "Вы выбрали 2 вариант ответа!");
                    break;
                case BotConstants.QUESTION_1_ANSWER_3_ID:
                    sendTextResponse(update.getCallbackQuery().getMessage().getChatId(), "Вы выбрали 3 вариант ответа!");
                    break;
                case BotConstants.QUESTION_1_ANSWER_4_ID:
                    sendTextResponse(update.getCallbackQuery().getMessage().getChatId(), "Вы выбрали 4 вариант ответа!");
                    break;
                default:
                    sendTextResponse(update.getCallbackQuery().getMessage().getChatId(), "Извините, кнопка не распознана!");
                    break;
            }
        }else {
            String messageText = update.getMessage().getText();
            switch (messageText) {
                case "/start":
                    String startCommandResponse = commandHandlerService.handleStartCommand();
                    sendTextResponse(update.getMessage().getChatId(), startCommandResponse);
                    break;
                case "/help":
                    sendTextResponse(update.getMessage().getChatId(), "Вы запросили справку по командам бота");
                    break;
                case "/question1":
                    sendMessage(update.getMessage().getChatId(), testService.getQuestion1Message());
                    break;
                default:
                    sendTextResponse(update.getMessage().getChatId(), commandHandlerService.getUnrecognizedCommandResponse());

            }
        }
    }

    private void sendMessage(long chatId, SendMessage message){
        try{
            message.setChatId(String.valueOf(chatId));
            execute(message);
        }catch(TelegramApiException ex){
            log.error("Error occurred while sending message for user. ChatId = " + chatId + " Exception: " + ex);
        }
    }

    private void sendTextResponse(long chatId, String text){
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
