package com.easy.bright.bot.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class SendMessageService {

    public SendMessage getStartMessage(){
        SendMessage startMessage = new SendMessage();
        startMessage.setText("Добро пожаловать в Mr Bright Bot :)" +
                "\nДля того чтобы начать тестирование нажмите кнопку start" +
                "\nПомните, что тестирование можно пройти только один раз!" +
                "\nЖелаю удачи)))");
        InlineKeyboardMarkup startMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>>buttonLines = new ArrayList<>();
        List<InlineKeyboardButton> buttonsInLine = new ArrayList<>();
        InlineKeyboardButton startButton = new InlineKeyboardButton();

        startButton.setCallbackData("WELCOME_BUTTON");
        startButton.setText("Start");

        buttonsInLine.add(startButton);
        buttonLines.add(buttonsInLine);
        startMarkup.setKeyboard(buttonLines);
        startMessage.setReplyMarkup(startMarkup);
        return startMessage;
    }
}
