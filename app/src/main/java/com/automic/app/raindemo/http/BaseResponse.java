package com.automic.app.raindemo.http;

/**
 *
 * 网络请求结果 基类
 * Created by sjt on 16/11/10.
 */

public class BaseResponse<T> {
    public int status;
    public String message;
public int code;
    public T result;

    public boolean isSuccess(){
        return status == 200;
    }
}
