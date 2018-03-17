package com.medici.demo.helper;

import android.support.annotation.NonNull;

import com.medici.demo.model.RemoteService;
import com.medici.demo.model.api.CodeModel;
import com.medici.retrofit.Network;
import com.medici.retrofit.data.DataSource;
import com.medici.retrofit.factory.rxjava.RxChain;
import com.medici.retrofit.factory.rxjava.RxErrorConsumer;

/**
 * ***************************************
 *
 * @desc:
 * @author：李宗好
 * @time: 2018/3/14 0014 20:25
 * @email：lzh@cnbisoft.com
 * @version：
 * @history: ***************************************
 */
public class MediciHelper {

    public static void sendCode(@NonNull String phone, @NonNull final DataSource.Callback<CodeModel> callback){
        RemoteService service = Network.remote(RemoteService.class);
        service.getForgetPhoneCode(phone)
                .compose(RxChain.handleNetEvent())
                .subscribe(data -> {
                    callback.onDataLoaded(data);
                }, new RxErrorConsumer() {
                    @Override
                    public void onError(int stringRes) {
                        callback.onDataNotAvailable(stringRes);
                    }
                });
    }

}
