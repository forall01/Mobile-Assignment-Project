package forallstudio.mobilephone.allmobile;

import android.app.ProgressDialog;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import forallstudio.mobilephone.Injection;
import forallstudio.mobilephone.R;
import forallstudio.mobilephone.data.Mobile;
import forallstudio.mobilephone.data.source.MobileSortType;
import forallstudio.mobilephone.databinding.FragmentMobileListBinding;
import forallstudio.mobilephone.detail.MobileDetailActivity;
import forallstudio.mobilephone.main.OnMobileSortTypeChangeListener;

public class MobileListFragment extends Fragment implements
        MobileListAdapter.MobileListAdapterListener, IMobileListPresenter.View,
        OnMobileSortTypeChangeListener {

    private ProgressDialog progressDialog;
    private FragmentMobileListBinding binding;
    private MobileListViewModel viewModel;

    private IMobileListPresenter.Action presenter;
    private MobileSortType sort = MobileSortType.PRICE_LOW_TO_HIGH;

    public static MobileListFragment newInstance() {
        return new MobileListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new MobileListViewModel();
        presenter = new MobileListPresenter(Injection.provideMobileRepository(), viewModel, this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_mobile_list, container, false);
        initRecycleView();
        binding.setViewModel(viewModel);
        binding.setListener(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.getAllMobileList(sort);
    }

    @BindingAdapter({"bind:items", "bind:listener"})
    public static void mobileListAdapter(RecyclerView view, List<Mobile> mobiles,
                                         MobileListAdapter.MobileListAdapterListener listener) {
        RecyclerView.Adapter adapter = view.getAdapter();
        if (adapter instanceof MobileListAdapter) {
            ((MobileListAdapter) adapter).update(mobiles);
        } else {
            adapter = new MobileListAdapter(mobiles, listener);
            view.setAdapter(adapter);
        }
    }

    @Override
    public void onMobileClicked(Mobile mobile) {
        presenter.onMobileClicked(mobile);
    }

    @Override
    public void onFavoriteClicked(Mobile mobile) {
        presenter.onFavoriteClicked(mobile);
    }

    @Override
    public void showLoadingDialog() {
        progressDialog = ProgressDialog.show(getContext(), "", getString(R.string.loading), true);
    }

    @Override
    public void hideLoadingDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void openMobileDetailScreen(int mobileId) {
        MobileDetailActivity.open(getContext(), mobileId);
    }

    @Override
    public void onSortChange(MobileSortType sort) {
        this.sort = sort;
        presenter.sortAllMobileList(sort);
    }

    private void initRecycleView() {
        binding.listAllMobile.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false)
        );
    }

}