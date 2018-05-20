package forallstudio.mobilephone.utils;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MyBindingAdapter {

    @BindingAdapter(value = {"bind:imageUrl", "bind:defaultImage", "bind:errorImage"}, requireAll = false)
    public static void loadImage(ImageView imageView, String imageUrl, Drawable placeHolder, Drawable error) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .apply(new RequestOptions()
                        .placeholder(placeHolder)
                        .error(error)
                        .centerCrop()
                        .dontAnimate()
                        .dontTransform())
                .into(imageView);
    }



}