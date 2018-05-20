package forallstudio.mobilephone.detail;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import forallstudio.mobilephone.R;
import forallstudio.mobilephone.data.MobileImage;
import forallstudio.mobilephone.databinding.ItemMobileImageBinding;

public class MobileImageAdapter extends PagerAdapter {

    private List<MobileImage> images = new ArrayList<>();

    public MobileImageAdapter(List<MobileImage> images) {
        this.images = images;
    }

    @Override
    public int getCount() {
        if (images == null) {
            return 0;
        }
        return images.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ItemMobileImageBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(container.getContext()),
                R.layout.item_mobile_image, container,
                false
        );
        MobileImage mobileImage = images.get(position);
        binding.setImageUrl(mobileImage.getUrl());
        container.addView(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

}