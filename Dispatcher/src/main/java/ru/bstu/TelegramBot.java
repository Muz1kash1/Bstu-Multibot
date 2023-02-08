package ru.bstu;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bstu.controller.UpdateController;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

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
//    updateController.processUpdate(update);
    if (update.getMessage().getText().equals("Расписание \uD83D\uDDD3")) {
      createCurriculumKeyboard(update);
    } else {
      updateController.processUpdate(update);
      createStudentKeyboard(update);
    }

  }

  private void createCurriculumKeyboard(final Update update) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.enableMarkdown(true);

    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    sendMessage.setReplyMarkup(replyKeyboardMarkup);
    replyKeyboardMarkup.setSelective(true);
    replyKeyboardMarkup.setResizeKeyboard(true);
    replyKeyboardMarkup.setOneTimeKeyboard(true);

    KeyboardRow keyboardButtonListFirstRow = new KeyboardRow();
//    KeyboardRow keyboardButtonListSecondRow = new KeyboardRow();

    keyboardButtonListFirstRow.add("Расписание на день");
    keyboardButtonListFirstRow.add("Расписание на завтра");
    keyboardButtonListFirstRow.add("Расписание на всю неделю");

    List<KeyboardRow> keyboard = new ArrayList<>();
    keyboard.add(keyboardButtonListFirstRow);
//    keyboard.add(keyboardButtonListSecondRow);
    replyKeyboardMarkup.setKeyboard(keyboard);

    sendMessage.setChatId(update.getMessage().getChatId());
    sendMessage.setReplyToMessageId(update.getMessage().getMessageId());
    sendMessage.setText("""
      1 - Расписание на день
      2 - Расписание на завтра
      3 - Расписание на эту неделю
      """);
//    execute(sendMessage);
    updateController.setView(sendMessage);
  }

  public void createStudentKeyboard(Update update) throws TelegramApiException {
    SendMessage sendMessage = new SendMessage();
    sendMessage.enableMarkdown(true);

    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    sendMessage.setReplyMarkup(replyKeyboardMarkup);
    replyKeyboardMarkup.setSelective(true);
    replyKeyboardMarkup.setResizeKeyboard(true);
    replyKeyboardMarkup.setOneTimeKeyboard(true);

    KeyboardRow keyboardButtonListFirstRow = new KeyboardRow();
//    KeyboardRow keyboardButtonListSecondRow = new KeyboardRow();

    keyboardButtonListFirstRow.add("Расписание \uD83D\uDDD3");
    keyboardButtonListFirstRow.add("Не расписание ♿️");

    List<KeyboardRow> keyboard = new ArrayList<>();
    keyboard.add(keyboardButtonListFirstRow);
//    keyboard.add(keyboardButtonListSecondRow);
    replyKeyboardMarkup.setKeyboard(keyboard);

    sendMessage.setChatId(update.getMessage().getChatId());
    sendMessage.setReplyToMessageId(update.getMessage().getMessageId());
    sendMessage.setText("""
      1 - Расписание
      2 - Не расписание
      """);
//    execute(sendMessage);
    updateController.setView(sendMessage);

  }
}
