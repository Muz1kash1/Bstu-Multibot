package ru.bstu.service;

import org.springframework.http.ResponseEntity;
import ru.bstu.service.implementation.dto.group.GroupListDto;
import ru.bstu.service.implementation.dto.teacher.TeacherListDto;
import ru.bstu.service.implementation.dto.group.GroupScheduleDto;
import ru.bstu.service.implementation.dto.teacher.TeacherScheduleDto;

public interface CurriculumApiClient {
  ResponseEntity<TeacherListDto> getListOfTeachers();
  ResponseEntity<GroupListDto> getListOfGroups();
  ResponseEntity<TeacherScheduleDto> getScheduleForTeacher(String nameOfTeacher);
  ResponseEntity<GroupScheduleDto> getScheduleForGroup(String nameOfGroup);
}
