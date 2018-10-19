package edu.gatech.cs2340.myapplication.controllers;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.gatech.cs2340.myapplication.R;
import edu.gatech.cs2340.myapplication.models.DonationItem;
import edu.gatech.cs2340.myapplication.models.LocationEntry;


public class ItemCardRecyclerViewAdapter
        extends RecyclerView.Adapter<
        ItemCardRecyclerViewAdapter.ItemCardViewHolder> {
    private List<DonationItem> mItemList;

    public class ItemCardViewHolder extends RecyclerView.ViewHolder {
        public TextView itemName;
        public TextView itemLocation;
        public TextView itemPrice;

        public ItemCardViewHolder(View view) {
            super(view);
            itemName = view.findViewById(R.id.itemName);
            itemLocation = view.findViewById(R.id.itemLocation);
            itemPrice = view.findViewById(R.id.itemPrice);
        }
    }

    public ItemCardRecyclerViewAdapter(List<DonationItem> mItemList) {
        this.mItemList = mItemList;
    }
    @NonNull
    @Override
    public ItemCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                     int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R
                .layout.item_card, parent, false);
        return new ItemCardViewHolder(layoutView);
    }
    @Override
    public void onBindViewHolder(@NonNull final ItemCardViewHolder holder,
                                 int position) {
        if (mItemList != null && position <mItemList.size()) {
            DonationItem item = mItemList.get(position);
            holder.itemName.setText(item.getSmallDescription());
            holder.itemLocation.setText(item.getLocation());
            holder.itemPrice.setText(item.getValue());
        }

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

}