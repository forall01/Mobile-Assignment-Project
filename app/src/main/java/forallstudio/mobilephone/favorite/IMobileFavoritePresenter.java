package forallstudio.mobilephone.favorite;

import forallstudio.mobilephone.data.Mobile;
import forallstudio.mobilephone.data.source.MobileSortType;

public interface IMobileFavoritePresenter {

    interface View {
        void openMobileDetailScreen(int mobileId);
    }

    interface Action {
        void getAllMobileFavorite(MobileSortType sort);

        void onFavoriteClicked(Mobile mobile);

        void onSwipeToDelete(int mobileId);
    }

}