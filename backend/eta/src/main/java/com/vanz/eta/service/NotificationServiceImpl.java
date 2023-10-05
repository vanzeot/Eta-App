package com.vanz.eta.service;

import com.vanz.eta.dto.NotificationData;
import com.vanz.eta.dto.ManagedNotificationData;
import com.vanz.eta.entity.Notification;
import com.vanz.eta.entity.NotificationStatus;
import com.vanz.eta.entity.Order;
import com.vanz.eta.entity.OrderStatus;
import com.vanz.eta.repository.NotificationRepository;
import com.vanz.eta.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional
    public String createNotification(NotificationData notificationData) {

        Notification notification = new Notification();

        //TODO: Export this to a mapping method
        notification.setTitle(notificationData.getTitle());
        notification.setAuthorId(notificationData.getAuthorId());
        notification.setDescription(notificationData.getDescription());
        notification.setEquipmentId(notificationData.getEquipmentId());
        notification.setLocationId(notificationData.getLocationId());

        // Business Logic
        notification.setStatus(NotificationStatus.PENDING);

        // Saving into the DB
        notificationRepository.save(notification);

        //TODO: Change to NotificationResponse
        return "Notification nº " + notification.getNumber() + " generated with success.";

    }

    @Override
    @Transactional
    public String processNotification(ManagedNotificationData managedNotificationData) {

        Order order = new Order();

        //TODO: Change to NotificationResponse
        order.setTitle(managedNotificationData.getTitle());
        order.setDescription(managedNotificationData.getDescription());
        order.setAuthorId(managedNotificationData.getAuthorId());
        order.setNotificationNumber(managedNotificationData.getNotificationNumber());

        // Business Logic
        order.setStatus(OrderStatus.BACKLOG);

        Notification notification = notificationRepository
                .findById(
                        managedNotificationData.getNotificationNumber()
                )
                .get();
        notification.setStatus(NotificationStatus.PROCESSING);

        // Saving into the DB
        orderRepository.save(order);
        notificationRepository.save(notification);

        //TODO: Create a NotificationResponse class
        return "Order nº " + order.getNumber() + " generated with success.";
    }

    @Override
    public String closeNotification(ManagedNotificationData managedNotificationData){

        Long notificationNumber = managedNotificationData.getNotificationNumber();
        Notification notification = notificationRepository.findById(notificationNumber).get();
        NotificationStatus notificationStatus = notification.getStatus();


        switch (notificationStatus) {
            case CLOSED -> {
                return "The notification (nº " + notificationNumber + ") is already closed.";
            }
            case PROCESSING -> {
                return "The notification (nº " + notificationNumber + ") cannot be closed, because it is attached to an order.";
            }
            default -> {
                notification.setStatus(NotificationStatus.CLOSED);
                notification.setDateClosed(Date.from(Instant.now()));
                notificationRepository.save(notification);
                return "The notification (nº " + notificationNumber + "was successfully closed.";
            }
        }
    }

    /*
        AUXILIARY METHODS
     */

    private boolean checkIfItsClosed(Long notificationNumber) {

        return NotificationStatus.CLOSED == notificationRepository.findById(notificationNumber).get().getStatus();

    }

    public Notification mapNotification(NotificationData notificationData, Notification notification){

        notification.setTitle(notificationData.getTitle());
        notification.setAuthorId(notificationData.getAuthorId());
        notification.setDescription(notificationData.getDescription());
        notification.setEquipmentId(notificationData.getEquipmentId());
        notification.setLocationId(notificationData.getLocationId());

        return notification;
    }

    public Order mapOrder(ManagedNotificationData managedNotificationData, Order order){

        order.setTitle(managedNotificationData.getTitle());
        order.setDescription(managedNotificationData.getDescription());
        order.setAuthorId(managedNotificationData.getAuthorId());
        order.setNotificationNumber(managedNotificationData.getNotificationNumber());

        return order;
    }
}
