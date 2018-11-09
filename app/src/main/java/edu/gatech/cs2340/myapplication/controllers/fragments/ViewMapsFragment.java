package edu.gatech.cs2340.myapplication.controllers.fragments;

import android.app.SearchManager;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.gatech.cs2340.myapplication.Callback;
import edu.gatech.cs2340.myapplication.R;
import edu.gatech.cs2340.myapplication.controllers.InventoryCardRecyclerViewAdapter;
import edu.gatech.cs2340.myapplication.controllers.LocationCardRecyclerViewAdapter;
import edu.gatech.cs2340.myapplication.models.InventoryEntry;
import edu.gatech.cs2340.myapplication.models.LocationEntry;
import edu.gatech.cs2340.myapplication.models.TheCloud;

/**
 * Main fragment activity controlled by navController
 */
public class ViewMapsFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<LocationEntry> mLocationList;

    public ViewMapsFragment() {

    }

    /**
     * Opens map and fills in the locations
     * @param inflater inflater
     * @param container container
     * @param savedInstanceState instanceState
     * @return a view
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final LocationCardRecyclerViewAdapter mAdapter = new
                LocationCardRecyclerViewAdapter(
                new ArrayList<LocationEntry>());

        TheCloud.getLocations(new Callback<List<LocationEntry>>() {
            @Override
            public void callback(List<LocationEntry> value) {
                mLocationList = value;
                mAdapter.updateList(value);
            }
        });
        mLocationList = mAdapter.getmLocationList();

        // View view = inflater.inflate(R.layout.fragment_view_map, container, false);
        View view = inflater.inflate(R.layout.activity_maps, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return view;
    }


    /**
     * Places markers on the map
     * @param googleMap the map
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        Log.d("onMapReady", "Got Locations");

        mMap.getUiSettings().setZoomControlsEnabled(true);

        //iterate through the list and add a pin for each element in the model
        for (LocationEntry locationEntry : mLocationList) {
            Log.d("onMapReady", "getting latlong");
            double lat = Double.parseDouble(locationEntry.latitude);
            double lng = Double.parseDouble(locationEntry.longitude);
            LatLng loc = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(loc).title(locationEntry.getName()).snippet(locationEntry.phone));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        }

        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
    }

    /**
     * This class implements a custom layout for the pin
     */
    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        /**
         * Make the adapter
         */
        CustomInfoWindowAdapter(){
            // hook up the custom layout view in res/custom_map_pin_layout.xml
            myContentsView = getLayoutInflater().inflate(R.layout.fragment_map_pin_details, null);
        }

        @Override
        public View getInfoContents(Marker marker) {

            TextView locationName = ((TextView)myContentsView.findViewById(R.id.locationName));
            locationName.setText(marker.getTitle());
            TextView locationPhone = ((TextView)myContentsView.findViewById(R.id.locationPhone));
            locationPhone.setText(marker.getSnippet());

            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

    }


}


