package ru.bstu.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.bstu.service.implementation.CurriculumApiClientImpl;

@Slf4j
@DisplayName("Тесты на проверку конвертации расписания в строки")
class CurriculumToStringConverterTest {
  CurriculumApiClientImpl curriculumApiClient = new CurriculumApiClientImpl();
  CurriculumToStringConverter curriculumToStringConverter = new CurriculumToStringConverter(curriculumApiClient);

  @Test
  @DisplayName("Тест на проверку конвертации расписания группы на сегодня в строку")
  void convertGroupCurriculumForToday() {
    String curriculumForGroupToday = curriculumToStringConverter.convertGroupCurriculumForToday("КБ-201");
    Assertions.assertNotNull(curriculumForGroupToday);
    Assertions.assertNotEquals("", curriculumForGroupToday);
    log.info(curriculumForGroupToday);
  }

  @Test
  @DisplayName("Тест на проверку конвертации расписания группы на завтра в строку")
  void convertGroupCurriculumForTomorrow() {
    String curriculumForGroupTomorrow = curriculumToStringConverter.convertGroupCurriculumForTomorrow("КБ-201");
    Assertions.assertNotNull(curriculumForGroupTomorrow);
    Assertions.assertNotEquals("", curriculumForGroupTomorrow);
    log.info(curriculumForGroupTomorrow);
  }

  @Test
  @DisplayName("Тест на проверку конвертации расписания группы на эту неделю в строку")
  void convertGroupCurriculumForThisWeek() {
    String curriculumForGroupThisWeek = curriculumToStringConverter.convertGroupCurriculumForThisWeek("КБ-201");
    Assertions.assertNotNull(curriculumForGroupThisWeek);
    Assertions.assertNotEquals("", curriculumForGroupThisWeek);
    log.info(curriculumForGroupThisWeek);
  }

  @Test
  @DisplayName("Тест на проверку конвертации расписания группы на следующую неделю в строку")
  void convertGroupCurriculumForNextWeek() {
    String curriculumForGroupNextWeek = curriculumToStringConverter.convertGroupCurriculumForNextWeek("КБ-201");
    Assertions.assertNotNull(curriculumForGroupNextWeek);
    Assertions.assertNotEquals("", curriculumForGroupNextWeek);
    log.info(curriculumForGroupNextWeek);
  }

  @Test
  @DisplayName("Тест на проверку конвертации полного расписания группы в строку")
  void convertGroupCurriculumFully() {
    String fullCurriculumForGroup = curriculumToStringConverter.convertGroupCurriculumFully("КБ-201");
    Assertions.assertNotNull(fullCurriculumForGroup);
    Assertions.assertNotEquals("", fullCurriculumForGroup);
    log.info(fullCurriculumForGroup);
  }
}