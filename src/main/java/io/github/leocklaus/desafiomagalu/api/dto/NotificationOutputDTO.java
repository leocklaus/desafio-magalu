package io.github.leocklaus.desafiomagalu.api.dto;

import io.github.leocklaus.desafiomagalu.domain.entity.Notification;
import io.github.leocklaus.desafiomagalu.domain.entity.NotificationStatus;
import io.github.leocklaus.desafiomagalu.domain.entity.NotificationType;

import java.time.LocalDateTime;

public record NotificationOutputDTO(
        Long id,
        LocalDateTime dateTime,
        String receiver,
        String message,
        NotificationType notificationType,
        NotificationStatus notificationStatus
) {

    public NotificationOutputDTO(Notification notification){
        this(
                notification.getId(),
                notification.getDateTime(),
                notification.getReceiver(),
                notification.getMessage(),
                notification.getNotificationType(),
                notification.getNotificationStatus()
        );
    }

}
