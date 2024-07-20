package io.github.leocklaus.desafiomagalu.domain.service;

import io.github.leocklaus.desafiomagalu.api.dto.NotificationInputDTO;
import io.github.leocklaus.desafiomagalu.api.dto.NotificationOutputDTO;
import io.github.leocklaus.desafiomagalu.domain.channel.*;
import io.github.leocklaus.desafiomagalu.domain.entity.Notification;
import io.github.leocklaus.desafiomagalu.domain.entity.NotificationStatus;
import io.github.leocklaus.desafiomagalu.domain.entity.NotificationType;
import io.github.leocklaus.desafiomagalu.domain.exception.NotificationAlreadySentException;
import io.github.leocklaus.desafiomagalu.domain.exception.NotificationNotFoundException;
import io.github.leocklaus.desafiomagalu.domain.repository.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final Map<NotificationType, Channel> notificationChannel = Map.of(
            NotificationType.EMAIL, new Email(this),
            NotificationType.SMS, new SMS(),
            NotificationType.PUSH, new Push(this),
            NotificationType.WHATSAPP, new Whatsapp()
    );

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public NotificationOutputDTO scheduleNotification(NotificationInputDTO dto){
        var notification = new Notification(dto);
        notification.setNotificationAsPending();
        notification =  notificationRepository.save(notification);

        return new NotificationOutputDTO(notification);
    }

    public NotificationOutputDTO getNotificationById(Long notificationId){
        var notification = getNotificationByIdOrThrowsExceptionIfNotExists(notificationId);

        return new NotificationOutputDTO(notification);
    }

    public void checkForNotificationsToBeSent(List<NotificationStatus> status, LocalDateTime time){

        List<Notification> notifications = notificationRepository
                .findByNotificationStatusInAndDateTimeBefore(
                        status,
                        time
                );

        sendNotifications(notifications);
    }

    private void sendNotifications(List<Notification> notifications){
        notifications.forEach(notification-> {
            var channel = notificationChannel.get(notification.getNotificationType());
            channel.send(notification);
        });
    }

    @Transactional
    public void setNotificationAsCanceled(Long notificationId){
        var notification = getNotificationByIdOrThrowsExceptionIfNotExists(notificationId);

        if(notification.getNotificationStatus() == NotificationStatus.SENT){
            throw new NotificationAlreadySentException(notificationId);
        }

        notification.setNotificationAsCanceled();
        notificationRepository.save(notification);
    }

    @Transactional
    public void setNotificationAsSent(Long notificationId){
        var notification = getNotificationByIdOrThrowsExceptionIfNotExists(notificationId);
        notification.setNotificationAsSent();
        notificationRepository.save(notification);
    }

    @Transactional
    public void setNotificationAsError(Long notificationId){
        var notification = getNotificationByIdOrThrowsExceptionIfNotExists(notificationId);
        notification.setNotificationAsError();
        notificationRepository.save(notification);
    }

    private Notification getNotificationByIdOrThrowsExceptionIfNotExists(Long notificationId){
        return notificationRepository.findById(notificationId)
                .orElseThrow(()-> new NotificationNotFoundException(notificationId));
    }

}
