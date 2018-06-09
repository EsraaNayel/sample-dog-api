package com.esraanayel.sampledogapi.utils;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Esraa on 6/6/2018.
 */

public class ImageLoader {
    static final String IMAGES_BASE_URL = "https://dog.ceo/api/breed/";

    public static void loadImage(String imageUrl, @ColorRes @DrawableRes int placeHolderResourceID, ImageView imageView) {
        Glide.with(imageView.getContext()).asBitmap().load(IMAGES_BASE_URL + "bulldog/images" + imageUrl).into(imageView);
    }

    public static void loadImage(String imageUrl, ImageView imageView) {
//        Glide.with(imageView.getContext()).asBitmap().load(imageUrl).into(imageView);
        Glide.with(imageView.getContext()).asDrawable().load(imageUrl).into(imageView);

    }
}
