package com.example.telegrambot;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import com.example.telegrambot.BotHandler;

@Service
public class NotificationService {
    private final BotHandler botHandler;

    public NotificationService(BotHandler botHandler) {
        this.botHandler = botHandler;
    }

    public void notifyUser(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            botHandler.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
