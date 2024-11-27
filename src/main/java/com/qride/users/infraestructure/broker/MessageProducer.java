package com.qride.users.infraestructure.broker;

import com.qride.users.domain.broker.IMessageProducer;
import com.qride.users.domain.events.NotificationEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer implements IMessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendNotification(NotificationEvent event) {
        rabbitTemplate.convertAndSend("NotificationEvent", event);
    }
}
