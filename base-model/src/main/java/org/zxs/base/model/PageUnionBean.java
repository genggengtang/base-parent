package org.zxs.base.model;

import java.util.List;

/**
 * 分页复合数据
 * @author Administrator
 *
 * @param <K>
 * @param <T>
 */
public class PageUnionBean<K, T> {
	// 前台发送的数据
	private String draw;
	
	// 记录总数
	private long total;
	
	// 错误码
	private int errorCode;
	
	// 错误信息
	private String errorMsg;
	
	// 非分页数据
	private K nonPageData;
	
	// 分页数据
	private List<T> pageData;
	
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

	public K getNonPageData() {
		return nonPageData;
	}

	public void setNonPageData(K nonPageData) {
		this.nonPageData = nonPageData;
	}

	public List<T> getPageData() {
		return pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}
}
