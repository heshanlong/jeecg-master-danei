package org.jeecgframework.jwt.util;

import org.jeecgframework.jwt.util.menu.ResponseMessageCodeEnum;

import java.util.Map;

/**
 * restful 接口响应返回结构
 * @author scott
 * @param <T>
 */
public class ResponseMessage<T> {

    private String respCode;
    private String respMsg;
    private T data;
    private boolean ok;
    private Map<Object,Object> map;
    public ResponseMessage() {
    }

    public ResponseMessage(ResponseMessageCodeEnum codeEnum, String message) {
        this.respCode = codeEnum.getCode();
        this.respMsg = message;
    }
    public ResponseMessage(ResponseMessageCodeEnum codeEnum, String message, boolean ok, T data) {
        this.respCode = codeEnum.getCode();
        this.respMsg = message;
        this.ok = ok;
        this.data = data;
    }
    
    public ResponseMessage(ResponseMessageCodeEnum codeEnum, String message, boolean ok, T data,Map map) {
        this.respCode = codeEnum.getCode();
        this.respMsg = message;
        this.ok = ok;
        this.data = data;
        this.map=map;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getMessage() {
        return respMsg;
    }

    public void setMessage(String message) {
        this.respMsg = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isOk() {
		return ok;
	}

    public Map<Object, Object> getMap() {
        return map;
    }

    public void setMap(Map<Object, Object> map) {
        this.map = map;
    }
}
