package com.vanz.eta.service;

import com.vanz.eta.dto.ExibitionNotificationData;
import com.vanz.eta.dto.NotificationData;
import com.vanz.eta.dto.ManagedNotificationData;
import com.vanz.eta.entity.Notification;
import com.vanz.eta.entity.NotificationStatus;
import com.vanz.eta.entity.Order;
import com.vanz.eta.entity.OrderStatus;
import com.vanz.eta.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

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
        notification.setNumber(generateNotificationNumber());

        // Saving into the DB
        notificationRepository.save(notification);

        //TODO: Change to NotificationResponse
        return "Notification nº " + notification.getNumber() + " generated with success.";

    }

    @Override
    @Transactional
    public String processNotification(ManagedNotificationData managedNotificationData) {

        Notification notification = notificationRepository.findByNumber(managedNotificationData.getNotificationNumber()).get();
        NotificationStatus notificationStatus = notification.getStatus();

        switch (notificationStatus){

            case CLOSED -> {
                return "The notification is closed, so it cannot be processed.";
            }

            case PROCESSING -> {
                return "The notification is already on processing.";
            }

            default -> {
                //TODO: Export this to a mapping method
                Order order = new Order();
                order.setTitle(managedNotificationData.getTitle());
                order.setDescription(managedNotificationData.getDescription());
                order.setAuthorId(managedNotificationData.getAuthorId());
                order.setNotificationId(notification.getId());

                // Business Logic
                order.setStatus(OrderStatus.BACKLOG);
                order.setNumber(generateOrderNumber());
                notification.setStatus(NotificationStatus.PROCESSING);

                // Saving into the DB
                orderRepository.save(order);
                notificationRepository.save(notification);

                //TODO: Create a NotificationResponse class
                return "Order nº " + order.getNumber() + " generated with success.";
            }
        }
    }

    @Override
    public String closeNotification(ManagedNotificationData managedNotificationData){

        String notificationNumber = managedNotificationData.getNotificationNumber();
        Notification notification = notificationRepository.findByNumber(notificationNumber).get();
        NotificationStatus notificationStatus = notification.getStatus();

        switch (notificationStatus) {
            case CLOSED -> {
                return "The notification is already closed.";
            }
            case PROCESSING -> {
                return "The notification cannot be closed, because it's attached to an order.";
            }
            default -> {
                notification.setStatus(NotificationStatus.CLOSED);
                notification.setDateClosed(Date.from(Instant.now()));
                notificationRepository.save(notification);
                return "The notification (nº " + notificationNumber + ") was successfully closed.";
            }
        }
    }

    /*
        AUXILIARY METHODS
     */

    private boolean checkIfItsClosed(Long notificationNumber) {

        return NotificationStatus.CLOSED == notificationRepository.findById(notificationNumber).get().getStatus();

    }

    public String formatGeneratedDoc(String code, Long lastId){
        return code + String.format("%06d", lastId + 1);
    }


    // TODO: Encapsulate both methods in a more reusable way:

    public String generateNotificationNumber(){

        Long lastId = null;
        try{
            Notification lastNotification = notificationRepository.findFirstByOrderByIdDesc().get();
            lastId = lastNotification.getId();

        } catch (NoSuchElementException e){
            lastId = 0L;
        }

        return formatGeneratedDoc("NTF", lastId);

    }

    public String generateOrderNumber(){

        Long lastId = null;
        try{
            Order lastOrder = orderRepository.findFirstByOrderByIdDesc().get();
            lastId = lastOrder.getId();

        } catch (NoSuchElementException e){
            lastId = 0L;
        }

        return formatGeneratedDoc("ORD", lastId);
    }

    public ExibitionNotificationData getNotificationByNumber(String number){

        Optional<Notification> notificationObject = notificationRepository.findByNumber(number);

         // TODO: Make this mapping better
         ExibitionNotificationData notificationData = new ExibitionNotificationData();
         notificationData.setNumber(notificationObject.get().getNumber());
         notificationData.setTitle((notificationObject.get().getTitle()));
         notificationData.setDescription(notificationObject.get().getDescription());
         notificationData.setStatus(String.valueOf(notificationObject.get().getStatus()));
        notificationData.setDateCreated(notificationObject.get().getDateCreated());
        notificationData.setDateClosed(notificationObject.get().getDateClosed());
         // Getting names and codes from id's in each repository:
         notificationData.setEquipmentCode(
                 equipmentRepository.findById(
                         notificationObject.get().getEquipmentId()
                 ).get().getCode()
         );
        notificationData.setEquipmentName(
                equipmentRepository.findById(
                        notificationObject.get().getEquipmentId()
                ).get().getName()
        );
        notificationData.setLocationCode(
                locationRepository.findById(
                        notificationObject.get().getLocationId()
                ).get().getCode()
        );
         notificationData.setLocationName(
                 locationRepository.findById(
                         notificationObject.get().getLocationId()
                 ).get().getName()
         );
        notificationData.setAuthorRegistration(
                employeeRepository.findById(
                        notificationObject.get().getAuthorId()
                ).get().getRegistration()
        );
         notificationData.setAuthorName(
                 employeeRepository.findById(
                         notificationObject.get().getAuthorId()
                 ).get().getName()
         );

         return notificationData;

    }


}
