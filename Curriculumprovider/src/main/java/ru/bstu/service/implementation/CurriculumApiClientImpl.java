package ru.bstu.service.implementation;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.bstu.service.CurriculumApiClient;
import ru.bstu.service.implementation.dto.group.GroupListDto;
import ru.bstu.service.implementation.dto.group.GroupScheduleDto;
import ru.bstu.service.implementation.dto.teacher.TeacherListDto;
import ru.bstu.service.implementation.dto.teacher.TeacherScheduleDto;
import java.time.Duration;
import java.util.Collections;

/** Класс клиент для запросов к сервису расписания */
@Component
public class CurriculumApiClientImpl implements CurriculumApiClient {
  private final RestTemplate restTemplate;

  /** Настройка клиента */
  public CurriculumApiClientImpl() {
    RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
    this.restTemplate =
        restTemplateBuilder
            .setConnectTimeout(Duration.ofMillis(5000))
            .setReadTimeout(Duration.ofMillis(5000))
            .messageConverters(converter)
            .build();
  }

  /**
   * Метод возвращающий список всех учителей
   *
   * @return дто содержащий список всех учителей
   */
  @Override
  public ResponseEntity<TeacherListDto> getListOfTeachers() {
    return restTemplate.exchange(
        "http://localhost:8083/teacher/list", HttpMethod.GET, null, TeacherListDto.class);
  }

  /**
   * Метод возвращающий список всех групп
   *
   * @return дто содержащий список всех групп
   */
  @Override
  public ResponseEntity<GroupListDto> getListOfGroups() {
    return restTemplate.exchange(
        "http://localhost:8083/group/list", HttpMethod.GET, null, GroupListDto.class);
  }

  /**
   * Метод возвращающий текущее расписание для учителя
   *
   * @param teacherName ФИО учителя
   * @return дто содержащий текущее расписание учителя
   */
  @Override
  public ResponseEntity<TeacherScheduleDto> getScheduleForTeacher(String teacherName) {
    return restTemplate.exchange(
        "http://localhost:8083/teacher/schedule?name=" + teacherName,
        HttpMethod.GET,
        null,
        TeacherScheduleDto.class);
  }

  /**
   * Метод возвращающий текущее расписание для группы
   *
   * @param nameOfGroup название группы
   * @return дто содержащий текущее расписание группы
   */
  @Override
  public ResponseEntity<GroupScheduleDto> getScheduleForGroup(String nameOfGroup) {
    return restTemplate.exchange(
        "http://localhost:8083/group/schedule?name=" + nameOfGroup,
        HttpMethod.GET,
        null,
        GroupScheduleDto.class);
  }
}
