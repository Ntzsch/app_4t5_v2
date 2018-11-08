package edu.gatech.cs2340.myapplication.controllers.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;


import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.navigation.Navigation;
import edu.gatech.cs2340.myapplication.Callback;
import edu.gatech.cs2340.myapplication.R;
import edu.gatech.cs2340.myapplication.models.LocationEntry;
import edu.gatech.cs2340.myapplication.models.TheCloud;

/**
 * AdvancedSearchFragment
 *
 * Adds Advanced Search parameters for search by item, location, category,
 * and price
 *
 * @author Team4t5
 * @version 1.0
 *
 */
public class AdvancedSearchFragment extends Fragment {
    private Spinner locationSpinner;
    private Spinner categorySpinner;

    private final List<String> allLocations = new ArrayList<>();
    private ArrayAdapter<String> adapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup
            container, Bundle savedInstanceState) {
        allLocations.add("All");
        TheCloud.getLocations(new Callback<List<LocationEntry>>() {
            @Override
            public void callback(List<LocationEntry> value) {
                for (LocationEntry entry : value) {
                    allLocations.add(entry.getName());
                }
                adapter.notifyDataSetChanged();
            }
        });
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_advanced_search, container,
                false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        locationSpinner = view.findViewById(R.id.locationSpinner);
        categorySpinner = view.findViewById(R.id.categorySpinner);
        //TextInputLayout advSearchText = view.findViewById(R.id.advSearchText);
        //TextInputLayout highPrice = view.findViewById(R.id.highPrice);
        //TextInputLayout lowPrice = view.findViewById(R.id.lowPrice);
        MaterialButton searchButton = view.findViewById(R.id.searchButton);


        String[] categories = new String[] {
                "All", "Clothing", "Hat", "Kitchen", "Electronics", "Household",
                "Other"
        };
        ArrayAdapter<String> catAdapter = new ArrayAdapter<>(Objects
                .requireNonNull(this.getActivity()), android.R.layout
                .simple_spinner_item, categories);
        catAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        categorySpinner.setAdapter(catAdapter);

        adapter = new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_spinner_item, allLocations);
        adapter.setDropDownViewResource(android.R.layout.
                simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Bundle bundle = new Bundle();
                bundle.putString("location_constraint", locationSpinner
                        .getSelectedItem().toString());
                bundle.putString("category_constraint", categorySpinner
                        .getSelectedItem().toString());

                Navigation.findNavController(view).navigate(R.id
                        .nav_view_items, bundle);
            }
        });


    }
}
