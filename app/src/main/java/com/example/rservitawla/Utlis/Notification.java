package com.example.rservitawla.Utlis;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Notification {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "title")
    String title;

    @ColumnInfo(name = "detail")
    String detail;

    @ColumnInfo(name = "date")
    String date;
    private String resolution;

    private String camera;

    private String dvr;

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getDvr() {
        return dvr;
    }

    public void setDvr(String dvr) {
        this.dvr = dvr;
    }

    public Notification(String title, String detail, String date, String resolution, String camera, String dvr) {
        this.title = title;
        this.detail = detail;
        this.date = date;
        this.resolution = resolution;
        this.camera = camera;
        this.dvr = dvr;
    }

    public Notification() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
