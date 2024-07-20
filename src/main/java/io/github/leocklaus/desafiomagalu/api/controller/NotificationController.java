package io.github.leocklaus.desafiomagalu.api.controller;

import io.github.leocklaus.desafiomagalu.api.dto.NotificationInputDTO;
import io.github.leocklaus.desafiomagalu.api.dto.NotificationOutputDTO;
import io.github.leocklaus.desafiomagalu.domain.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> checkNotificationStatus(@PathVariable() Long id){
        var notification = notificationService.getNotificationById(id);
        return ResponseEntity.ok(notification);
    }

    @PostMapping
    public ResponseEntity<NotificationOutputDTO> scheduleNotification(@RequestBody NotificationInputDTO dto){
        var notification = notificationService.scheduleNotification(dto);
        URI uri = URI.create("/notification/" + notification.id());
        return ResponseEntity.created(uri).body(notification);

    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> cancelNotification(@PathVariable Long id){
        notificationService.setNotificationAsCanceled(id);
        return ResponseEntity.noContent().build();
    }


}
