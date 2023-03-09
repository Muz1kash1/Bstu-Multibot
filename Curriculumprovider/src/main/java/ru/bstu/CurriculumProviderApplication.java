package ru.bstu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Приложение для взаимодействия с сервисом предоставляющим расписание */
@SpringBootApplication
public class CurriculumProviderApplication {
  public static void main(String[] args) {
    SpringApplication.run(CurriculumProviderApplication.class);
  }
}
