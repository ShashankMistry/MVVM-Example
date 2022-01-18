package com.shashank.mvvm.Modals;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "actors")
public class actors {

    @PrimaryKey(autoGenerate = true)
    private  int actorId;

    @SerializedName("id")
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("url")
    @ColumnInfo(name = "url")
    private String url;

    @SerializedName("thumbnailUrl")
    @ColumnInfo(name = "thumbnailUrl")
    private String thumbnailUrl;

    public actors(int id, String title, String url, String thumbnailUrl) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
