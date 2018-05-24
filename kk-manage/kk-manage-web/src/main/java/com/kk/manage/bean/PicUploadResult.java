package com.kk.manage.bean;
/**
 * @ClassName PicUploadResult 上传文件的实体类
 * @Author Administrator
 * @Param
 * @Return
 * @Throws
 * @Date 2018/5/2 22:52
 */
public class PicUploadResult {
    /**
     * 文件是否上传成功的标识
     */
    private Integer error;
    
    private String url;
    
    private String width;
    
    private String height;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
    
    

}
