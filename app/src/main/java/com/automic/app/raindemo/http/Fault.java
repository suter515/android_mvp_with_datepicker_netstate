package com.automic.app.raindemo.http;

/**
 * 异常处理类，将异常包装成一个 Fault ,抛给上层统一处理
 * Created by sjt on 16/11/17.
 */

public class Fault extends RuntimeException {
    private int errorCode;//预留错误码

    public Fault(int errorCode,String message){
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
