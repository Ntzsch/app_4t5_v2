package edu.gatech.cs2340.myapplication.controllers.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.myapplication.Callback;
import edu.gatech.cs2340.myapplication.R;
import edu.gatech.cs2340.myapplication.models.LocationEntry;
import edu.gatech.cs2340.myapplication.models.TheCloud;

public class AdvancedSearchFragment extends Fragment {
    private Spinner locationSpinner;
    private Spinner categorySpinner;
    private EditText advSearchText;
    private EditText highPrice;
    private EditText lowPrice;
    private MaterialButton searchButton;
    private List<LocationEntry> allLocations;


    public AdvancedSearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_advanced_search, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        locationSpinner = view.findViewById(R.id.locationSpinner);
        categorySpinner = view.findViewById(R.id.categorySpinner);
        advSearchText = view.findViewById(R.id.advSearchText);
        highPrice = view.findViewById(R.id.highPrice);
        lowPrice = view.findViewById(R.id.lowPrice);
        searchButton = view.findViewById(R.id.searchButton);


        String[] categories = new String[] {
                "Clothing", "Hat", "Kitchen", "Electronics", "Household",
                "Other"
        };
        ArrayAdapter<String> catAdapter = new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_spinner_item, categories);
        catAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        categorySpinner.setAdapter(catAdapter);


        TheCloud.getLocations(new Callback<List<LocationEntry>>() {
            @Override
            public void callback(List<LocationEntry> value) {
                allLocations = value;
                //make for loop that gets the names of locations
            }
        });
        List<String> locationNames = new ArrayList<>();

        for (LocationEntry location : allLocations) {
            locationNames.add(location.getName());
        }
        String[] locationNames1 = new String[locationNames.size()];
        locationNames.toArray(locationNames1);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_spinner_item, locationNames1);
        adapter.setDropDownViewResource(android.R.layout.
                simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter);

        //        //add button click leading to search function
//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View view) {
//                String hPrice = highPrice.getText().toString();
//                String lPrice = lowPrice.getText().toString();
//                String searchText = advSearchText.getText().toString();

//            }
//        });

    }
}
