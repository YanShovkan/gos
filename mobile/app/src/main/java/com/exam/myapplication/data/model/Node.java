package com.exam.myapplication.data.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;


public class Node implements Parcelable {
    private final Long id;
    private final String title;
    private final String description;

    public Node(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Node(String title, String description) {
        id = -1L;
        this.title = title;
        this.description = description;
    }

    protected Node(Parcel in) {
        id = in.readLong();
        title = in.readString();
        description = in.readString();
    }

    public static final Creator<Node> CREATOR = new Creator<Node>() {
        @Override
        public Node createFromParcel(Parcel in) {
            return new Node(in);
        }

        @Override
        public Node[] newArray(int size) {
            return new Node[size];
        }
    };

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Node copy(Long id, String title, String description) {
        return new Node(
                id == null ? this.id : id,
                title == null ? this.title : title,
                description == null ? this.description : description
        );
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(description);
    }
}
