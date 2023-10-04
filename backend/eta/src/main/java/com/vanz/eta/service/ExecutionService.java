package com.vanz.eta.service;


import com.vanz.eta.dto.ExecutionData;

public interface ExecutionService {

    String confirm(ExecutionData executionData);

    String abort(ExecutionData executionData);

}
