package forallstudio.mobilephone.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import forallstudio.mobilephone.R;
import forallstudio.mobilephone.allmobile.MobileListFragment;
import forallstudio.mobilephone.data.source.MobileSortType;
import forallstudio.mobilephone.databinding.FragmentMobileBinding;
import forallstudio.mobilephone.favorite.MobileFavoriteFragment;

public class MobileFragment extends Fragment implements IMobilePresenter.View {

    private FragmentMobileBinding binding;

    private int indexSortSelected = 0; // default

    public static MobileFragment newInstance() {
        return new MobileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_mobile_sort_options, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.sort) {
            alertSortOption();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void alertSortOption() {
        new AlertDialog.Builder(getContext())
                .setSingleChoiceItems(R.array.sort_option, indexSortSelected,
                        (dialogInterface, position) -> {
                            updateSortOptionSelected(position);
                            sendSortFilterToMobileAndFavoriteScreen(position);
                            dialogInterface.dismiss();
                        }
                )
                .show();
    }

    private void initView() {
        binding.tabLayout.setupWithViewPager(binding.pagerMobileInfo);
        binding.pagerMobileInfo.setAdapter(new MobileInfoAdapter(getChildFragmentManager()));
    }

    private void updateSortOptionSelected(int position) {
        indexSortSelected = position;
    }

    private void sendSortFilterToMobileAndFavoriteScreen(int position) {
        MobileSortType sort = getMobileSortType(position);
        Fragment mobileList = getFragmentFromViewPager(binding.pagerMobileInfo.getId(), 0);
        Fragment favoriteList = getFragmentFromViewPager(binding.pagerMobileInfo.getId(), 1);
        if (mobileList instanceof OnMobileSortTypeChangeListener) {
            ((OnMobileSortTypeChangeListener) mobileList).onSortChange(sort);
        }
        if (favoriteList instanceof OnMobileSortTypeChangeListener) {
            ((OnMobileSortTypeChangeListener) favoriteList).onSortChange(sort);
        }
    }

    private MobileSortType getMobileSortType(int position) {
        if (position == 0) {
            return MobileSortType.PRICE_LOW_TO_HIGH;
        } else if (position == 1) {
            return MobileSortType.PRICE_HIGH_TO_LOW;
        } else if (position == 2) {
            return MobileSortType.RATING;
        } else {
            return MobileSortType.PRICE_LOW_TO_HIGH; // default
        }
    }

    @Nullable
    private Fragment getFragmentFromViewPager(int viewId, int position) {
        String tag = findTagFragmentFromViewPager(viewId, position);
        return getChildFragmentManager().findFragmentByTag(tag);
    }

    private String findTagFragmentFromViewPager(int viewId, long index) {
        return "android:switcher:" + viewId + ":" + index;
    }

    private class MobileInfoAdapter extends FragmentPagerAdapter {

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