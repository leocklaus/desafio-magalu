package io.github.leocklaus.desafiomagalu.domain.channel;

import io.github.leocklaus.desafiomagalu.domain.entity.Notification;
import org.springframework.stereotype.Component;

@Component
public class Whatsapp implements Channel{
    @Override
    public void send(Notification notification) {

    }
}
