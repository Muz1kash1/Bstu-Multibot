package ru.bstu.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
@Slf4j
@Service
public class CurriculumUpdatesProcessor {
  /**
   * Обрабатывает пришедшее обновление с требованием расписания
   * @param update обновление
   * @return возвращает обновление с расписанием
   * @throws Exception исключение в случае некоректного обновления
   */
  public SendMessage processCurriculumUpdate(final Update update) throws Exception {
    SendMessage sendMessage = new SendMessage();
    sendMessage.setChatId(update.getMessage().getChatId());
    if (update != null) {
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
    }
    else throw new Exception("Передан некорректный запрос");
    return sendMessage;
  }
}
