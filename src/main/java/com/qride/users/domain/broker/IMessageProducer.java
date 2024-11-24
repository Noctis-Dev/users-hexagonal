package com.qride.users.domain.broker;

import com.qride.users.domain.events.NotificationEvent;

public interface IMessageProducer {

    void sendNotification(NotificationEvent event);
}
