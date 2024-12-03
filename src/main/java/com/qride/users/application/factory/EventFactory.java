package com.qride.users.application.factory;

import lombok.Builder;
import com.qride.users.domain.broker.IMessageProducer;
import com.qride.users.domain.events.EventType;
import com.qride.users.domain.events.NotificationEvent;

@Builder
public class EventFactory {

    private IMessageProducer producer;
    private EventType type;
    private String email;
    private String phoneNumber;
    private String message;
    private String subject;

    public interface Notification {
        void send();
    }

    public Notification getNotification() {
        NotificationEvent event = new NotificationEvent();
        event.setType(type);
        event.setSubject(subject);
        event.setMessage(message);

        if (type == EventType.EMAIL) {
            event.setDestination(email);
        } else if (type == EventType.WHATSAPP) {
            event.setDestination(phoneNumber);
        } else {
            throw new IllegalArgumentException("Unsupported event type: " + type);
        }

        return () -> producer.sendNotification(event);
    }
}
