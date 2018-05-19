package forallstudio.mobilephone.utils;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class MyBindingAdapter {

    @BindingAdapter(value = {"bind:imageUrl", "bind:defaultImage"}, requireAll = false)
    public static void loadImage(ImageView imageView, String imageUrl, Drawable defaultImage) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

}