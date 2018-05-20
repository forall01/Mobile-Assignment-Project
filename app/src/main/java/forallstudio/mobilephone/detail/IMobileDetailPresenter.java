package forallstudio.mobilephone.detail;

public interface IMobileDetailPresenter {

    interface View {

    }

    interface Action {
        void getMobileDetailById(int mobileId);

        void getMobileImages(int mobileId);
    }

}