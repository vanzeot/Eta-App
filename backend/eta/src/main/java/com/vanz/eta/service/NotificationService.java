package com.vanz.eta.service;

import com.vanz.eta.dto.NotificationData;
import com.vanz.eta.dto.ManagedNotificationData;

public interface NotificationService {

    String createNotification(NotificationData notificationData);

    String processNotification(ManagedNotificationData managedNotificationData);

    String closeNotification(ManagedNotificationData managedNotificationData);
}
