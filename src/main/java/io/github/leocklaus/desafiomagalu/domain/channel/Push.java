package io.github.leocklaus.desafiomagalu.domain.channel;

import io.github.leocklaus.desafiomagalu.domain.entity.Notification;
import io.github.leocklaus.desafiomagalu.domain.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Push implements Channel{

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    private final NotificationService notificationService;

    public Push(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void send(Notification notification) {
        log.info("Sending a push to: " + notification.getReceiver() +
                " via notification: " + notification.getId());
        this.notificationService.setNotificationAsSent(notification.getId());
    }
}
