package com.vanz.eta.dto;

import lombok.Data;

@Data
public class ManagedNotificationData {

    /*
    This DTO references to a notification that already exists and is
    going to be modified: processed, aborted et cetera.
     */

    private String title;
    private String description;
    private Long authorId;
    private String notificationNumber;

}
