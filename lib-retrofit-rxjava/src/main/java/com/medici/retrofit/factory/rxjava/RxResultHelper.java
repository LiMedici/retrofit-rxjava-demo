package com.medici.retrofit.factory.rxjava;

import android.util.Log;

import com.medici.retrofit.model.RxRspModel;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * @desc:对使用RxJava http请求回调结果的过滤处理
 * @author：李宗好
 * @time: 2017/06/14 11:05
 * @email：lzh@cnbisoft.com
 */
public class RxResultHelper {

    public static final String TAG = "RxResultHelper";

    /**
     * 返回将HttpEntity转换成内部Data数据的Observable
     * @param <T>
     * @return
     */
    public static <T> Function<RxRspModel<T>, ObservableSource<T>> handleResult() {
        return rspModel -> {
            if (rspModel.success()) {
                if(null == rspModel.getData()){
                    // 将Integer类型错误码转换成String,让RxServerException 传到应用层
                    String codeStr = Integer.toString(RxRspModel.ERROR_UNKNOWN);
                    return Observable.error(new RxServerException(codeStr));
                }
                return Observable.just(rspModel.getData());
            } else {
                //打印msg
                String msgStr = rspModel.getMsg();
                Log.e(TAG,msgStr);
                // 将Integer类型错误码转换成String,让RxServerException 传到应用层
                String codeStr = Integer.toString(rspModel.getCode());
                return Observable.error(new RxServerException(codeStr));
            }
        };
    }

}
