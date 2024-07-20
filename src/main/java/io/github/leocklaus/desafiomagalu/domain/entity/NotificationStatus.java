package io.github.leocklaus.desafiomagalu.domain.entity;

public enum NotificationStatus {

    PENDING("the notification is schedule to be sent"),
    SENT("the notification has been sent"),
    CANCELED("the notification sent has been canceled"),
    ERROR("error sending the notification");

    NotificationStatus(String description){

    }

}
