package com.vanz.eta.dto;

import lombok.Data;

@Data
public class ManagedNotificationData {

    private String title;
    private String description;
    private Long authorId;
    private Long notificationNumber;

}
