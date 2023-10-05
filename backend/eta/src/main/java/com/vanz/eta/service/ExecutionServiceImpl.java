package com.vanz.eta.service;

import com.vanz.eta.dto.ExecutionData;
import com.vanz.eta.entity.*;
import com.vanz.eta.repository.ConfirmationRepository;
import com.vanz.eta.repository.NotificationRepository;
import com.vanz.eta.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class ExecutionServiceImpl implements ExecutionService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ConfirmationRepository confirmationRepository;

    @Override
    @Transactional
    public String confirm(ExecutionData executionData) {

        Long orderNumber = executionData.getOrderNumber();

        if (isStatusBacklog(orderNumber)){
            Confirmation confirmation = new Confirmation();

            //TODO: Export this to a mapping method
            confirmation.setOrderNumber(orderNumber);
            confirmation.setDateStarted(executionData.getDateStarted());
            confirmation.setDateFinished(executionData.getDateFinished());
            confirmation.setType(executionData.getType());
            confirmation.setExecutorId(executionData.getExecutorId());

            confirmationRepository.save(confirmation);

            if (confirmation.getType() == ConfirmationType.FINAL) {

                // Change the status of the order to "FINISHED" and define the closing date-time
                Order order = orderRepository.findById(orderNumber).get();
                order.setStatus(OrderStatus.FINISHED);
                order.setDateClosed(Date.from(Instant.now()));

                // Same thing, but for the notification
                Notification notification = notificationRepository.findById(order.getNotificationNumber()).get();
                notification.setStatus(NotificationStatus.CLOSED);
                notification.setDateClosed(Date.from(Instant.now()));

                // Save both into the DB
                orderRepository.save(order);
                notificationRepository.save(notification);

            }

            return "Confirmation nº " + confirmation.getNumber() + " generated with success.";

        } else {

            return "The order is not pending, so it cannot be executed.";

        }

    }

    @Override
    @Transactional
    public String abort(ExecutionData executionData){

        Long orderNumber = executionData.getOrderNumber();

        if(isStatusBacklog(orderNumber)){

            Order order = orderRepository.findById(orderNumber).get();
            order.setStatus(OrderStatus.ABORTED);
            orderRepository.save(order);

            Notification notification = notificationRepository.findById(order.getNotificationNumber()).get();
            notification.setStatus(NotificationStatus.PENDING);
            notificationRepository.save(notification);

            return "The order (nº " + orderNumber + ") was successfully aborted.";

        } else {

            return "The order could not be aborted because it's not pending.";
        }

    }

    /*
        AUXILIARY METHODS
     */

    public Confirmation mapConfirmation(ExecutionData executionData, Confirmation confirmation){

        confirmation.setDateStarted(executionData.getDateStarted());
        confirmation.setDateFinished(executionData.getDateFinished());
        confirmation.setType(executionData.getType());
        confirmation.setExecutorId(executionData.getExecutorId());

        return confirmation;
    }

    public boolean isStatusBacklog(Long orderNumber){

        return OrderStatus.BACKLOG == orderRepository.findById(orderNumber).get().getStatus();

    }

}
