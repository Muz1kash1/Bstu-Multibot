package ru.bstu.service.implementation;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bstu.service.UpdateProducer;

/**
 * Реализцация отправки сообщений в брокер
 */
@Service
@Slf4j
@AllArgsConstructor
public class UpdateProducerImpl implements UpdateProducer {
  private final RabbitTemplate rabbitTemplate;

  /**
   * Отправляет в очередь брокера сообщение с контентом
   * @param rabbitQueue название очереди в брокере сообщений
   * @param update контент который надо отправить в очередь
   */
  @Override
  @SneakyThrows
  public void produce(final String rabbitQueue, final Update update) {
    if (update.getMessage().getText() != null) {
      log.info(update.getMessage().getText());
      rabbitTemplate.convertAndSend(rabbitQueue, update);
    }
    if (update.getMessage().getPhoto() != null) {
      log.info(update.getMessage().getPhoto().toString());
      rabbitTemplate.convertAndSend(rabbitQueue, update);
    }
    if (update.getMessage().getDocument() != null) {
      log.info(update.getMessage().getDocument().toString());
      rabbitTemplate.convertAndSend(rabbitQueue, update);
    }
    if (update.getMessage().getAudio() != null) {
      log.info(update.getMessage().getAudio().getFileName());
      rabbitTemplate.convertAndSend(rabbitQueue,update);
    }
  }
}
