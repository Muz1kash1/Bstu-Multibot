package ru.bstu.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bstu.TelegramBot;
import ru.bstu.commotrabbitmq.RabbitQueue;
import ru.bstu.service.UpdateProducer;
import ru.bstu.service.implementation.utils.MessageUtils;

/**
 * Класс выполняющий функцию контроллера приходящих обновлений
 */
@Component
@Slf4j
public class UpdateController {
  private TelegramBot telegramBot;
  private final MessageUtils messageUtils;
  private final UpdateProducer updateProducer;


  public UpdateController(final MessageUtils messageUtils, final UpdateProducer updateProducer) {
    this.messageUtils = messageUtils;
    this.updateProducer = updateProducer;
  }


  /**
   * Регистрация бота в контроллере
   *
   * @param telegramBot наш телеграм бот
   */
  public void registerBot(TelegramBot telegramBot) {
    this.telegramBot = telegramBot;
  }

  /**
   * Первичная валидация информации
   *
   * @param update пришедший апдейт
   */
  public void processUpdate(Update update) {
    if (update == null) {
      log.error("Ничего не пришло");
      return;
    }
    if (update.getMessage() != null) {
      distributeMessagesByType(update);
    } else {
      log.error("такой тип сообщений не поддерживается " + update.getMessage().getText());
    }
  }

  /**
   * Распределяет сообщения в зависимости от типа контента
   *
   * @param update сообщение
   */
  @SneakyThrows
  private void distributeMessagesByType(Update update) {
    if (update.getMessage().getText() != null) {
//      setView(setInlineKeyboardMarkup(update));
      processTextMessage(update);
    } else if (update.getMessage().getAudio() != null) {
      processAudioMessage(update);
    } else if (update.getMessage().getDocument() != null) {
      processDocumentMessage(update);
    } else if (update.getMessage().getPhoto() != null) {
      processPhotoMessage(update);
    } else {
      setUnsupportedMessageTypeView(update);
    }
  }

  /**
   * Для неподдерживаемых типов сообщений
   *
   * @param update обновление
   */
  private void setUnsupportedMessageTypeView(final Update update) {
    setView(messageUtils.generateSendMessageWithText(update, "неподдерживаемый тип сообщения"));
  }

  /**
   * Отправляет сообщение
   *
   * @param sendMessage сообщение для отправки
   */
  @SneakyThrows
  public void setView(final SendMessage sendMessage) {
    telegramBot.execute(sendMessage);
  }

  /**
   * обработать сообщение с фото
   *
   * @param update сообощение с фото
   */
  private void processPhotoMessage(final Update update) {
    updateProducer.produce(RabbitQueue.PHOTO_MESSAGE_UPDATE, update);
    setFileIsReceivedView(update);
  }

  /**
   * обработать сообщение с документом
   *
   * @param update сообщение с документом
   */
  private void processDocumentMessage(final Update update) {
    updateProducer.produce(RabbitQueue.DOC_MESSAGE_UPDATE, update);
    setFileIsReceivedView(update);

  }

  /**
   * Ответить о том, что файл получен
   *
   * @param update полученное сообщение с файлом
   */
  private void setFileIsReceivedView(final Update update) {
    setView(messageUtils.generateSendMessageWithText(update, "Файл получен, обрабатывается..."));
  }

  /**
   * обработать сообщение с аудио
   *
   * @param update сообщение с аудио
   */
  private void processAudioMessage(final Update update) {
    updateProducer.produce(RabbitQueue.AUDIO_MESSAGE_UPDATE, update);
  }

  /**
   * обработать сообщение с текстом
   *
   * @param update сообщение с текстом
   */
  private void processTextMessage(final Update update) {
    updateProducer.produce(RabbitQueue.TEXT_MESSAGE_UPDATE, update);
  }
}
