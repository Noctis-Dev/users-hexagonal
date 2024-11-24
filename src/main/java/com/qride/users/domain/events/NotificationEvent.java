package com.qride.users.domain.events;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NotificationEvent {
    private EventType type;
    private String destination;
    private String subject;
    private String message;
}
