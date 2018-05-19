package forallstudio.mobilephone.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import forallstudio.mobilephone.R;
import forallstudio.mobilephone.allmobile.MobileListFragment;
import forallstudio.mobilephone.databinding.FragmentMobileBinding;
import forallstudio.mobilephone.favorite.MobileFavoriteFragment;

public class MobileFragment extends Fragment {

    private FragmentMobileBinding binding;

    public static MobileFragment newInstance() {
        return new MobileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_mobile, container, false);
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar);
        initView();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initView() {
        binding.tabLayout.setupWithViewPager(binding.pagerMobileInfo);
        binding.pagerMobileInfo.setAdapter(new MobileInfoAdapter(getChildFragmentManager()));
    }

    @Nullable
    public Fragment getFragmentFromViewPager(int viewId, int position) {
        String tag = findTagFragmentFromViewPager(viewId, position);
        return getChildFragmentManager().findFragmentByTag(tag);
    }

    public String findTagFragmentFromViewPager(int viewId, long index) {
        return "android:switcher:" + viewId + ":" + index;
    }

    public class MobileInfoAdapter extends FragmentPagerAdapter {

        public MobileInfoAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return MobileListFragment.newInstance();
                case 1:
                    return MobileFavoriteFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.mobile_list);
                case 1:
                    return getString(R.string.favorite_list);
                default:
                    return super.getPageTitle(position);
            }
        }

    }

}