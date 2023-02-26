package ru.bstu.utils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bstu.service.CurriculumApiClient;

@Slf4j
@Service
@AllArgsConstructor
public class CurriculumUpdatesProcessor {
  final CurriculumApiClient curriculumApiClient;

  /**
   * Обрабатывает пришедшее обновление с требованием расписания
   *
   * @param update обновление
   * @return возвращает обновление с расписанием
   */
  public SendMessage processCurriculumUpdate(final Update update) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(update.getMessage().getChatId());

    log.info("получено текстовое сообщение из ребита " + update.getMessage().getText());
    if (update.getMessage().getText().equals("Расписание на день")) {
      sendMessage.setText(
          //          update.getMessage().getText() +
          "типа строка с расписанием на седня от влада");
    } else if (update.getMessage().getText().equals("Расписание на завтра")) {
      sendMessage.setText(
          //          update.getMessage().getText() +
          " типа строка с расписанием на завтра от влада");
    } else if (update.getMessage().getText().equals("Расписание на неделю")) {
      sendMessage.setText(
          //          update.getMessage().getText() +
          "типа строка с расписанием на неделю от влада");
    } else {
      sendMessage.setText("Нет такого варианта");
    }
    return sendMessage;
  }
}
