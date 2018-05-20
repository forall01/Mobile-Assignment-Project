package forallstudio.mobilephone.detail;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.List;

import forallstudio.mobilephone.Injection;
import forallstudio.mobilephone.R;
import forallstudio.mobilephone.data.MobileImage;
import forallstudio.mobilephone.databinding.FragmentMobileDetailBinding;
import forallstudio.mobilephone.utils.Utils;

public class MobileDetailFragment extends Fragment {

    private static final String ARGS_MOBILE_ID = "ARGS_MOBILE_ID";

    private MobileDetailViewModel viewModel;
    private IMobileDetailPresenter.Action presenter;
    private int mobileId;

    public static MobileDetailFragment newInstance(int mobileId) {
        MobileDetailFragment fragment = new MobileDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_MOBILE_ID, mobileId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mobileId = getArguments().getInt(ARGS_MOBILE_ID, 0);
        viewModel = new MobileDetailViewModel();
        presenter = new MobileDetailPresenter(Injection.provideMobileRepository(), viewModel);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentMobileDetailBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_mobile_detail, container, false);
        setViewPagerHeight(binding.pagerMobileImages);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.getMobileDetailById(mobileId);
        presenter.getMobileImages(mobileId);
    }

    @BindingAdapter({"bind:items"})
    public static void mobileImagesAdapter(ViewPager view, List<MobileImage> mobileImages) {
        MobileImageAdapter adapter = new MobileImageAdapter(mobileImages);
        view.setAdapter(adapter);
    }

    private void setViewPagerHeight(ViewPager pager) {
        ViewGroup.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.height = get35PercentOfScreenHeight();
        pager.setLayoutParams(params);
    }

    private int get35PercentOfScreenHeight() {
        try {
            Point defaultDisplay = Utils.getDefaultDisplay(getContext());
            return (int) (defaultDisplay.y * 0.35);
        } catch (NullPointerException e) {
            return (int) Utils.convertDpToPixel(getContext(), 200);
        }
    }

}