package ru.bstu.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.bstu.service.CurriculumApiClient;
import ru.bstu.service.implementation.dto.group.DayDto;
import ru.bstu.service.implementation.dto.group.SubjectDto;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс преобразующий получаемое расписание для группы в строки для отправки.
 */
@Component
@AllArgsConstructor
public class CurriculumToStringConverter {
  private final CurriculumApiClient curriculumApiClient;

  /**
   * Метод возвращающий конвертированное расписание для группы на день этой недели.
   *
   * @param groupName имя группы
   * @param dayOfWeek номер дня недели
   * @return расписание для группы на день
   */
  private String convertGroupCurriculumForDayOfThisWeek(String groupName, int dayOfWeek) {
    DayDto today = Objects.requireNonNull(curriculumApiClient.getScheduleForGroup(groupName).getBody())
      .getWeeks()
      .get(0)
      .getDays()
      .get(dayOfWeek);

    return convertDayToString(today);
  }

  /**
   * Метод возвращающий конвертированное расписание для группы на день следующей недели.
   *
   * @param groupName имя группы
   * @param dayOfWeek номер дня недели
   * @return расписание для группы на день
   */
  private String convertGroupCurriculumForDayOfNextWeek(String groupName, int dayOfWeek) {
    DayDto today = Objects.requireNonNull(curriculumApiClient.getScheduleForGroup(groupName).getBody())
      .getWeeks()
      .get(1)
      .getDays()
      .get(dayOfWeek);

    return convertDayToString(today);
  }

  /**
   * Метод конверсии дня недели в строку.
   *
   * @param today день недели
   * @return строка из дня недели
   */
  private static String convertDayToString(final DayDto today) {
    String response = """
      """;
    response += "\n";
    response += today.getDate();
    response += "\n";
    response += today.getDayOfWeek();
    response += "\n";


    StringBuilder responseBuilder = new StringBuilder(response);
    if (today.getSubjects() != null) {
      for (
        SubjectDto subject : today.getSubjects()
      ) {
        if (subject.getNumber() != null) {
          responseBuilder.append(subject.getNumber());
          responseBuilder.append("\n");
        }
        if (subject.getType() != null) {
          responseBuilder.append(subject.getType());
          responseBuilder.append("\n");
        }
        responseBuilder.append(subject.getName());
        responseBuilder.append("\n");
        if (subject.getStart() != null) {
          responseBuilder.append(subject.getStart());
          responseBuilder.append("\n");
        }
        if (subject.getEnd() != null) {
          responseBuilder.append(subject.getEnd());
          responseBuilder.append("\n");
        }
        if (subject.getClassrooms() != null) {
          for (String classroom : subject.getClassrooms()) {
            responseBuilder.append(classroom);
            responseBuilder.append("\n");
          }
        }
        if (subject.getTeachers() != null) {
          for (String teacher : subject.getTeachers()) {
            responseBuilder.append(teacher);
            responseBuilder.append("\n");
          }
        }
      }
    }
    response = responseBuilder.toString();

    return response;
  }

  /**
   * Метод возвращающий конвертированное расписание группы на сегодня.
   *
   * @param groupName имя группы
   * @return расписание группы на сегодня
   */
  public String convertGroupCurriculumForToday(String groupName) {
    return convertGroupCurriculumForDayOfThisWeek(groupName, LocalDateTime.now().getDayOfWeek().minus(1).getValue());
  }

  /**
   * Метод возвращающий конвертированное расписание группы на завтра.
   *
   * @param groupName имя группы
   * @return расписание группы на завтра
   */
  public String convertGroupCurriculumForTomorrow(String groupName) {
    if (LocalDateTime.now().getDayOfWeek() == DayOfWeek.SUNDAY) {
      return convertGroupCurriculumForDayOfNextWeek(groupName, 0);
    } else {
      return convertGroupCurriculumForDayOfThisWeek(groupName, LocalDateTime.now().getDayOfWeek().getValue());
    }
  }

  /**
   * Метод возвращающий конвертированное расписание группы на всю текущую неделю.
   *
   * @param groupName имя группы
   * @return расписание группы на текущую неделю
   */
  public String convertGroupCurriculumForThisWeek(String groupName) {
    StringBuilder response = new StringBuilder();
    for (int i = 0; i < 6; i++) {
      response.append(convertGroupCurriculumForDayOfThisWeek(groupName, i));
    }
    return response.toString();
  }

  /**
   * Метод возвращающий конвертированное расписание группы на всю следующую неделю.
   *
   * @param groupName имя группы
   * @return расписание группы на следующую неделю
   */
  public String convertGroupCurriculumForNextWeek(String groupName) {
    StringBuilder response = new StringBuilder();
    for (int i = 0; i < 6; i++) {
      response.append(convertGroupCurriculumForDayOfNextWeek(groupName, i));
    }
    return response.toString();
  }

  /**
   * Метод возвращающий полное конвертированное расписание группы.
   * @param groupName имя группы
   * @return полное расписание группы
   */
  public String convertGroupCurriculumFully(String groupName) {
    String response = convertGroupCurriculumForThisWeek(groupName);
    response += convertGroupCurriculumForNextWeek(groupName);
    return response;
  }


}
