package io.github.leocklaus.desafiomagalu.domain.service;

import com.sendgrid.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Qualifier("sendgrid")
public class SendgridMailService implements MailService{

    public final NotificationService notificationService;
    private static final Logger log = LoggerFactory.getLogger(SendgridMailService.class);

    public SendgridMailService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void send(String receiverEmail, String subject, String content, Long notificationId) {

        Email from = new Email("sender-email");
        Email to = new Email(receiverEmail);
        Content emailContent = new Content("text/plain", content);
        Mail mail = new Mail(from, subject, to, emailContent);

        SendGrid sg = new SendGrid("api-key");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            if(response.getStatusCode() == 202){
                notificationService.setNotificationAsSent(notificationId);
            }

        } catch (IOException ex) {
            notificationService.setNotificationAsError(notificationId);
            log.info("error {}", ex.getMessage());
        }
    }
}
