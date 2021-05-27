package com.arjun.gameshort;

public class videoModel {
    String videoUrl,vTitle,vDes;

    public videoModel(String videoUrl, String vTitle, String vDes) {
        this.videoUrl = videoUrl;
        this.vTitle = vTitle;
        this.vDes = vDes;
    }


    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getvTitle() {
        return vTitle;
    }

    public void setvTitle(String vTitle) {
        this.vTitle = vTitle;
    }

    public String getvDes() {
        return vDes;
    }

    public void setvDes(String vDes) {
        this.vDes = vDes;
    }
}
