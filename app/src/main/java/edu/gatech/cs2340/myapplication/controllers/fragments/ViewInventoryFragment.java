package edu.gatech.cs2340.myapplication.controllers.fragments;

import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.gatech.cs2340.myapplication.Callback;
import edu.gatech.cs2340.myapplication.R;
import edu.gatech.cs2340.myapplication.controllers.InventoryCardRecyclerViewAdapter;
import edu.gatech.cs2340.myapplication.models.InventoryEntry;
import edu.gatech.cs2340.myapplication.models.TheCloud;
import androidx.annotation.NonNull;


public class ViewInventoryFragment extends Fragment implements SearchView
        .OnQueryTextListener{
    private InventoryCardRecyclerViewAdapter mAdapter;
    private List<InventoryEntry> inventoryList;
    private String categoryConstraint = null;
    private String locationConstraint = null;

    public ViewInventoryFragment() { }
    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_view_items, container, false);

        RecyclerView rView = view.findViewById(R.id.recycler_view1);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(new GridLayoutManager(getContext(), 1,
                RecyclerView.VERTICAL, false));

        this.mAdapter = new InventoryCardRecyclerViewAdapter(new ArrayList<InventoryEntry>(), rView);

        TheCloud.getInventory(new Callback<List<InventoryEntry>>() {
            @Override
            public void callback(List<InventoryEntry> value) {
                inventoryList = value;
                mAdapter.updateList(value);
            }
        });
        rView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        categoryConstraint = getArguments().getString("category_constraint");
        locationConstraint = getArguments().getString("location_constraint");
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String input = newText.toLowerCase();
        List<InventoryEntry> newList = new ArrayList<>();
        if (categoryConstraint != null && (!categoryConstraint.equals("All"))) {
            for (InventoryEntry ie : inventoryList) {
                if (ie.getCategory().toLowerCase().contains(
                        (categoryConstraint.toLowerCase())) && ie.getSmallDescription()
                        .toLowerCase().contains(input)) {
                    newList.add(ie);
                }
            }
        } else if (locationConstraint != null && (!locationConstraint.equals("All"))) {
            for (InventoryEntry ie : inventoryList) {
                if (ie.getLocation().toLowerCase().contains(locationConstraint.toLowerCase())
                        && ie.getSmallDescription().toLowerCase().contains(input)) {
                    newList.add(ie);
                }
            }
        } else {
            for (InventoryEntry ie : inventoryList) {
                if (ie.getSmallDescription().toLowerCase().contains(input)) {
                    newList.add(ie);
                }
            }
        }

        mAdapter.updateList(newList);

        return true;
    }

}
