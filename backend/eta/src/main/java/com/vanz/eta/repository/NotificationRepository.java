package com.vanz.eta.repository;

import com.vanz.eta.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
