package com.camunda.camunda.service;

import org.springframework.stereotype.Service;

import com.camunda.camunda.model.HistoryActivityInstanceRequest;

@Service
public interface HistoryService {

	void persistHistoryActivityInstanceUsingPOST(HistoryActivityInstanceRequest historyActivityInstanceRequest);

}
