package forallstudio.mobilephone.allmobile;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import forallstudio.mobilephone.R;
import forallstudio.mobilephone.data.Mobile;
import forallstudio.mobilephone.databinding.ItemMobileBinding;

public class MobileListAdapter extends RecyclerView.Adapter<MobileListAdapter.MobileListViewHolder> {

    private List<Mobile> mobiles = new ArrayList<>();
    private MobileListAdapterListener listener;

    public interface MobileListAdapterListener {
        void onMobileClicked(Mobile mobile);

        void onFavoriteClicked(Mobile mobile);
    }

    public MobileListAdapter(List<Mobile> mobiles, MobileListAdapterListener listener) {
        this.mobiles = mobiles;
        this.listener = listener;
    }

    public void update(List<Mobile> mobiles) {
        this.mobiles = mobiles;
        notifyDataSetChanged();
    }

    public Mobile getItem(int position) {
        return mobiles.get(position);
    }

    @NonNull
    @Override
    public MobileListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemMobileBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.item_mobile, parent, false);
        return new MobileListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MobileListViewHolder holder, int position) {
        Mobile mobile = getItem(position);
        holder.binding.parentMobileItem.setOnClickListener(view ->
                listener.onMobileClicked(mobile)
        );
        holder.binding.imageFavorite.setOnClickListener(view ->
                listener.onFavoriteClicked(mobile)
        );
        holder.bind(mobile);
    }

    @Override
    public int getItemCount() {
        if (mobiles == null) {
            return 0;
        }
        return mobiles.size();
    }

    public class MobileListViewHolder extends RecyclerView.ViewHolder {

        private ItemMobileBinding binding;

        public MobileListViewHolder(ItemMobileBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Mobile mobile) {
            binding.setItem(mobile);
            binding.executePendingBindings();
        }
    }

}