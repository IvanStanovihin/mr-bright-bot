package com.easy.bright.bot.service;

import com.easy.bright.bot.utils.BotConstants;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для реализации логики тестирования пользователей
 */
@Service
@Data
public class TestService {

    public SendMessage getQuestion1Message(){
        SendMessage message = new SendMessage();
        message.setText("Вопрос № 1");
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>>rowsInLine = new ArrayList<>();
        List<InlineKeyboardButton>rowWithButtons1 = new ArrayList<>();
        List<InlineKeyboardButton>rowWithButtons2 = new ArrayList<>();

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Вариант ответа 1");
        button1.setCallbackData(BotConstants.QUESTION_1_ANSWER_1_ID);

        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Вариант ответа 2");
        button2.setCallbackData(BotConstants.QUESTION_1_ANSWER_2_ID);

        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("Вариант ответа 3");
        button3.setCallbackData(BotConstants.QUESTION_1_ANSWER_3_ID);

        InlineKeyboardButton button4 = new InlineKeyboardButton();
        button4.setText("Вариант ответа 4");
        button4.setCallbackData(BotConstants.QUESTION_1_ANSWER_4_ID);

        rowWithButtons1.add(button1);
        rowWithButtons1.add(button2);
        rowWithButtons2.add(button3);
        rowWithButtons2.add(button4);
        rowsInLine.add(rowWithButtons1);
        rowsInLine.add(rowWithButtons2);
        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);
        return message;
    }


}
