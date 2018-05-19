package forallstudio.mobilephone.allmobile;

import forallstudio.mobilephone.data.Mobile;
import forallstudio.mobilephone.data.source.MobileSortType;

public interface IMobileListPresenter {

    interface View {
        void openMobileDetailScreen(int mobileId);
    }

    interface Action {
        void getAllMobileList(MobileSortType sort);

        void sortAllMobileList(MobileSortType sort);

        void onMobileClicked(Mobile mobile);

        void onFavoriteClicked(Mobile mobile);
    }

}