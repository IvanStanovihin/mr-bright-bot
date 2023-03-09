package com.easy.bright.bot.service;

import com.easy.bright.bot.utils.BotConstants;
import com.easy.bright.bot.utils.BotStateService;
import com.easy.bright.bot.utils.BotStateEnum;
import com.easy.bright.bot.utils.UserConstants;
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

    public SendMessage getClassInfoMessage(){
        SendMessage startMessage = new SendMessage();
        startMessage.setText("Выбери номер класса в котором учишься");
        InlineKeyboardMarkup startMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>>buttonLines = new ArrayList<>();
        List<InlineKeyboardButton> buttonsInLine = new ArrayList<>();

        InlineKeyboardButton sevenEightClassBtn = new InlineKeyboardButton();
        sevenEightClassBtn.setCallbackData(BotConstants.CLASS_7_8_BUTTON);
        sevenEightClassBtn.setText(UserConstants.CLASS_7_8);

        InlineKeyboardButton nineTenClassBtn = new InlineKeyboardButton();
        nineTenClassBtn.setCallbackData(BotConstants.CLASS_9_10_BUTTON);
        nineTenClassBtn.setText(UserConstants.CLASS_9_10);

        InlineKeyboardButton elevenClassButton = new InlineKeyboardButton();
        elevenClassButton.setCallbackData(BotConstants.CLASS_11_BUTTON);
        elevenClassButton.setText(UserConstants.CLASS_11);

        buttonsInLine.add(sevenEightClassBtn);
        buttonsInLine.add(nineTenClassBtn);
        buttonsInLine.add(elevenClassButton);
        buttonLines.add(buttonsInLine);
        startMarkup.setKeyboard(buttonLines);
        startMessage.setReplyMarkup(startMarkup);
        return startMessage;
    }

    public SendMessage getPhoneInfoMessage(){
        SendMessage startMessage = new SendMessage();
        startMessage.setText("Введи свой номер телефона");
        return startMessage;
    }

    public SendMessage getSchoolInfoMessage(){
        SendMessage startMessage = new SendMessage();
        startMessage.setText("Введи название школы, в которой ты учишься");
        return startMessage;
    }

    public SendMessage getStartSessionInfo(){
        SendMessage startSessionMessage = new SendMessage();
        startSessionMessage.setText("\\nДля того чтобы начать тестирование нажмите кнопку start" +
                "\nПомните, что тестирование можно пройти только один раз!\" +\n" +
                "\nЖелаю удачи ;)");
        InlineKeyboardMarkup startSessionMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>>buttonLines = new ArrayList<>();
        List<InlineKeyboardButton>buttonInLine = new ArrayList<>();
        InlineKeyboardButton startSessionButton = new InlineKeyboardButton();
        startSessionButton.setText("Начать тестирование)");
        startSessionButton.setCallbackData(BotConstants.START_SESSION_BUTTON);
        buttonInLine.add(startSessionButton);
        buttonLines.add(buttonInLine);
        startSessionMarkup.setKeyboard(buttonLines);
        startSessionMessage.setReplyMarkup(startSessionMarkup);
        return startSessionMessage;
    }

    public SendMessage getNameInfoMessage(){
        SendMessage startMessage = new SendMessage();
        startMessage.setText("Введи своё имя и фамилию" +
                "\nВводить нужно через пробел в формате: \"имя фамилия\" :)");
        return startMessage;
    }

    public SendMessage getCompleteRegistrationMessage(){
        SendMessage startMessage = new SendMessage();
        startMessage.setText("Вы успешно зарегистрировались!");
        return startMessage;
    }

    public SendMessage getStartMessage(){
        SendMessage startMessage = new SendMessage();
        startMessage.setText("Добро пожаловать в Mr Bright Bot :)" +
                "\nДля того чтобы сохранить твой результат, я должен получше тебя узнать" +
                "\nЧтобы познакомиться нажимай кнопку РЕГИСТРАЦИЯ 8)");
        InlineKeyboardMarkup startMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>>buttonLines = new ArrayList<>();
        List<InlineKeyboardButton> buttonsInLine = new ArrayList<>();
        InlineKeyboardButton startButton = new InlineKeyboardButton();

        startButton.setCallbackData(BotConstants.START_BUTTON);
        startButton.setText("Start");

        buttonsInLine.add(startButton);
        buttonLines.add(buttonsInLine);
        startMarkup.setKeyboard(buttonLines);
        startMessage.setReplyMarkup(startMarkup);
        return startMessage;
    }
}
