package com.vanz.eta.controller;

import com.vanz.eta.dto.NotificationData;
import com.vanz.eta.dto.ManagedNotificationData;
import com.vanz.eta.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/create")
    public String createNotification(@RequestBody NotificationData notificationData) {

        return notificationService.createNotification(notificationData);

    }

    @PostMapping("/process")
    public String processNotification(@RequestBody ManagedNotificationData managedNotificationData) {

        return notificationService.processNotification(managedNotificationData);

    }

    @PatchMapping("/close")
    public String closeNotification(@RequestBody ManagedNotificationData managedNotificationData) {

        return notificationService.closeNotification(managedNotificationData);

    }


}
