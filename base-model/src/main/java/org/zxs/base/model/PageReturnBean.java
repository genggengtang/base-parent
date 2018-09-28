package org.zxs.base.model;

import java.util.List;

public class PageReturnBean<T> {
	// 前台发送的数据
	private String draw;
	
	// 记录总数
	private long total;
	
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

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
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
