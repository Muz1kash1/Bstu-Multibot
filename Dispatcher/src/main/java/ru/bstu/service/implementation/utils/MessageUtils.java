package ru.bstu.service.implementation.utils;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс для генерации контента для отправки
 */
@Component
public class MessageUtils {
  /**
   * Генерирует сообщение для отправки с текстом
   *
   * @param update полученное сообщение
   * @param text   текст
   * @return сообщение для отправки с текстом
   */
  public SendMessage generateSendMessageWithText(Update update, String text) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(update.getMessage().getChatId());
    sendMessage.setText(text);
    return sendMessage;
  }
}
