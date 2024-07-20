package io.github.leocklaus.desafiomagalu.domain.service;

import io.github.leocklaus.desafiomagalu.api.dto.NotificationInputDTO;
import io.github.leocklaus.desafiomagalu.api.dto.NotificationOutputDTO;
import io.github.leocklaus.desafiomagalu.domain.entity.Notification;
import io.github.leocklaus.desafiomagalu.domain.entity.NotificationStatus;
import io.github.leocklaus.desafiomagalu.domain.exception.NotificationAlreadySentException;
import io.github.leocklaus.desafiomagalu.domain.exception.NotificationNotFoundException;
import io.github.leocklaus.desafiomagalu.domain.repository.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

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

    @Transactional
    public void setNotificationAsCanceled(Long notificationId){
        var notification = getNotificationByIdOrThrowsExceptionIfNotExists(notificationId);

        if(notification.getNotificationStatus() == NotificationStatus.SENT){
            throw new NotificationAlreadySentException(notificationId);
        }

        notification.setNotificationAsCanceled();
        notificationRepository.flush();
    }

    @Transactional
    public void setNotificationAsSent(Long notificationId){
        var notification = getNotificationByIdOrThrowsExceptionIfNotExists(notificationId);
        notification.setNotificationAsSent();
        notificationRepository.flush();
    }

    @Transactional
    public void setNotificationAsError(Long notificationId){
        var notification = getNotificationByIdOrThrowsExceptionIfNotExists(notificationId);
        notification.setNotificationAsError();
        notificationRepository.flush();
    }

    private Notification getNotificationByIdOrThrowsExceptionIfNotExists(Long notificationId){
        return notificationRepository.findById(notificationId)
                .orElseThrow(()-> new NotificationNotFoundException(notificationId));
    }

}
