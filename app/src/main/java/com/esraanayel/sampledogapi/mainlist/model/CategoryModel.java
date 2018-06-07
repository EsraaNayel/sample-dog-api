package com.esraanayel.sampledogapi.mainlist.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import static android.os.UserHandle.readFromParcel;

/**
 * Created by Esraa on 6/6/2018.
 */

public class CategoryModel implements Parcelable {

//    @SerializedName("status")
//    private String id;

//    @SerializedName("status")
//    private String categoryTitle;

    @SerializedName("message")
    private List<String>message;

    public CategoryModel() {
    }

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

//    public String getCategoryTitle() {
//        return categoryTitle;
//    }
//
//    public void setCategoryTitle(String categoryTitle) {
//        this.categoryTitle = categoryTitle;
//    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(this.getCategoryTitle());
        dest.writeList(this.getMessage());
    }

    public CategoryModel(Parcel in) {
        readFromParcel(in);
    }

    @SuppressWarnings("unchecked")
    public static final Creator CREATOR = new Creator() {
        @Override
        public CategoryModel createFromParcel(Parcel in) {
            return new CategoryModel(in);
        }

        @Override
        public Object[] newArray(int size) {
            return new CategoryModel[size];
        }
    };
}
