package ru.bstu;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bstu.controller.UpdateController;
import javax.annotation.PostConstruct;

@Slf4j
@Component
/**
 * Класс для внешнего телеграм бота и взаимодействия с его API
 */
public class TelegramBot extends TelegramLongPollingBot {
  private final TelegramBotsApi telegramBotsApi;
  @Value("${bot.name}")
  private String botName;
  @Value("${bot.token}")
  private String botToken;
  private final UpdateController updateController;

  public TelegramBot(final TelegramBotsApi telegramBotsApi, final UpdateController updateController) {
    this.telegramBotsApi = telegramBotsApi;
    this.updateController = updateController;
  }

  /**
   * Регистрация бота (без этого метода ничего не заработает)
   *
   * @throws TelegramApiException
   */
  @PostConstruct
  private void botRegistration() throws TelegramApiException {
    telegramBotsApi.registerBot(this);
  }

  /**
   * Регистрация бота в контролллере во избежание циклической зависимости
   */
  @PostConstruct
  private void controllerInit() {
    updateController.registerBot(this);
  }


  /**
   * Имя ботяры
   *
   * @return возвращает имя ботяры
   */
  @Override
  public String getBotUsername() {
    return botName;
  }

  /**
   * токен ботяры
   *
   * @return возвращает токен ботяры
   */
  @Override
  public String getBotToken() {
    return botToken;
  }

  /**
   * Реакция на полученное обновление в чате
   *
   * @param update обновление в чате (звук, текст, итд.)
   */
  @SneakyThrows
  @Override
  public void onUpdateReceived(final Update update) {
    updateController.processUpdate(update);
  }
}
