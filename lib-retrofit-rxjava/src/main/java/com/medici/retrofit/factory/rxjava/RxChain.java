package com.medici.retrofit.factory.rxjava;

import com.medici.retrofit.model.RxRspModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import retrofit2.adapter.rxjava2.Result;

/**
 * ***************************************
 *
 * @desc: Rxjava 处理Event 常用链式调用方式
 * @author：李宗好
 * @time: 2017/12/23 0023 17:43
 * @email：lzh@cnbisoft.com
 * @version：
 * @history:
 *
 * ***************************************
 */
public class RxChain {

    /**
     * 处理网络请求的Rx 操作符转换 Observable 置换 延迟800ms
     * @param <T> 泛型
     * @return
     */
    public static <T> ObservableTransformer<Result<RxRspModel<T>>,T> delayHandleNetEvent(){
        return new ObservableTransformer<Result<RxRspModel<T>>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<Result<RxRspModel<T>>> upstream) {
                return upstream.delay(800, TimeUnit.MILLISECONDS)
                        .map(result->RxResults.responseBody(result))
                        .flatMap(RxResultHelper.handleResult())
                        .compose(RxSchedulersHelper.applySchedulers());
            }
        };
    }

    /**
     * 处理网络请求的Rx 操作符转换 Observable 置换
     * @param <T> 泛型
     * @return
     */
    public static <T> ObservableTransformer<Result<RxRspModel<T>>,T> handleNetEvent(){
        return new ObservableTransformer<Result<RxRspModel<T>>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<Result<RxRspModel<T>>> upstream) {
                return upstream.map(result->RxResults.responseBody(result))
                        .flatMap(RxResultHelper.handleResult())
                        .compose(RxSchedulersHelper.applySchedulers());
            }
        };
    }
}
