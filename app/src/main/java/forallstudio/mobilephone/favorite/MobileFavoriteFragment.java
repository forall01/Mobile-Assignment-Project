package forallstudio.mobilephone.favorite;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import forallstudio.mobilephone.Injection;
import forallstudio.mobilephone.R;
import forallstudio.mobilephone.data.Mobile;
import forallstudio.mobilephone.data.source.MobileSortType;
import forallstudio.mobilephone.databinding.FragmentMobileFavoriteBinding;
import forallstudio.mobilephone.detail.MobileDetailActivity;
import forallstudio.mobilephone.main.OnMobileSortTypeChangeListener;

public class MobileFavoriteFragment extends Fragment implements
        MobileFavoriteAdapter.MobileFavoriteAdapterListener, IMobileFavoritePresenter.View,
        RecyclerItemTouchHelper.RecyclerItemTouchHelperListener, OnMobileSortTypeChangeListener {

    private FragmentMobileFavoriteBinding binding;
    private MobileFavoriteViewModel viewModel;

    private IMobileFavoritePresenter.Action presenter;
    private MobileSortType sort = MobileSortType.PRICE_LOW_TO_HIGH;

    public static MobileFavoriteFragment newInstance() {
        return new MobileFavoriteFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new MobileFavoriteViewModel();
        presenter = new MobileFavoritePresenter(Injection.provideMobileRepository(), viewModel, this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_mobile_favorite, container, false);
        initRecycleView();
        binding.setViewModel(viewModel);
        binding.setListener(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.getAllMobileFavorite(sort);
    }

    @BindingAdapter({"bind:items", "bind:listener"})
    public static void mobileFavoriteAdapter(RecyclerView view, List<Mobile> mobiles,
                                             MobileFavoriteAdapter.MobileFavoriteAdapterListener listener) {
        RecyclerView.Adapter adapter = view.getAdapter();
        if (adapter instanceof MobileFavoriteAdapter) {
            ((MobileFavoriteAdapter) adapter).update(mobiles);
        } else {
            adapter = new MobileFavoriteAdapter(mobiles, listener);
            view.setAdapter(adapter);
        }
    }

    @Override
    public void openMobileDetailScreen(int mobileId) {
        MobileDetailActivity.open(getContext(), mobileId);
    }

    @Override
    public void onMobileFavoriteClicked(Mobile mobile) {
        presenter.onFavoriteClicked(mobile);
    }

    private void initRecycleView() {
        binding.listAllFavorite.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false)
        );
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(binding.listAllFavorite);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        try {
            Mobile mobile = viewModel.getMobiles().get(position);
            presenter.onSwipeToDelete(mobile.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSortChange(MobileSortType sort) {
        this.sort = sort;
        presenter.getAllMobileFavorite(sort);
    }

}