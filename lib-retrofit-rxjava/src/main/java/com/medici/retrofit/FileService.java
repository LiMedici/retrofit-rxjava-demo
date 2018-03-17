package com.medici.retrofit;

import com.medici.retrofit.model.RxRspModel;
import com.medici.retrofit.model.api.UploadEntity;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * ***************************************
 *
 * @desc: Rxjava+Retrofit 文件上传接口
 * @author：李宗好
 * @time: 2018/3/17 0017 13:52
 * @email：lzh@cnbisoft.com
 * @version：
 * @history:
 *
 * ***************************************
 */
public interface FileService {

    /**
     * 上传头像
     *
     * @param description
     * @param file
     * @return
     */
    @POST("/file/uploadImage.cnbi")
    @Multipart
    Call<RxRspModel<UploadEntity>> uploadCallFile(@Part("description") RequestBody description, @Part MultipartBody.Part file);

    /**
     * 上传头像
     * @param description
     * @param file
     * @return
     */
    @POST("/file/uploadImage.cnbi")
    @Multipart
    Observable<Result<RxRspModel<UploadEntity>>> uploadFile(@Part("description") RequestBody description, @Part MultipartBody.Part file);


    /**
     * 进行文件下载
     * @param param
     * @return
     */
    @FormUrlEncoded
    @POST("fileService")
    Call<ResponseBody> downloadCallFile(@Field("param") String param);

    /**
     * 进行文件下载
     * @param param
     * @return
     */
    @FormUrlEncoded
    @POST("fileService")
    Call<Result<ResponseBody>> downloadFile(@Field("param") String param);
}
