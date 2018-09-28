package org.zxs.base.model;

/**
 * 错误码枚举类
 * @author Administrator
 *
 */
public enum ErrorCodeBaseEnum implements IErrorCode{
	SUCC(0, "执行成功！"), 
	UNAUTHORIZED(401, "授权失败！"),
	PARAM_ILLEGAL(-111, "请求参数不合法！"),
	USER_ABSENT(-201, "用户不存在！"), 
	PSW_ERROR(-202, "密码错误！"), 
	APP_LOGIN_FAIL(-203, "用户登录失败！"),
	MOD_PSW_FAIL(-204, "APP上修改密码失败！"),
	MOD_PSW_ERROR(-205, "APP上修改密码出错！"),
	CHATROOM_EXIST(-206, "聊天室已存在！"),
	CREATE_CHATROOM_FAIL(-207, "创建群聊失败！"),
	CREATE_CHATROOM_ERROR(-208, "创建群聊出错！");

	private ErrorCodeBaseEnum(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	private final int errorCode;
	private final String errorMsg;

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
