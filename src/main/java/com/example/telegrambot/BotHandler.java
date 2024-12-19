package com.example.telegrambot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class BotHandler extends TelegramLongPollingBot {

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.token}")
    private String botToken;

    private final Long user1Id = 657629367L;
    private final Long user2Id = 916962390L;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            String userMessage = update.getMessage().getText();
            String userName = update.getMessage().getFrom().getFirstName(); // Отримуємо ім'я користувача

            if ("/start".equals(userMessage)) {
                sendWelcomeMessage(chatId);
            } else if (chatId.equals(user1Id)) {
                handleUser(chatId, userMessage, userName, user2Id);
            } else if (chatId.equals(user2Id)) {
                handleUser(chatId, userMessage, userName, user1Id);
            } else {
                sendResponse(chatId, "Вибачте, але ви не авторизовані для використання цього бота.");
            }
        }
    }

    private void sendWelcomeMessage(Long chatId) {
        String welcomeText = "Вітаю! Оберіть категорію:";
        sendResponseWithKeyboard(chatId, welcomeText, List.of("Сніданок 🍳", "Суп 🍲", "Вечеря 🍝"));
    }

    private void handleUser(Long chatId, String userMessage, String userName, Long targetUserId) {
        // Перетворюємо повідомлення користувача на нижній регістр
        String message = userMessage.toLowerCase();

        switch (message) {
            case "сніданок 🍳":
                sendResponseWithKeyboard(chatId, "Для сніданку оберіть одну з наступних страв:", List.of("Омлет 🍳", "Канапочки 🥪", "Кофі ☕", "Чайочок 🍵", "Какао панкейки 🥞"));
                break;
            case "суп 🍲":
                sendResponseWithKeyboard(chatId, "Для супу оберіть одну з наступних страв:", List.of("Гречаний 🍜", "З макаронами 🍝", "Бульйон 🥣", "Рисовий 🍲", "Зелений Борщик 🥬", "Червоний Борщик 🍅"));
                break;
            case "вечеря 🍝":
                sendResponseWithKeyboard(chatId, "Для вечері оберіть одну з наступних страв:", List.of("Рагу 🥘", "Печена картопля 🥔", "Жарена картопля 🍟", "Макарони з чимось 🍝", "Піца 🍕", "Гречка з мясом 🍖"));
                break;
            case "омлет 🍳":
            case "канапочки 🥪":
            case "кофі ☕":
            case "чайочок 🍵":
            case "какао панкейки 🥞":
            case "гречаний 🍜":
            case "з макаронами 🍝":
            case "бульйон 🥣":
            case "рисовий 🍲":
            case "зелений борщик 🥬":
            case "червоний борщик 🍅":
            case "рагу 🥘":
            case "печена картопля 🥔":
            case "жарена картопля 🍟":
            case "макарони з чимось 🍝":
            case "піца 🍕":
            case "гречка з мясом 🍖":
                sendResponse(targetUserId, "Захотілося "+ userName + " на сьогодні " + userMessage + ".");
                sendResponse(chatId, "Ваша хотілка була передана вашому коханню.");
                sendWelcomeMessage(chatId); // Повертаємося до початку
                break;
            default:
                sendResponse(chatId, "Невідома команда. Оберіть: Сніданок 🍳, Суп 🍲, Вечеря 🍝.");
        }
    }

    private void sendResponseWithKeyboard(Long chatId, String text, List<String> options) {
        try {
            SendMessage message = new SendMessage();
            message.setChatId(chatId.toString());
            message.setText(text);

            // Створення клавіатури
            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            keyboardMarkup.setResizeKeyboard(true); // Автоматично змінює розмір кнопок
            keyboardMarkup.setOneTimeKeyboard(false); // Клавіатура залишатиметься видимою

            // Додавання кнопок
            List<KeyboardRow> keyboardRows = new ArrayList<>();
            for (String option : options) {
                KeyboardRow row = new KeyboardRow();
                row.add(new KeyboardButton(option));
                keyboardRows.add(row);
            }
            keyboardMarkup.setKeyboard(keyboardRows);

            message.setReplyMarkup(keyboardMarkup);

            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendResponse(Long chatId, String text) {
        try {
            SendMessage message = new SendMessage();
            message.setChatId(chatId.toString());
            message.setText(text);
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
