package edu.gatech.cs2340.myapplication.controllers;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import edu.gatech.cs2340.myapplication.R;
import edu.gatech.cs2340.myapplication.models.InventoryEntry;


public class ItemCardRecyclerViewAdapter
        extends RecyclerView.Adapter<
        ItemCardRecyclerViewAdapter.ItemCardViewHolder> {

    private List<InventoryEntry> mItemList;
    CustomItemClickListener listener;

    public class ItemCardViewHolder extends RecyclerView.ViewHolder {
        public TextView itemName;
        public TextView itemLocation;
        public TextView itemPrice;

        ItemCardViewHolder(View view) {
            super(view);
            itemName = view.findViewById(R.id.itemName);
            itemLocation = view.findViewById(R.id.itemLocation);
            itemPrice = view.findViewById(R.id.itemPrice);
        }
    }

    public ItemCardRecyclerViewAdapter(List<InventoryEntry> mItemList,
                                       CustomItemClickListener listener) {
        this.mItemList = mItemList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ItemCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                     int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R
                .layout.item_card, parent, false);
        final ItemCardViewHolder mViewHolder = new ItemCardViewHolder(layoutView);
        layoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getLayoutPosition());
            }
        });
        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final ItemCardViewHolder holder,
                                 int position) {
        if (mItemList != null && position <mItemList.size()) {
            InventoryEntry item = mItemList.get(position);
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
