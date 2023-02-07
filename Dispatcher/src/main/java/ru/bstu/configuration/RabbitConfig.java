package ru.bstu.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.bstu.commotrabbitmq.RabbitQueue;

import static ru.bstu.commotrabbitmq.RabbitQueue.ANSWER_MESSAGE_UPDATE;
import static ru.bstu.commotrabbitmq.RabbitQueue.AUDIO_MESSAGE_UPDATE;
import static ru.bstu.commotrabbitmq.RabbitQueue.DOC_MESSAGE_UPDATE;
import static ru.bstu.commotrabbitmq.RabbitQueue.PHOTO_MESSAGE_UPDATE;

/**
 * класс конфигурации брокера сообщений RabbitMq
 */
@Configuration
public class RabbitConfig {
  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public Queue textMessageQueue() {
    return new Queue(RabbitQueue.TEXT_MESSAGE_UPDATE);
  }

  @Bean
  public Queue photoMessageQueue() {
    return new Queue(PHOTO_MESSAGE_UPDATE);
  }

  @Bean
  Queue audioMessageQueue() {
    return new Queue(AUDIO_MESSAGE_UPDATE);
  }

  @Bean
  Queue documentMessageQueue() {
    return new Queue(DOC_MESSAGE_UPDATE);
  }

  @Bean
  Queue answerMessageQueue() {
    return new Queue(ANSWER_MESSAGE_UPDATE);
  }

  @Bean
  public CachingConnectionFactory connectionFactory() {
    return new CachingConnectionFactory("localhost");
  }

  @Bean
  public AmqpAdmin amqpAdmin() {
    return new RabbitAdmin(connectionFactory());
  }

  @Bean
  RabbitTemplate rabbitTemplate() {

    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
    rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    return rabbitTemplate;
  }
}
