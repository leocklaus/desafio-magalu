package io.github.leocklaus.desafiomagalu.api.dto;

import io.github.leocklaus.desafiomagalu.domain.entity.NotificationType;

import java.time.LocalDateTime;

public record NotificationInputDTO(
        LocalDateTime dateTime,
        String receiver,
        String message,
        NotificationType notificationType) {
}
