package com.vanz.eta.service;


import com.vanz.eta.dto.ExecutionData;
import com.vanz.eta.dto.ExibitionOrderData;

public interface ExecutionService {

    String confirm(ExecutionData executionData);

    String abort(ExecutionData executionData);

    ExibitionOrderData getOrderByNumber(String number);
}
