package forallstudio.mobilephone.favorite;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import forallstudio.mobilephone.R;
import forallstudio.mobilephone.data.Mobile;
import forallstudio.mobilephone.databinding.ItemMobileFavoriteBinding;

public class MobileFavoriteAdapter extends RecyclerView.Adapter<MobileFavoriteAdapter.MobileFavoriteViewHolder> {

    private List<Mobile> mobiles = new ArrayList<>();
    private MobileFavoriteAdapterListener listener;

    public interface MobileFavoriteAdapterListener {
        void onMobileFavoriteClicked(Mobile mobile);
    }

    public MobileFavoriteAdapter(List<Mobile> mobiles, MobileFavoriteAdapterListener listener) {
        this.mobiles = mobiles;
        this.listener = listener;
    }

    public void update(List<Mobile> mobiles) {
        this.mobiles = mobiles;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MobileFavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemMobileFavoriteBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.item_mobile_favorite, parent, false);
        return new MobileFavoriteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MobileFavoriteViewHolder holder, int position) {
        Mobile mobile = mobiles.get(position);
        holder.bind(mobile);
        holder.binding.parentFavorite.setOnClickListener(view -> {
            if (listener != null) {
                listener.onMobileFavoriteClicked(mobile);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mobiles == null) {
            return 0;
        }
        return mobiles.size();
    }

    public class MobileFavoriteViewHolder extends RecyclerView.ViewHolder {

        private ItemMobileFavoriteBinding binding;

        public MobileFavoriteViewHolder(ItemMobileFavoriteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Mobile mobile) {
            binding.setItem(mobile);
            binding.executePendingBindings();
        }

        public ItemMobileFavoriteBinding getBinding() {
            return binding;
        }
    }

}