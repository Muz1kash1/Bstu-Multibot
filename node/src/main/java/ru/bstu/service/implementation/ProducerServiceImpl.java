package ru.bstu.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.bstu.commotrabbitmq.RabbitQueue;
import ru.bstu.service.ProducerService;

@AllArgsConstructor
@Service
public class ProducerServiceImpl implements ProducerService {
  private final RabbitTemplate rabbitTemplate;

  @Override public void produceAnswer(SendMessage sendMessage) {
    rabbitTemplate.convertAndSend(RabbitQueue.ANSWER_MESSAGE_UPDATE, sendMessage);
  }
}
