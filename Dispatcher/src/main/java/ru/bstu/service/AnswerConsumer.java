package ru.bstu.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * Принимающий извне интерфейс
 */
public interface AnswerConsumer {
  /**
   * Принимающий из вне метод
   *
   * @param sendMessage
   */
  void consume(SendMessage sendMessage);
}
