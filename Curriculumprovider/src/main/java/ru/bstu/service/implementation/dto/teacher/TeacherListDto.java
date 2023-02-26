package ru.bstu.service.implementation.dto.teacher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherListDto {
  @JsonProperty("teacher_names")
  private List<String> teacherNames;
}
