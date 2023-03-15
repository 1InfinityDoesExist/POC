package com.camunda.camunda.service;

import java.net.URISyntaxException;

import org.springframework.stereotype.Service;

import com.camunda.camunda.model.HistoryActivityInstanceRequest;

@Service
public interface HistoryService {

	void persistHistoryActivityInstanceUsingPOST(HistoryActivityInstanceRequest historyActivityInstanceRequest)
			throws URISyntaxException;

}
