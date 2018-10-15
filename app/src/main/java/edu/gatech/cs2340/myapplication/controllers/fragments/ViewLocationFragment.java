package edu.gatech.cs2340.myapplication.controllers.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.gatech.cs2340.myapplication.Callback;
import edu.gatech.cs2340.myapplication.R;
import edu.gatech.cs2340.myapplication.controllers.LocationCardRecyclerViewAdapter;
import edu.gatech.cs2340.myapplication.models.LocationEntry;
import edu.gatech.cs2340.myapplication.models.The_Cloud;

public class ViewLocationFragment extends Fragment {
    public ViewLocationFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("ViewLocationFragment", "ENTERING");

        View view = inflater.inflate(R.layout.fragment_view_location, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, RecyclerView.VERTICAL, false));

        final LocationCardRecyclerViewAdapter adapter = new LocationCardRecyclerViewAdapter(
                new ArrayList<LocationEntry>());
        recyclerView.setAdapter(adapter);

        The_Cloud.get_locations(new Callback<List<LocationEntry>>() {
            @Override
            public void callback(List<LocationEntry> value) {
                adapter.update_list(value);
            }
        });

        int largePadding = 16; //getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing);
        int smallPadding = 16; //getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small);
        // recyclerView.addItemDecoration(new ProductGridItemDecoration(largePadding, smallPadding));


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {}
}
