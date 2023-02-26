package ru.bstu.service.implementation.dto.teacher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DayDto {
  @JsonProperty("day_of_week")
  String dayOfWeek;
  @JsonProperty("date")
  String date;
  @JsonProperty("subjects")
  List<SubjectDto> subjects;
}
