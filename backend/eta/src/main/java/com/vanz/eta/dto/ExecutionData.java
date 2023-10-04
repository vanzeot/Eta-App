package com.vanz.eta.dto;

import com.vanz.eta.entity.ConfirmationType;
import lombok.Data;

import java.util.Date;

@Data
public class ExecutionData {

    private ConfirmationType type;
    private Date dateStarted;
    private Date dateFinished;
    private Long executorId;
    private Long orderNumber;

}
