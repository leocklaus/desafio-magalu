package io.github.leocklaus.desafiomagalu.domain.entity;

import io.github.leocklaus.desafiomagalu.api.dto.NotificationInputDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime;

    private String receiver;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @Enumerated(EnumType.STRING)
    private NotificationStatus notificationStatus;

    public Notification(Long id,
                        LocalDateTime dateTime,
                        String receiver,
                        String message,
                        NotificationType notificationType,
                        NotificationStatus notificationStatus) {
        this.id = id;
        this.dateTime = dateTime;
        this.receiver = receiver;
        this.message = message;
        this.notificationType = notificationType;
        this.notificationStatus = notificationStatus;
    }

    public Notification() {
    }

    public Notification(NotificationInputDTO dto){
        this.dateTime = dto.dateTime();
        this.message = dto.message();
        this.receiver = dto.receiver();
        this.notificationType = dto.notificationType();
    }

    public void setNotificationAsPending(){
        this.notificationStatus = NotificationStatus.PENDING;
    }

    public void setNotificationAsSent(){
        this.notificationStatus = NotificationStatus.SENT;
    }

    public void setNotificationAsCanceled(){
        this.notificationStatus = NotificationStatus.CANCELED;
    }

    public void setNotificationAsError(){
        this.notificationStatus = NotificationStatus.ERROR;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public NotificationStatus getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(NotificationStatus notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
