package com.example.videoproject;


/**
 * Created by Administrator on 2018/7/19/019.
 */

public class HomeDateBean {


    public HomeDateBean(String videoUrl, String videoContent,String imageUrl ) {
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.videoContent = videoContent;
    }
    private String imageUrl;
    private String videoUrl;
    private String videoContent;
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoContent() {
        return videoContent;
    }

    public void setVideoContent(String videoContent) {
        this.videoContent = videoContent;
    }


}
