package com.vanz.eta.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ExibitionNotificationData {

    /*
    This DTO references to a notification that already exists and is
    going to be exibited in the frontend.
     */

    private String number;
    private String title;
    private String description;
    private String authorName;
    private String equipmentName;
    private String locationName;
    private Date dateCreated;
    private Date dateClosed;
    private String status;


}
