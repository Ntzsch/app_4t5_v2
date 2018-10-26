package edu.gatech.cs2340.myapplication.controllers.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.gatech.cs2340.myapplication.Callback;
import edu.gatech.cs2340.myapplication.R;
import edu.gatech.cs2340.myapplication.controllers.CustomItemClickListener;
import edu.gatech.cs2340.myapplication.controllers.InventoryCardRecyclerViewAdapter;
import edu.gatech.cs2340.myapplication.models.InventoryEntry;
import edu.gatech.cs2340.myapplication.models.TheCloud;

public class EditInventoryFragment extends Fragment {
    public EditInventoryFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_edit_inventory, container, false);

        FloatingActionButton addButton = view.findViewById(R.id.floating_action_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Navigation.findNavController(view).navigate(R.id
                        .nav_add_inventory);
            }
        });
        RecyclerView rView = view.findViewById(R.id.recycler_view2);
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
        /*
        RecyclerView rView = view.findViewById(R.id.recycler_view2);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(new GridLayoutManager(getContext(), 1,
                RecyclerView.VERTICAL, false));

        // ---------TEMPORARY CODE FROM HERE-----------//
        final ArrayList<InventoryEntry> hardCode = new ArrayList<>();
        InventoryEntry one = new InventoryEntry
                ("10:00", "GoodWill", "Piano",
                        "haha", "$150", "Other");
        InventoryEntry two = new InventoryEntry
                ("11:29", "SalvationArmy", "1.0 GPA",
                        "haha", "$-2", "Other");
        hardCode.add(one);
        hardCode.add(two);
        // ------------------TO HERE--------------------//

        final InventoryCardRecyclerViewAdapter adapter = new
                InventoryCardRecyclerViewAdapter(hardCode, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                InventoryEntry clicked = hardCode.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("time", clicked.getTimeStamp());
                bundle.putString("location", clicked.getLocation());
                bundle.putString("smallDescript", clicked.getSmallDescription());
                bundle.putString("longDescript", clicked.getFullDescription());
                bundle.putString("value", clicked.getValue());
                bundle.putString("category", clicked.getCategory());

                Navigation.findNavController(v).navigate(R.id
                        .nav_view_inventory_details, bundle);
            }
        });

        rView.setAdapter(adapter);
        */
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) { }
}
