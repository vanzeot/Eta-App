package com.vanz.eta.dto;

import com.vanz.eta.entity.NotificationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
public class NotificationData {

    private String title;
    private String description;
    private Long authorId;
    private Long equipmentId;
    private Long locationId;

}
