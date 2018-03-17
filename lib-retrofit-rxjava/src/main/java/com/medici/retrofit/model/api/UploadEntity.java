package com.medici.retrofit.model.api;

/**
 * ***************************************
 *
 * @desc: 上传文件的返回结果
 * @author：李宗好
 * @time: 2018/1/30 0030 17:36
 * @email：lzh@cnbisoft.com
 * @version：
 * @history:
 *
 * ***************************************
 */
public class UploadEntity {
    /**
     * 名字
     */
    private String name;
    /**
     * 图片地址
     */
    private String src;
    /**
     * 大小
     */
    private String size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
