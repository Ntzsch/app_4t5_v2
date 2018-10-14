package edu.gatech.cs2340.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LocationCardRecyclerViewAdapter extends RecyclerView.Adapter<LocationCardRecyclerViewAdapter.LocationCardViewHolder> {  // TODO:
    private List<LocationEntry> m_location_list;
    // private ImageRequester imageRequester;

    public class LocationCardViewHolder extends RecyclerView.ViewHolder {
        public TextView location_name;
        public TextView location_address;
        public TextView location_type;
        public TextView location_phone;


        public LocationCardViewHolder(View itemView) {
            super(itemView);
            location_name = itemView.findViewById(R.id.location_name);
            location_address = itemView.findViewById(R.id.location_address);
            location_type = itemView.findViewById(R.id.location_type);
            location_phone = itemView.findViewById(R.id.location_phone);
        }
    }

    LocationCardRecyclerViewAdapter(List<LocationEntry> productList) {
        this.m_location_list = productList;
        // imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public LocationCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_card, parent, false);
        return new LocationCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationCardViewHolder holder, int position) {
        if (m_location_list != null && position < m_location_list.size()) {
            LocationEntry location = m_location_list.get(position);
            holder.location_name.setText(location.name);
            holder.location_address.setText(location.street_address + ", " + location.city + ", " + location.state);
            holder.location_type.setText(location.type);
            holder.location_phone.setText(location.phone);
            // imageRequester.setImageFromUrl(holder.productImage, product.url);
        }
    }

    @Override
    public int getItemCount() {
        return m_location_list.size();
    }

    public void update_list(List<LocationEntry> location_list) {
        m_location_list = location_list;
        notifyDataSetChanged();
    }
}
