package com.medici.retrofit.data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * ***************************************
 *
 * @desc: 用于文件上传进度回调
 * @author：李宗好
 * @time: 2018/3/2 0002 16:53
 * @email：lzh@cnbisoft.com
 * @version：
 * @history:
 *
 * ***************************************
 */
public abstract class FileCallback<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(response.isSuccessful()) {
            onSuccess(call, response);
        } else {
            onFailure(call, new Throwable(response.message()));
        }
    }

    /**
     * 上传成功
     * @param call
     * @param response
     */
    public abstract void onSuccess(Call<T> call, Response<T> response);

    /**
     * 用于进度的回调
     * @param total 文件总大小
     * @param progress 上传进度
     */
    public abstract void onLoading(long total, long progress) ;
}
