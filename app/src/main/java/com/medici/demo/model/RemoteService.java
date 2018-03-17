package com.medici.demo.model;

import com.medici.demo.model.api.CodeModel;
import com.medici.retrofit.model.RxRspModel;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 网络请求的所有的接口
 *
 * @author qiujuer Email:qiujuer@live.cn
 * @version 1.0.0
 */
public interface RemoteService {

    @GET("www.mrmedici.com")
    /**
     * @desc 测试请求
     * @return Call<String>
     */
    Call<String> mediciHTML();

    /**
     * 忘记密码用手机请求验证码
     *
     * @param phone
     * @return
     */
    @GET("user/getForgetPhoneCheckCode")
    Observable<Result<RxRspModel<CodeModel>>> getForgetPhoneCode(@Query("phone") String phone);
}
