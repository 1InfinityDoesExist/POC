package com.camunda.camunda.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
	public void persistHistoryActivityInstanceUsingPOST(HistoryActivityInstanceRequest historyActivityInstanceRequest)
			throws URISyntaxException {

		try {

			Map<String, Object> parameters = new LinkedHashMap<String, Object>();
			JSONObject jsonObject = (JSONObject) new JSONParser()
					.parse(OBJECT_MAPPER.writeValueAsString(historyActivityInstanceRequest));
			for (Object obj : jsonObject.keySet()) {
				String param = (String) obj;
				parameters.put(param, jsonObject.get(param));
			}

			historyActivityInstanceUrl = appendToUrl(historyActivityInstanceUrl, parameters);

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

	public String appendToUrl(String url, Map<String, Object> parameters) throws URISyntaxException {
		URI uri = new URI(url);
		String query = uri.getQuery();

		StringBuilder builder = new StringBuilder();

		if (query != null)
			builder.append(query);

		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			String keyValueParam = entry.getKey() + "=" + entry.getValue();
			if (!builder.toString().isEmpty())
				builder.append("&");

			builder.append(keyValueParam);
		}

		URI newUri = new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), builder.toString(), uri.getFragment());
		return newUri.toString();
	}
}
