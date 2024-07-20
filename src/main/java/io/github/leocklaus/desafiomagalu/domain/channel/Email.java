package io.github.leocklaus.desafiomagalu.domain.channel;

import io.github.leocklaus.desafiomagalu.domain.entity.Notification;
import io.github.leocklaus.desafiomagalu.domain.service.MailService;
import io.github.leocklaus.desafiomagalu.domain.service.NotificationService;
import io.github.leocklaus.desafiomagalu.domain.service.SendgridMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class Email implements Channel{

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    @Qualifier("sendgrid")
    private final MailService mailService;

    public Email(MailService mailService) {
        this.mailService = mailService;
    }


    @Override
    public void send(Notification notification) {
        mailService.send(
                notification.getReceiver(),
                "subject",
                notification.getMessage(),
                notification.getId()
        );
    }
}
