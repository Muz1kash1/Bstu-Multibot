package ru.bstu.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import ru.bstu.service.CurriculumApiClient;
import ru.bstu.service.implementation.dto.group.GroupListDto;
import ru.bstu.service.implementation.dto.group.GroupScheduleDto;
import ru.bstu.service.implementation.dto.teacher.TeacherListDto;
import ru.bstu.service.implementation.dto.teacher.TeacherScheduleDto;

@Slf4j
@DisplayName("Тесты клиента для сервиса расписания")
class CurriculumApiClientImplTest {
  CurriculumApiClient curriculumApiClient = new CurriculumApiClientImpl();

  @Test
  @DisplayName("Тест на получение списка всех учителей")
  void getListOfTeachers() {
    ResponseEntity<TeacherListDto> teachers = curriculumApiClient.getListOfTeachers();
    Assertions.assertEquals(HttpStatusCode.valueOf(200), teachers.getStatusCode());
    Assertions.assertNotEquals(true, teachers.getBody().getTeacherNames().isEmpty());
    Assertions.assertFalse(teachers.getBody().getTeacherNames().isEmpty());
  }

  @Test
  @DisplayName("Тест на получение списка всех групп")
  void getListOfGroups() {
    ResponseEntity<GroupListDto> groups = curriculumApiClient.getListOfGroups();
    Assertions.assertEquals(HttpStatusCode.valueOf(200), groups.getStatusCode());
    Assertions.assertNotEquals(true, groups.getBody().getGroupNames().isEmpty());
    Assertions.assertFalse(groups.getBody().getGroupNames().isEmpty());
  }

  @Test
  @DisplayName("Тест на получение расписания для преподавателя")
  void getScheduleForTeacher() {
    ResponseEntity<TeacherScheduleDto> scheduleForTeacher =
        curriculumApiClient.getScheduleForTeacher("Амелин Павел Андреевич");
    Assertions.assertEquals(HttpStatusCode.valueOf(200), scheduleForTeacher.getStatusCode());
    Assertions.assertFalse(scheduleForTeacher.getBody().getWeeks().isEmpty());
    Assertions.assertEquals(2, scheduleForTeacher.getBody().getWeeks().size());
    Assertions.assertFalse(scheduleForTeacher.getBody().getWeeks().get(0).getDays().isEmpty());
    Assertions.assertEquals(
        "Понедельник",
        scheduleForTeacher.getBody().getWeeks().get(0).getDays().get(0).getDayOfWeek());
  }

  @Test
  @DisplayName("Тест на получение расписания группы")
  void getScheduleForGroup() {
    ResponseEntity<GroupScheduleDto> scheduleForGroup =
        curriculumApiClient.getScheduleForGroup("КБ-201");
    Assertions.assertEquals(HttpStatusCode.valueOf(200), scheduleForGroup.getStatusCode());
    Assertions.assertFalse(scheduleForGroup.getBody().getWeeks().isEmpty());
    Assertions.assertEquals(2, scheduleForGroup.getBody().getWeeks().size());
    Assertions.assertFalse(scheduleForGroup.getBody().getWeeks().get(0).getDays().isEmpty());
    Assertions.assertEquals(
        "Понедельник",
        scheduleForGroup.getBody().getWeeks().get(0).getDays().get(0).getDayOfWeek());
  }
}
