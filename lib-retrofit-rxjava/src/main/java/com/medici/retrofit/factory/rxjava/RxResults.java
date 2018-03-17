package com.medici.retrofit.factory.rxjava;

import com.medici.retrofit.model.RxRspModel;

import io.reactivex.functions.Predicate;
import retrofit2.adapter.rxjava2.Result;

/**
 * @desc:对返回结果数据判断的PREDICATE,其实我们不应该对Rx请求回调结果的过滤
 *       因为这样会使请求结果Error信息丢失,无法做页面响应
 * @author：李宗好
 * @time: 2017/06/09 16:01
 * @email：lzh@cnbisoft.com
 */
public class RxResults<T> {

    private static Predicate<Result<?>> DATA_PREDICATE = result ->
             !result.isError() && result.response().isSuccessful();

    public static Predicate<Result<?>> isSuccess() {
        return DATA_PREDICATE;
    }

    /**
     * 根据Result 返回RxRspModel 对Result异常做出检测,关心网络异常
     * @param result 网络请求返回的Result
     * @param <T> 泛型 请求数据
     * @return 请求数据
     * @throws Exception 抛出异常
     */
    public static <T> RxRspModel<T> responseBody(Result<RxRspModel<T>> result) throws Exception{
        // 请求发生异常
        if(result.isError()){
            // 获取Throwable
            Throwable throwable = result.error();
            if(null != throwable && throwable instanceof RxNetworkException){
                // 检测是网络异常 包装抛出
                throw  new RxNetworkException("network error",throwable);
            }else{
                // 抛出其它异常
                throw new RuntimeException("http error",throwable);
            }
        }
        return result.response().body();
    }
}
