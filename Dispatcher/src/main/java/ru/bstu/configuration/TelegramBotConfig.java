package ru.bstu.configuration;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * Класс конфигурации телеграм бота
 */
@Configuration
public class TelegramBotConfig {
  /**
   * Создаем бин апи телеграм бота(без этого метода он не оживет)
   *
   * @return новый бин апи телеграма
   */
  @Bean
  @SneakyThrows
  TelegramBotsApi telegramBotsApi() {
    return new TelegramBotsApi(DefaultBotSession.class);
  }
}
