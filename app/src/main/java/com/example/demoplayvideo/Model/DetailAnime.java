package com.example.demoplayvideo.Model;

public class DetailAnime {
    private String vnTitleDetail, engTitleDetail, mainImage, datePublish, view, movieSchedule, description;

    public DetailAnime(String vnTitleDetail, String engTitleDetail, String mainImage, String datePublish, String view, String movieSchedule, String description) {
        this.vnTitleDetail = vnTitleDetail;
        this.engTitleDetail = engTitleDetail;
        this.mainImage = mainImage;
        this.datePublish = datePublish;
        this.view = view;
        this.movieSchedule = movieSchedule;
        this.description = description;
    }

    public String getVnTitleDetail() {
        return vnTitleDetail;
    }

    public void setVnTitleDetail(String vnTitleDetail) {
        this.vnTitleDetail = vnTitleDetail;
    }

    public String getEngTitleDetail() {
        return engTitleDetail;
    }

    public void setEngTitleDetail(String engTitleDetail) {
        this.engTitleDetail = engTitleDetail;
    }

    public String getDatePublish() {
        return datePublish;
    }

    public void setDatePublish(String datePublish) {
        this.datePublish = datePublish;
    }

    public String getMovieSchedule() {
        return movieSchedule;
    }

    public void setMovieSchedule(String movieSchedule) {
        this.movieSchedule = movieSchedule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
}
