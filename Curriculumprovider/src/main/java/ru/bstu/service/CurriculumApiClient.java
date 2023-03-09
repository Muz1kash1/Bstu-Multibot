package ru.bstu.service;

import org.springframework.http.ResponseEntity;
import ru.bstu.service.implementation.dto.group.GroupListDto;
import ru.bstu.service.implementation.dto.group.GroupScheduleDto;
import ru.bstu.service.implementation.dto.teacher.TeacherListDto;
import ru.bstu.service.implementation.dto.teacher.TeacherScheduleDto;

/** Интерфейс для клиента взаимодействия с апи сервиса расписания */
public interface CurriculumApiClient {
  /**
   * Метод возвращающий список учителей
   *
   * @return дто список учителей
   */
  ResponseEntity<TeacherListDto> getListOfTeachers();

  /**
   * Метод возвращающий список групп
   *
   * @return дто список групп
   */
  ResponseEntity<GroupListDto> getListOfGroups();

  /**
   * Возвращает объект содержащий текущее расписание учителя
   *
   * @param nameOfTeacher ФИО учителя
   * @return объект содержащий текущее расписание учителя
   */
  ResponseEntity<TeacherScheduleDto> getScheduleForTeacher(String nameOfTeacher);

  /**
   * Возвращает объект содержащий текущее расписание группы
   *
   * @param nameOfGroup Имя группы
   * @return объект содержащий текущее расписание группы
   */
  ResponseEntity<GroupScheduleDto> getScheduleForGroup(String nameOfGroup);
}
