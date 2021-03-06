package org.zxs.base.model;

public class CommonReturnBean<T> {
	
	// 错误码
	private int errorCode;
	
	// 错误信息
	private String errorMsg;
	
	// 数据
	private T data;

	public CommonReturnBean() {
		super();
	}

	public CommonReturnBean(IErrorCode en) {
		this.errorCode = en.getErrorCode();
		this.errorMsg = en.getErrorMsg();
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
