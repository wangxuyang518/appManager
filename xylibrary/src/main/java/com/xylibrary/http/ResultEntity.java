package com.xylibrary.http;

import java.io.Serializable;

/**
 * Created by jiajun.wang on 2018/2/25.
 * 统一的返回结果
 */

public class ResultEntity<T> implements Serializable {

    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
