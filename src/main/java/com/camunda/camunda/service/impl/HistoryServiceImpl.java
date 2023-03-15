package com.camunda.camunda.service.impl;

import org.apache.http.client.utils.URIBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.camunda.camunda.entity.HistoryActivityInstance;
import com.camunda.camunda.model.HistoryActivityInstanceRequest;
import com.camunda.camunda.repository.HistoryActivityInstanceRepository;
import com.camunda.camunda.service.HistoryService;
import com.camunda.camunda.util.GenericRestCalls;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class HistoryServiceImpl implements HistoryService {

	private static final ObjectMapper OBJECT_MAPPER;
	static {
		OBJECT_MAPPER = new ObjectMapper();
	}

	@Value("${history.activity.instance}")
	private String historyActivityInstanceUrl;

	@Autowired
	private HistoryActivityInstanceRepository historyActivityInstanceRepository;

	@Autowired
	private GenericRestCalls genericRestCalls;

	@Override
	public void persistHistoryActivityInstanceUsingPOST(HistoryActivityInstanceRequest historyActivityInstanceRequest) {

		try {

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			log.info("---- Url : {}", historyActivityInstanceUrl);
			String response = genericRestCalls.execute(historyActivityInstanceUrl, HttpMethod.GET, headers, null,
					String.class);

			log.info("------Response : {}", response);
			JSONArray jsonArray = (JSONArray) new JSONParser().parse(response);

			for (int iter = 0; iter < jsonArray.size(); iter++) {
				JSONObject jObject = (JSONObject) jsonArray.get(iter);
				HistoryActivityInstance historyActivityInstance = OBJECT_MAPPER.readValue(jObject.toJSONString(),
						HistoryActivityInstance.class);
				historyActivityInstanceRepository.save(historyActivityInstance);
			}
		} catch (JsonProcessingException | ParseException e) {
			e.printStackTrace();
		}
	}
}
