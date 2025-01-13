package cn.cuit.controller;

import cn.cuit.domain.Notification;
import cn.cuit.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminNotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification")
    public ResponseEntity<List<Notification>> getNotifications() {
        List<Notification> notifications = notificationService.list();
        return ResponseEntity.ok().body(notifications);
    }

    @PostMapping("/editNotification")
    public ResponseEntity<Void> editNotification(@RequestBody Notification notification) {
        notificationService.updateById(notification);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteNotification")
    public ResponseEntity<Void> deleteNotification(@RequestParam Integer id) {
        notificationService.removeById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addNotification")
    public ResponseEntity<Void> addNotification(@RequestBody Notification notification) {
        notificationService.save(notification);
        return ResponseEntity.ok().build();
    }
}
