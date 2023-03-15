package com.camunda.camunda.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "history_activity_instance")
public class HistoryActivityInstance implements Serializable {

	@Id
	private String id;
	private String activityId;
	private String activityName;
	private String activityType;
	private String assignee;
	private String calledProcessInstanceId;
	private String calledCaseInstanceId;
	private boolean canceled;
	private boolean completeScope;
	private float durationInMillis;
	private String endTime;
	private String executionId;
	private String parentActivityInstanceId;
	private String processDefinitionId;
	private String processInstanceId;
	private String startTime;
	private String taskId;
	private String tenantId;
	private String removalTime;
	private String rootProcessInstanceId;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
