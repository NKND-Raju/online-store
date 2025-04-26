package com.online.store.online.store.common.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchBaseRequest {

	@Min(0)
	private Integer page;

	@Min(0)
	private Integer size;
}
