package ru.bstu.service.implementation.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GroupScheduleDto {
  @JsonProperty("weeks")
  List<WeekDto> weeks;

  @JsonProperty("nameofgroup")
  String nameOfGroup;
}
