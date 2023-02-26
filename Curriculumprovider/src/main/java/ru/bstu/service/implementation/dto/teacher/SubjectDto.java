package ru.bstu.service.implementation.dto.teacher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {
  @JsonProperty("number")
  String number;

  @JsonProperty("type")
  String type;

  @JsonProperty("name")
  String name;

  @JsonProperty("start")
  String start;

  @JsonProperty("end")
  String end;

  @JsonProperty("classroom")
  List<String> classrooms;

  @JsonProperty("group")
  List<String> groups;
}
