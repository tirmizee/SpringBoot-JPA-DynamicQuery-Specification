package com.tirmizee.jpa.specification;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author Pratya Yeekhaday
 * 
 * @param <B> Object type of SearchPageable
 * @param <T> Entity
 */
public abstract class SearchPageSpecification<S extends SearchPageable, T> extends SearchSpecification<S,T> {

	private static final long serialVersionUID = 1L;
	
	public static final String DEFUALT_SORT_ASC = "asc";
	public static final String DEFUALT_SORT_DESC = "desc";

	public SearchPageSpecification(S serachPage) {
		super(serachPage);
	}
	
	/**
	 * Override this method. if you want to customize the sort property.
	 */
	protected String sortProperty(String sortField) {
		return sortField;
	}
	
	/**
	 * Override this method. if you want to customize the sort directions.
	 */
	protected Sort buildSort(String sort, String sortField) {
		switch (sort) {
			case DEFUALT_SORT_ASC  : return Sort.by(Sort.Order.asc(sortField));
			case DEFUALT_SORT_DESC : return Sort.by(Sort.Order.desc(sortField));
			default : return Sort.by(Sort.Order.asc(sortField));
		}
	}
	
	/**
	 * Override this method. if you want to customize Pageable.
	 */
	public Pageable getPageable() {
		Integer page = getSearch().getPage();
		Integer size = getSearch().getSize();
		String sort = StringUtils.defaultString(getSearch().getSort(), DEFUALT_SORT_ASC);
		String sortField = this.sortProperty(super.getSearch().getSortField());
		return PageRequest.of(page, size, buildSort(sort, sortField));
	}

}
