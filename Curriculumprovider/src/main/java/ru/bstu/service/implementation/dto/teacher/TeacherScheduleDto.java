package ru.bstu.service.implementation.dto.teacher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherScheduleDto {
  @JsonProperty("weeks")
  List<WeekDto> weeks;

  @JsonProperty("nameofteacher")
  String nameOfTeacher;
}
