package com.camunda.camunda.controller.impl;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

	@GetMapping("/dummy-data")
	public ResponseEntity<?> getDummyData(@RequestParam String processInstanceId,
			@RequestParam String activityInstanceId) throws ParseException {

		String response = "[\n" + "  {\n" + "    \"activityId\": \"anActivity\",\n"
				+ "    \"activityName\": \"anActivityName\",\n" + "    \"activityType\": \"userTask\",\n"
				+ "    \"assignee\": \"peter\",\n"
				+ "    \"calledProcessInstanceId\": \"aHistoricCalledProcessInstanceId\",\n"
				+ "    \"calledCaseInstanceId\": null,\n" + "    \"canceled\": true,\n"
				+ "    \"completeScope\": false,\n" + "    \"durationInMillis\": 2000,\n"
				+ "    \"endTime\": \"2013-04-23T18:42:43.000+0200\",\n" + "    \"executionId\": \"anExecutionId\",\n"
				+ "    \"id\": \"aHistoricActivityInstanceId\",\n"
				+ "    \"parentActivityInstanceId\": \"aHistoricParentActivityInstanceId\",\n"
				+ "    \"processDefinitionId\": \"aProcDefId\",\n" + "    \"processInstanceId\": \"aProcInstId\",\n"
				+ "    \"startTime\": \"2013-04-23T11:20:43.000+0200\",\n" + "    \"taskId\": \"aTaskId\",\n"
				+ "    \"tenantId\":null,\n" + "    \"removalTime\":\"2018-02-10T14:33:19.000+0200\",\n"
				+ "    \"rootProcessInstanceId\": \"aRootProcessInstanceId\"\n" + "  }\n" + "]";
		return ResponseEntity.status(HttpStatus.OK).body(new JSONParser().parse(response));
	}

}
