package org.zxs.base.model;

import java.util.List;

public class DataTablesBean<T> {
	// 前台发送的数据
	private String draw;
	
	// 记录总数
	private long recordsTotal;
	
	// 条件过滤后的记录数
	private long recordsFiltered;
	
	// 错误码
	private int errorCode;
	
	// 错误信息
	private String errorMsg;
	
	// 数据
	private List<T> data;
	
	public String getDraw() {
		return draw;
	}

	public void setDraw(String draw) {
		this.draw = draw;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
