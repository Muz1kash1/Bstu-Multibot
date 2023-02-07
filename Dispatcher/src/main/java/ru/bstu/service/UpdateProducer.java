package ru.bstu.service;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Отправляющий интерфейс
 */
public interface UpdateProducer {
  /**
   * Отправляющий в очередь метод
   *
   * @param rabbitQueue название очереди
   * @param update      то что надо отправить
   */
  void produce(String rabbitQueue, Update update);
}
