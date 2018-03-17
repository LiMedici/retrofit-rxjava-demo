package com.medici.retrofit.factory.rxjava;

import java.io.IOException;

/**
 * ***************************************
 *
 * @desc: http网络异常
 * @author：李宗好
 * @time: 2018/1/4 0004 16:52
 * @email：lzh@cnbisoft.com
 * @version：
 * @history:
 *
 * ***************************************
 */
public class RxNetworkException extends IOException{

    public RxNetworkException() {
    }

    public RxNetworkException(String message) {
        super(message);
    }

    public RxNetworkException(String message, Throwable cause) {
        super(message, cause);
    }

    public RxNetworkException(Throwable cause) {
        super(cause);
    }
}
