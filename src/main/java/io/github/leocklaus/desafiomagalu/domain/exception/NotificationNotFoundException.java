package io.github.leocklaus.desafiomagalu.domain.exception;

public class NotificationNotFoundException extends NotificationException{

    public NotificationNotFoundException(String message) {
        super(message);
    }

    public NotificationNotFoundException(Long id) {
        this("Notification not found with id: " + id);
    }
}
