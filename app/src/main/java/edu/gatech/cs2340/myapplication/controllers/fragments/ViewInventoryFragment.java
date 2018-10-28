package edu.gatech.cs2340.myapplication.controllers.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.gatech.cs2340.myapplication.Callback;
import edu.gatech.cs2340.myapplication.R;
import edu.gatech.cs2340.myapplication.controllers.InventoryCardRecyclerViewAdapter;
import edu.gatech.cs2340.myapplication.models.InventoryEntry;
import edu.gatech.cs2340.myapplication.models.TheCloud;

public class ViewInventoryFragment extends Fragment {

    public ViewInventoryFragment() { }
    @Override
    public void onCreate (Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_view_items, container, false);
        RecyclerView rView = view.findViewById(R.id.recycler_view1);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(new GridLayoutManager(getContext(), 1,
                RecyclerView.VERTICAL, false));

        final InventoryCardRecyclerViewAdapter adapter = new InventoryCardRecyclerViewAdapter(new ArrayList<InventoryEntry>(), rView);
        rView.setAdapter(adapter);
        TheCloud.getInventory(new Callback<List<InventoryEntry>>() {
            @Override
            public void callback(List<InventoryEntry> value) {
                adapter.updateList(value);
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) { }
}
