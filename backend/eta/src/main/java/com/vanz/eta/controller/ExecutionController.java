package com.vanz.eta.controller;

import com.vanz.eta.dto.ExecutionData;
import com.vanz.eta.service.ExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/execution")
public class ExecutionController {

    @Autowired
    private ExecutionService executionService;

    @PostMapping("/confirm")
    public String confirmAsFinal(@RequestBody ExecutionData executionData){

        return executionService.confirm(executionData);

    }

    @PatchMapping("/abort")
    public String abort(@RequestBody ExecutionData executionData){

        return executionService.abort(executionData);

    }

}
