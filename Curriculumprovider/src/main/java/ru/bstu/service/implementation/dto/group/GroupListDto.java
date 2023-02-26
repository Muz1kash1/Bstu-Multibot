package ru.bstu.service.implementation.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupListDto {
  @JsonProperty("group_names")
  private List<String> groupNames;
}