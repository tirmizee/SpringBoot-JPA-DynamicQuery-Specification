package com.tirmizee.jpa.specification.custom;

import com.tirmizee.jpa.specification.SearchPageable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria<T> extends SearchPageable {
	
	private T search;
	
}
