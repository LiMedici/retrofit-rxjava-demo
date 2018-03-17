package com.medici.retrofit.factory.rxjava;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @desc: Rxjava的线程调度器
 * @author：李宗好
 * @time: 2017/06/03 15:16
 * @email：lzh@cnbisoft.com
 */
public class RxSchedulersHelper {

    public static <T> ObservableTransformer<T, T> io2Main() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> ObservableSource<T> io2Main(Observable<T> upstream){
        return upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<T, T> applySchedulers() {
        return io2Main();
    }

}
