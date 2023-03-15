package com.camunda.camunda.controller;

import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.camunda.camunda.model.HistoryActivityInstanceRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPKMSTServerCodegen", date = "2021-04-16T06:00"
		+ ":39.859Z")
@Api(value = "HistoryController", description = "The HistoryController API")
public interface HistoryController {

	@ApiOperation(value = "Api to persist HistoryActivityInstance", notes = "", response = Object.class, tags = {})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Object.class),
			@ApiResponse(code = 401, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found") })
	@RequestMapping(value = { "/v1.0/history/activity-instance" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public ResponseEntity<?> persistHistoryActivityInstanceUsingPOST(
			@RequestBody HistoryActivityInstanceRequest historyActivityInstanceRequest) throws URISyntaxException;

}
