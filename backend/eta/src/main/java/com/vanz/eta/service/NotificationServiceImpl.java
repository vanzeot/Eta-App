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
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class NotificationServiceImpl implements NotificationService{

    private NotificationRepository notificationRepository;
    private OrderRepository orderRepository;

    public NotificationServiceImpl(
                                    NotificationRepository notificationRepository,
                                    OrderRepository orderRepository
    ){

        this.notificationRepository = notificationRepository;
        this.orderRepository = orderRepository;

    }

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
        notification.setStatus(NotificationStatus.CREATED);

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
        order.setStatus(OrderStatus.PENDING);

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

        if(checkIfItsClosed(notificationNumber)){

            return "The notification (nº " + notificationNumber + ") is already closed.";

        } else {

            Notification notification = notificationRepository.findById(notificationNumber).get();

            //TODO: Check if the notification has an order, and if so, it cannot be closed
            notification.setStatus(NotificationStatus.CLOSED);
            notification.setDateClosed(Date.from(Instant.now()));
            notificationRepository.save(notification);

            return "The notification (nº " + notificationNumber + "was successfully closed.";

        }
    }

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
