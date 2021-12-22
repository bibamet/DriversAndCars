package com.example.driversandcars.controller;

import com.example.driversandcars.dto.AssignDto;
import com.example.driversandcars.service.AssignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


//todo add checkstyle

@RestController
@RequestMapping("/assign") //todo rename
@RequiredArgsConstructor
public class AssignController {

    private final AssignService assignService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping
    public void assignEntities(@Valid @RequestBody AssignDto assignDto) {
        assignService.assignEntities(assignDto);
    }

}
