package ru.bstu.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface ProducerService {
  /**
   * Метод производящий ответ к диспатчеру
   *
   * @param sendMessage произведенное сообщение
   */
  void produceAnswer(SendMessage sendMessage);
}
