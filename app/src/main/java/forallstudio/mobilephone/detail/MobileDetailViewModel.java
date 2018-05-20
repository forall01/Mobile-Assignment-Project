package forallstudio.mobilephone.detail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.ArrayList;
import java.util.List;

import forallstudio.mobilephone.BR;
import forallstudio.mobilephone.data.Mobile;
import forallstudio.mobilephone.data.MobileImage;

public class MobileDetailViewModel extends BaseObservable {

    private List<MobileImage> mobileImages = new ArrayList<>();
    private Mobile mobile;

    @Bindable
    public List<MobileImage> getMobileImages() {
        return mobileImages;
    }

    public void setMobileImages(List<MobileImage> mobileImages) {
        this.mobileImages = mobileImages;
        notifyPropertyChanged(BR.mobileImages);
    }

    @Bindable
    public Mobile getMobile() {
        return mobile;
    }

    public void setMobile(Mobile mobile) {
        this.mobile = mobile;
        notifyPropertyChanged(BR.mobile);
    }
}