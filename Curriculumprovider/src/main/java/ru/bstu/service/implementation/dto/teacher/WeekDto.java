package ru.bstu.service.implementation.dto.teacher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeekDto {
  @JsonProperty("week_status")
  String weekStatus;
  @JsonProperty("day")
  List<DayDto> days;
}
