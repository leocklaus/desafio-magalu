package io.github.leocklaus.desafiomagalu.domain.service;

public interface MailService {
    void send(String receiverEmail, String subject, String content, Long notificationId);
}
