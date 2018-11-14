package edu.gatech.cs2340.myapplication.controllers;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import edu.gatech.cs2340.myapplication.R;
import edu.gatech.cs2340.myapplication.models.InventoryEntry;


public class InventoryCardRecyclerViewAdapter
        extends RecyclerView.Adapter<
        InventoryCardRecyclerViewAdapter.ItemCardViewHolder> {

    private List<InventoryEntry> mInventoryList;
    private List<InventoryEntry> filteredList;
    private final View.OnClickListener mOnClickListener;

    public class ItemCardViewHolder extends RecyclerView.ViewHolder {
        private final TextView itemName;
        private final TextView itemLocation;
        private final TextView itemPrice;

        ItemCardViewHolder(View view) {
            super(view);
            itemName = view.findViewById(R.id.itemName);
            itemLocation = view.findViewById(R.id.itemLocation);
            itemPrice = view.findViewById(R.id.itemPrice);
        }
    }

    public InventoryCardRecyclerViewAdapter(List<InventoryEntry> mItemList, final RecyclerView recyclerView) {
        this.mInventoryList = mItemList;
        this.mOnClickListener = new View.OnClickListener(){
            public void onClick(View v){
                Log.e("TEST", "onClick: ");
                int itemPosition = recyclerView.getChildLayoutPosition(v);
                InventoryEntry entry = mInventoryList.get(itemPosition);
                Bundle args = new Bundle();
                args.putString("smallDescription", entry.getSmallDescription());
                args.putString("longDescription", entry.getFullDescription());
                args.putString("location", entry.getLocation());
                args.putString("value", entry.getValue());
                args.putString("time", entry.getTimeStamp());
                args.putString("category", entry.getCategory());

                Navigation.findNavController(v).navigate(R.id.nav_view_inventory_details, args);
            }
        };



        /*
        this.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        ); */
    }

    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView){}


    @NonNull
    @Override
    public ItemCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                     int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R
                .layout.item_card, parent, false);
        layoutView.setOnClickListener(mOnClickListener);
        return new ItemCardViewHolder(layoutView);

    }


    @Override
    public void onBindViewHolder(@NonNull final ItemCardViewHolder holder,
                                 int position) {
        if (mInventoryList != null && position < mInventoryList.size()) {
            InventoryEntry item = mInventoryList.get(position);
            holder.itemName.setText(item.getSmallDescription());
            holder.itemLocation.setText(item.getLocation());
            holder.itemPrice.setText(item.getValue());
        }
    }

    @Override
    public int getItemCount() {
        return mInventoryList.size();
    }

<<<<<<< HEAD

=======
>>>>>>> 7e20e9908c91907de433a840d5d5d8880155faa4
    public void updateList(List<InventoryEntry> inventoryList) {
        mInventoryList = new ArrayList<>();
        mInventoryList.addAll(inventoryList);
        notifyDataSetChanged();
    }

    /**
     * a getter for the entry list
     * @return the location entry list
     */
    public List<InventoryEntry> getmInventoryList() {
        return mInventoryList;
    }

}
