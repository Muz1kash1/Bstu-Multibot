package ru.bstu.service.implementation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.bstu.controller.UpdateController;
import ru.bstu.service.AnswerConsumer;

import static ru.bstu.commotrabbitmq.RabbitQueue.ANSWER_MESSAGE_UPDATE;

/**
 * Класс реализация приемщика ответов
 */
@AllArgsConstructor
@Slf4j
@Service
public class AnswerConsumerImpl implements AnswerConsumer {
  private final UpdateController updateController;
  /**
   * принимает ответ
   *
   * @param sendMessage принимаемый ответ
   */
  @Override
  @RabbitListener(queues = ANSWER_MESSAGE_UPDATE)
  public void consume(final SendMessage sendMessage) {
    log.info(" Вот щас отправится отвечаю");
    updateController.setView(sendMessage);
  }
}
