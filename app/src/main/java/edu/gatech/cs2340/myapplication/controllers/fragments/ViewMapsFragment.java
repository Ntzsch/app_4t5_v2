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

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.gatech.cs2340.myapplication.Callback;
import edu.gatech.cs2340.myapplication.R;
import edu.gatech.cs2340.myapplication.controllers.InventoryCardRecyclerViewAdapter;
import edu.gatech.cs2340.myapplication.models.InventoryEntry;
import edu.gatech.cs2340.myapplication.models.LocationEntry;
import edu.gatech.cs2340.myapplication.models.TheCloud;

public class ViewMapsFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap mMap;

    public ViewMapsFragment() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_map, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        SupportMapFragment mapFragment = (SupportMapFragment)getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        final List<LocationEntry> locationList = new ArrayList<>();

        TheCloud.getLocations(new Callback<List<LocationEntry>>() {
            @Override
            public void callback(List<LocationEntry> value) {
                for (LocationEntry location:value) {
                    locationList.add(location);
                }
            }
        });

        //iterate through the list and add a pin for each element in the model
        for (LocationEntry locationEntry : locationList) {
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
            // TODO Auto-generated method stub
            return null;
        }

    }
}


