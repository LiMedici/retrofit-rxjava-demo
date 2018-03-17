package com.medici.retrofit.data.helper;

import android.support.annotation.NonNull;

import com.medici.retrofit.FileService;
import com.medici.retrofit.Network;
import com.medici.retrofit.data.DataSource;
import com.medici.retrofit.data.FileCallback;
import com.medici.retrofit.factory.request.FileRequestBody;
import com.medici.retrofit.factory.rxjava.RxChain;
import com.medici.retrofit.factory.rxjava.RxErrorConsumer;
import com.medici.retrofit.model.RxRspModel;
import com.medici.retrofit.model.api.UploadEntity;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * ***************************************
 *
 * @desc: 上传文件工具类
 * @author：李宗好
 * @time: 2018/1/30 0030 17:18
 * @email：lzh@cnbisoft.com
 * @version：
 * @history:
 *
 * ***************************************
 */
public class UploadHelper {

    /**
     * 上传文件 Rxjava+Retrofit调用方式
     * @param file 文件
     * @return Observable<UploadEntity>
     */
    public static Observable<UploadEntity> uploadFile(@NonNull File file){
        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part  和后端约定好Key，这里的partName是用file
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        // 添加描述
        String descriptionString = "hello, this file description";
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);

        return uploadFile(description,body);
    }


    /**
     * 上传文件
     * @param description 文件描述
     * @param body 文件实体
     * @return Observable<UploadEntity>
     */
    private static Observable<UploadEntity> uploadFile(RequestBody description, MultipartBody.Part body){
        FileService service = Network.remote(FileService.class);
        return service.uploadFile(description,body)
                .compose(RxChain.handleNetEvent());
    }

    /**
     * 上传文件 Retrofit 调用,进度回调
     * @param file 文件
     * @return Observable<UploadEntity>
     */
    public static Call<RxRspModel<UploadEntity>> uploadCallbackFile(@NonNull File file, @NonNull FileCallback callback){
        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        //通过该行代码将RequestBody转换成特定的FileRequestBody
        FileRequestBody fileBody = new FileRequestBody(requestFile, callback);

        // MultipartBody.Part  和后端约定好Key，这里的partName是用file
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), fileBody);

        // 添加描述
        String descriptionString = "hello, this file description";
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);

        return uploadCallbackFile(description,body);
    }

    /**
     * 上传文件
     * @param description 文件描述
     * @param body 文件实体
     * @return Call<RxRspModel<UploadEntity>>
     */
    private static Call<RxRspModel<UploadEntity>> uploadCallbackFile(RequestBody description, MultipartBody.Part body){
        FileService service = Network.remote(FileService.class);
        return service.uploadCallFile(description,body);
    }

    /**
     * 上传文件
     * @param file 文件
     * @param callback 回调对象
     */
    public static void uploadFile(@NonNull File file, DataSource.Callback<UploadEntity> callback){
        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part  和后端约定好Key，这里的partName是用file
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        // 添加描述
        String descriptionString = "hello, this file description";
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);

        uploadFile(description,body,callback);
    }

    /**
     * 上传文件
     * @param description 文件描述
     * @param body 文件实体
     * @param callback 回调对象
     */
    private static void uploadFile(@NonNull RequestBody description,
                                   @NonNull MultipartBody.Part body,
                                   @NonNull final DataSource.Callback<UploadEntity> callback){
        FileService service = Network.remote(FileService.class);
        service.uploadFile(description,body)
                .compose(RxChain.handleNetEvent())
                .subscribe(uploadEntity -> {
                        // 请求成功返回
                        if (callback != null){
                            callback.onDataLoaded(uploadEntity);
                    }
                }, new RxErrorConsumer() {
                    @Override
                    public void onError(int stringRes) {
                        callback.onDataNotAvailable(stringRes);
                    }
                });
    }





}
