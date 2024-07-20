package io.github.leocklaus.desafiomagalu.domain.scheduler;

import io.github.leocklaus.desafiomagalu.domain.entity.NotificationStatus;
import io.github.leocklaus.desafiomagalu.domain.service.NotificationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class NotificationScheduler {

    private final NotificationService notificationService;

    public NotificationScheduler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Scheduled(timeUnit = TimeUnit.MINUTES, fixedRate = 1)
    public void checkForNotifications(){

        List<NotificationStatus> status = Arrays.asList(
                NotificationStatus.PENDING,
                NotificationStatus.CANCELED);

        LocalDateTime time = LocalDateTime.now();

        notificationService.checkForNotificationsToBeSent(status, time);
    }

}
