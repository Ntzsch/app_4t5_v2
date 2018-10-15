package edu.gatech.cs2340.myapplication.controllers;

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
import edu.gatech.cs2340.myapplication.models.LocationEntry;

public class LocationCardRecyclerViewAdapter extends RecyclerView.Adapter<LocationCardRecyclerViewAdapter.LocationCardViewHolder> {  // TODO:
    private List<LocationEntry> m_location_list;
    // private ImageRequester imageRequester;

    public class LocationCardViewHolder extends RecyclerView.ViewHolder {
        public TextView location_name;
        public TextView location_address;
        public TextView location_type;
        public TextView location_phone;
        public TextView location_lat_lon;
        public TextView location_website;
        public TextView location_zip;
        public ImageView dropdown_button;
        public boolean open = false;


        public LocationCardViewHolder(View itemView) {
            super(itemView);
            location_name = itemView.findViewById(R.id.location_name);
            location_address = itemView.findViewById(R.id.location_address);
            location_type = itemView.findViewById(R.id.location_type);
            location_phone = itemView.findViewById(R.id.location_phone);
            location_lat_lon = itemView.findViewById(R.id.location_lat_lon);
            location_website = itemView.findViewById(R.id.location_website);
            location_zip = itemView.findViewById(R.id.location_zip);
            dropdown_button = itemView.findViewById(R.id.dropdown_button);
        }
    }

    public LocationCardRecyclerViewAdapter(List<LocationEntry> productList) {
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
    public void onBindViewHolder(@NonNull final LocationCardViewHolder holder, int position) {
        if (m_location_list != null && position < m_location_list.size()) {
            LocationEntry location = m_location_list.get(position);
            holder.location_name.setText(location.name);
            holder.location_address.setText(location.street_address + ", " + location.city + ", " + location.state);
            holder.location_type.setText(location.type);
            holder.location_phone.setText(location.phone);
            holder.location_lat_lon.setText(location.latitude + ", " + location.longitude);
            holder.location_website.setText(location.website);
            holder.location_zip.setText(location.zip);
            holder.dropdown_button.setOnClickListener(new View.OnClickListener() {
                final private LocationCardViewHolder h = holder;
                @Override
                public void onClick(View v) {
                    // v.findViewById(R.id.location_zip).setVisibility(View.VISIBLE);
                    if (h.open == false) {
                        Log.i("dropdown button", "maximizing view");
                        h.location_lat_lon.setVisibility(View.VISIBLE);
                        h.location_website.setVisibility(View.VISIBLE);
                        h.location_zip.setVisibility(View.VISIBLE);
                        h.dropdown_button.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp);
                    } else {
                        Log.i("dropdown button", "minimizing view");
                        h.location_lat_lon.setVisibility(View.GONE);
                        h.location_website.setVisibility(View.GONE);
                        h.location_zip.setVisibility(View.GONE);
                        h.dropdown_button.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp);
                    }
                    h.open = !h.open;
                }
            });
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
