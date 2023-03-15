package com.camunda.camunda.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RestController;

import com.camunda.camunda.controller.HistoryController;
import com.camunda.camunda.model.HistoryActivityInstanceRequest;
import com.camunda.camunda.service.HistoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HistoryControllerImpl implements HistoryController {

	@Autowired
	private HistoryService historyService;

	@Override
	public ResponseEntity<?> persistHistoryActivityInstanceUsingPOST(
			HistoryActivityInstanceRequest historyActivityInstanceRequest) {

		historyService.persistHistoryActivityInstanceUsingPOST(historyActivityInstanceRequest);
		return ResponseEntity.status(HttpStatus.OK).body(new ModelMap().addAttribute("msg", "Success"));
	}

}
