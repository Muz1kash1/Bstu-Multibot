package ru.bstu.service.implementation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bstu.service.ConsumerService;
import ru.bstu.service.ProducerService;

import static ru.bstu.commotrabbitmq.RabbitQueue.AUDIO_MESSAGE_UPDATE;
import static ru.bstu.commotrabbitmq.RabbitQueue.DOC_MESSAGE_UPDATE;
import static ru.bstu.commotrabbitmq.RabbitQueue.PHOTO_MESSAGE_UPDATE;
import static ru.bstu.commotrabbitmq.RabbitQueue.TEXT_MESSAGE_UPDATE;

@Service
@Slf4j
@AllArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {
  private final ProducerService producerService;

  /**
   * Принять обновление из очереди текстовых сообщений
   * @param update обновление
   */
  @Override
  @RabbitListener(queues = TEXT_MESSAGE_UPDATE)
  public void consumeTextMessageUpdate(final Update update) {
    if (update != null) {
      log.info("получено текстовое сообщение из ребита " + update.getMessage().getText());

      SendMessage sendMessage = new SendMessage();
      sendMessage.setChatId(update.getMessage().getChatId());
      sendMessage.setText(update.getMessage().getText() + " но это все получено из ноды");
      producerService.produceAnswer(sendMessage);
    }

  }

  /**
   * Принять обновление из очереди документов
   * @param update обновление
   */
  @Override
  @RabbitListener(queues = DOC_MESSAGE_UPDATE)
  public void consumeDocMessageUpdate(final Update update) {
    log.info("получено документальное сообщение из ноды");
  }

  /**
   * Принять обновление из очереди фотографий
   * @param update обновление
   */
  @Override
  @RabbitListener(queues = PHOTO_MESSAGE_UPDATE)
  public void consumePhotoMessageUpdate(final Update update) {
    log.info("получено фото сообщение из ноды");
  }

  /**
   * Принять обновление из очереди с аудиозаписями
   * @param update обновление
   */
  @Override
  @RabbitListener(queues = AUDIO_MESSAGE_UPDATE)
  public void consumeAudioMessageUpdate(final Update update) {
    log.info("получено аудио сообщение из ноды");
  }
}
