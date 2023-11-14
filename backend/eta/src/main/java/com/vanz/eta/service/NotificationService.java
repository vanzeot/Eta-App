package com.vanz.eta.service;

import com.vanz.eta.dto.ExibitionNotificationData;
import com.vanz.eta.dto.NotificationData;
import com.vanz.eta.dto.ManagedNotificationData;
import com.vanz.eta.entity.Notification;

import java.util.Optional;

public interface NotificationService {

    String createNotification(NotificationData notificationData);

    String processNotification(ManagedNotificationData managedNotificationData);

    String closeNotification(ManagedNotificationData managedNotificationData);

    ExibitionNotificationData getNotificationByNumber(String number);
}
