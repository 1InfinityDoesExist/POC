package com.camunda.camunda.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
public class HistoryActivityInstanceRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String activityInstanceId;
	private String processInstanceId;
	private String processDefinitionId;
	private String executionId;
	private String activityId;
	private String activityName;
	private String activityNameLike;
	private String activityType;
	private String taskAssignee;
	private Boolean finished;
	private Boolean unfinished;
	private Boolean canceled;
	private Boolean completeScope;
	private Date startedBefore;
	private Date startedAfter;
	private Date finishedBefore;
	private Date finishedAfter;
	private List<String> tenantIdIn;
	private Boolean withoutTenantId;
	private String sortBy;
	private String sortOrder;
	private Long firstResult;
	private Long maxResults;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
