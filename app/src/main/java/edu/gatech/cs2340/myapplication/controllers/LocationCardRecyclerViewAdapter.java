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

/**
 * adapter class for the location view UI
 */
public class LocationCardRecyclerViewAdapter extends RecyclerView
        .Adapter<LocationCardRecyclerViewAdapter.LocationCardViewHolder> {
    private List<LocationEntry> mLocationList;
    // private ImageRequester imageRequester;

    public class LocationCardViewHolder extends RecyclerView.ViewHolder {
        private final TextView locationName;
        private final TextView locationAddress;
        private final TextView locationType;
        private final TextView locationPhone;
        private final TextView locationLatLon;
        private final TextView locationWebsite;
        private final TextView locationZip;
        private final ImageView dropdownButton;
        private boolean open = false;

        /**
         * constructor for a LocationCardViewHolder
         * @param itemView the view of the card view
         */
        private LocationCardViewHolder(View itemView) {
            super(itemView);
            locationName = itemView.findViewById(R.id.locationName);
            locationAddress = itemView.findViewById(R.id.locationAddress);
            locationType = itemView.findViewById(R.id.locationType);
            locationPhone = itemView.findViewById(R.id.locationPhone);
            locationLatLon = itemView.findViewById(R.id.locationLatLon);
            locationWebsite = itemView.findViewById(R.id.locationWebsite);
            locationZip = itemView.findViewById(R.id.locationZip);
            dropdownButton = itemView.findViewById(R.id.dropdownButton);
        }
    }

    /**
     * constructor for a card view adapter
     * @param productList the list of entries to display
     */
    public LocationCardRecyclerViewAdapter(List<LocationEntry> productList) {
        this.mLocationList = productList;
        // imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public LocationCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                     int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R
                .layout.location_card, parent, false);
        return new LocationCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull final LocationCardViewHolder
                                             holder, int position) {
        if (mLocationList != null && position < mLocationList.size()) {
            LocationEntry location = mLocationList.get(position);
            holder.locationName.setText(location.name);
            holder.locationAddress.setText(location.streetAddress + ", "
                    + location.city + ", " + location.state);
            holder.locationType.setText(location.type);
            holder.locationPhone.setText(location.phone);
            holder.locationLatLon.setText(location.latitude + ", " + location
                    .longitude);
            holder.locationWebsite.setText(location.website);
            holder.locationZip.setText(location.zip);
            holder.dropdownButton.setOnClickListener(new View.OnClickListener(
                    ) {
                final private LocationCardViewHolder h = holder;
                @Override
                public void onClick(View v) {
                    // v.findViewById(R.id.locationZip).setVisibility(View
                    // .VISIBLE);
                    if (!h.open) {
                        Log.i("dropdown button", "maximizing view");
                        h.locationPhone.setVisibility(View.VISIBLE);
                        h.locationLatLon.setVisibility(View.VISIBLE);
                        h.locationWebsite.setVisibility(View.VISIBLE);
                        h.locationZip.setVisibility(View.VISIBLE);
                        h.dropdownButton.setImageResource(R.drawable
                                .ic_arrow_drop_up_black_24dp);
                    } else {
                        Log.i("dropdown button", "minimizing view");
                        h.locationPhone.setVisibility(View.GONE);
                        h.locationLatLon.setVisibility(View.GONE);
                        h.locationWebsite.setVisibility(View.GONE);
                        h.locationZip.setVisibility(View.GONE);
                        h.dropdownButton.setImageResource(R.drawable
                                .ic_arrow_drop_down_black_24dp);
                    }
                    h.open = !h.open;
                }
            });
            // imageRequester.setImageFromUrl(holder.productImage, product.url);
        }
    }

    @Override
    public int getItemCount() {
        return mLocationList.size();
    }

    /**
     * updates the list of entries displayed
     * @param locationList the new list of entries
     */
    public void updateList(List<LocationEntry> locationList) {
        mLocationList = locationList;
        notifyDataSetChanged();
    }

    /**
     * a getter for the entry list
     * @return the location entry list
     */
    public List<LocationEntry> getmLocationList() {
        return mLocationList;
    }
}
