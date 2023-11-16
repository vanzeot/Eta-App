package com.vanz.eta.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vanz.eta.entity.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
public class ExibitionOrderData {

    private String number;
    private String title;
    private String description;
    private String status;
    private Date dateCreated;
    private Date dateClosed;
    private String authorRegistation;
    private String authorName;
    private String notificationNumber;
    private String notificationTitle;

}
