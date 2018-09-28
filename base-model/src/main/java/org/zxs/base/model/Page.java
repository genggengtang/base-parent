package org.zxs.base.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装分页数据
 */
public class Page {

	public static final String DEFAULT_PAGESIZE = "10";

	private int start;
	private int length;
	private String orderBy;
	private Map<String, Object> params;
	private Page() {
	}

	public static Page newBuilder(int start, int length) {
		Page page = new Page();
		page.start = start;
		page.length = length;
		if(page.params == null)
			page.params = new HashMap<String, Object>();
		return page;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Object addParams(String key, Object value){
		return params.put(key, value);
	}


	
}
