package io.github.leocklaus.desafiomagalu.domain.repository;

import io.github.leocklaus.desafiomagalu.domain.entity.Notification;
import io.github.leocklaus.desafiomagalu.domain.entity.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByNotificationStatusInAndDateTimeBefore(
            List<NotificationStatus> status,
            LocalDateTime dateTime);
}
