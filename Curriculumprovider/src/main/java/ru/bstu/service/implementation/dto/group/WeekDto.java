package ru.bstu.service.implementation.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bstu.service.implementation.dto.group.DayDto;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WeekDto {
  @JsonProperty("week_status")
  String weekStatus;
  @JsonProperty("day")
  List<DayDto> days;
}
