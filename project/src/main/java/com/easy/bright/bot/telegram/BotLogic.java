package com.easy.bright.bot.telegram;

import com.easy.bright.bot.configuration.AppConfig;
import com.easy.bright.bot.service.CommandHandlerService;
import com.easy.bright.bot.service.MessageService;
import com.easy.bright.bot.service.SendMessageService;
import com.easy.bright.bot.service.TestService;
import com.easy.bright.bot.utils.BotConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class BotLogic extends TelegramLongPollingBot {

    private AppConfig appConfig;
    private TestService testService;
    private CommandHandlerService commandHandlerService;
    private SendMessageService sendMessageService;
    private MessageService messageService;

    public BotLogic(AppConfig appConfig, CommandHandlerService commandHandlerService,
                    TestService testService, SendMessageService sendMessageService, MessageService messageService) {
        this.appConfig = appConfig;
        this.commandHandlerService = commandHandlerService;
        this.testService = testService;
        this.sendMessageService = sendMessageService;
        this.messageService = messageService;
    }


    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasCallbackQuery()) {
            String callBackData = update.getCallbackQuery().getData();
            switch (callBackData) {
                case BotConstants.QUESTION_1_ANSWER_1_ID:
                case BotConstants.QUESTION_1_ANSWER_2_ID:
                case BotConstants.QUESTION_1_ANSWER_3_ID:
                case BotConstants.QUESTION_1_ANSWER_4_ID:
                    deleteMessage(update.getCallbackQuery().getMessage().getChatId(), update.getCallbackQuery().getMessage().getMessageId());
                    sendMessage(update.getCallbackQuery().getMessage().getChatId(), testService.getQuestion2Message());
                    break;
                case BotConstants.QUESTION_2_ANSWER_1_ID:
                case BotConstants.QUESTION_2_ANSWER_2_ID:
                case BotConstants.QUESTION_2_ANSWER_3_ID:
                case BotConstants.QUESTION_2_ANSWER_4_ID:
                    deleteMessage(update.getCallbackQuery().getMessage().getChatId(), update.getCallbackQuery().getMessage().getMessageId());
                    sendMessage(update.getCallbackQuery().getMessage().getChatId(), testService.getQuestion3Message());
                    break;
                case BotConstants.QUESTION_3_ANSWER_1_ID:
                case BotConstants.QUESTION_3_ANSWER_2_ID:
                case BotConstants.QUESTION_3_ANSWER_3_ID:
                case BotConstants.QUESTION_3_ANSWER_4_ID:
                    deleteMessage(update.getCallbackQuery().getMessage().getChatId(), update.getCallbackQuery().getMessage().getMessageId());
                    sendMessage(update.getCallbackQuery().getMessage().getChatId(), testService.getQuestion4Message());
                    break;
                case BotConstants.QUESTION_4_ANSWER_1_ID:
                case BotConstants.QUESTION_4_ANSWER_2_ID:
                case BotConstants.QUESTION_4_ANSWER_3_ID:
                case BotConstants.QUESTION_4_ANSWER_4_ID:
                    deleteMessage(update.getCallbackQuery().getMessage().getChatId(), update.getCallbackQuery().getMessage().getMessageId());
                    sendTextResponse(update.getCallbackQuery().getMessage().getChatId(), "Поздравляю, вы завершили тестирование!!!");
                    break;
                default:
                    sendTextResponse(update.getCallbackQuery().getMessage().getChatId(), "Извините, кнопка не распознана!");
                    break;
            }
        } else if (update.hasMessage() && update.getMessage().hasText()){
            String messageText = update.getMessage().getText();
            switch (messageText) {
                case "/start":
                    sendMessage(update.getMessage().getChatId(), messageService.handleStartCommand(update));
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

    private void sendMessage(long chatId, SendMessage message) {
        try {
            message.setChatId(String.valueOf(chatId));
            execute(message);
        } catch (TelegramApiException ex) {
            log.error("Error occurred while sending message for user. ChatId = " + chatId + " Exception: " + ex);
        }
    }

    private void deleteMessage(long chatId, long messageId) {
        DeleteMessage messageForDelete = new DeleteMessage();
        messageForDelete.setChatId(String.valueOf(chatId));
        messageForDelete.setMessageId((int) messageId);
        try {
            execute(messageForDelete);
        } catch (TelegramApiException ex) {
            log.error("Error occurred in method deleteMessage. ChatId = " + chatId + " MessageId = " + messageId + " Exception: " + ex);
        }
    }

    private void sendEditMessage(long chatId, EditMessageText message) {
        try {
            message.setChatId(String.valueOf(chatId));
            execute(message);
        } catch (TelegramApiException ex) {
            log.error("Error occurred while sending message for user. ChatId = " + chatId + " Exception: " + ex);
        }
    }

    private void sendTextResponse(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException ex) {
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
