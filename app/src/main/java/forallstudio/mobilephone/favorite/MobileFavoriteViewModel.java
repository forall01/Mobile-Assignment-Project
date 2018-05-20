package forallstudio.mobilephone.favorite;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.ArrayList;
import java.util.List;

import forallstudio.mobilephone.BR;
import forallstudio.mobilephone.data.Mobile;

public class MobileFavoriteViewModel extends BaseObservable {

    private List<Mobile> mobiles = new ArrayList<>();

    private boolean isNoContentAvailable;

    @Bindable
    public List<Mobile> getMobiles() {
        return mobiles;
    }

    public void setMobiles(List<Mobile> mobiles) {
        this.mobiles = mobiles;
        notifyPropertyChanged(BR.mobiles);
    }

    @Bindable
    public boolean isNoContentAvailable() {
        return isNoContentAvailable;
    }

    public void setNoContentAvailable(boolean noContentAvailable) {
        isNoContentAvailable = noContentAvailable;
        notifyPropertyChanged(BR.noContentAvailable);
    }

}