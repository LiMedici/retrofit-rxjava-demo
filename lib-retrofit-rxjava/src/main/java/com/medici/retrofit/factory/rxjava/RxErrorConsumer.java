package com.medici.retrofit.factory.rxjava;

import android.support.annotation.StringRes;
import android.util.Log;

import com.medici.retrofit.Factory;
import com.medici.retrofit.R;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * @desc:Rxjava,错误回调接口
 * @author：李宗好
 * @time: 2017/6/14 14:46
 * @email：lzh@cnbisoft.com
 */
public abstract class RxErrorConsumer implements Consumer<Throwable> {

    public static final String TAG = "RxErrorConsumer";

    @Override
    public void accept(@NonNull Throwable throwable) throws Exception {
        Log.e(TAG,this.getClass().getCanonicalName()+" ========== RxError");
        if(throwable instanceof RxServerException){
            // 获取 string 类型的错误码
            String codeStr = throwable.getMessage();
            int code = Integer.parseInt(codeStr);
            // 错误解析
            int stringRes = Factory.decodeRspCode(code);
            onError(stringRes);
        }else if(throwable instanceof SocketTimeoutException){
            onError(R.string.data_http_error_timeout);
        }else if(throwable instanceof UnknownHostException){
            onError(R.string.data_http_error_network);
        }else if(throwable instanceof NullPointerException){
            // 通常情况是因为服务器返回了空的Body 或者是数据解析异常
            onError(R.string.data_http_error_null);
        }else if(throwable instanceof RxNetworkException){
            // 网络异常
            onError(R.string.data_http_error_network_warning);
        }else{
            onError(R.string.data_http_error_unknown);
        }
    }

    /**
     * 回调已经经过分析处理过的throwable
     * @param stringRes 错误信息
     */
    public abstract void onError(@StringRes int stringRes);
}
