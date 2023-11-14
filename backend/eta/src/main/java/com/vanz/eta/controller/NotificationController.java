package com.vanz.eta.controller;

import com.vanz.eta.dto.ExibitionNotificationData;
import com.vanz.eta.dto.NotificationData;
import com.vanz.eta.dto.ManagedNotificationData;
import com.vanz.eta.entity.Notification;
import com.vanz.eta.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/notification")
@CrossOrigin("http://localhost:4200")
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

    @GetMapping("/{number}")
    @ResponseBody
    public ExibitionNotificationData getNotificationData (@PathVariable String number) {

        return notificationService.getNotificationByNumber(number);


    }

}
