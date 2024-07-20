package io.github.leocklaus.desafiomagalu.domain.exception;

public class NotificationAlreadySentException extends NotificationException{
    public NotificationAlreadySentException(String message) {
        super(message);
    }

    public NotificationAlreadySentException(Long notificationId){
        this("The notification with id " + notificationId + "you are trying to cancel has already been sent.");
    }
}
