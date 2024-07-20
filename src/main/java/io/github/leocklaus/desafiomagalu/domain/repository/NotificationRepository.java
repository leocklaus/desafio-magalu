package io.github.leocklaus.desafiomagalu.domain.repository;

import io.github.leocklaus.desafiomagalu.domain.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
