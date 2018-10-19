package edu.gatech.cs2340.myapplication.controllers.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.gatech.cs2340.myapplication.Callback;
import edu.gatech.cs2340.myapplication.R;
import edu.gatech.cs2340.myapplication.controllers.ItemCardRecyclerViewAdapter;
import edu.gatech.cs2340.myapplication.controllers.LocationCardRecyclerViewAdapter;
import edu.gatech.cs2340.myapplication.models.DonationItem;
import edu.gatech.cs2340.myapplication.models.LocationEntry;
import edu.gatech.cs2340.myapplication.models.TheCloud;

public class ViewItemFragment extends Fragment {

    public ViewItemFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_items, container, false);
        RecyclerView rView = view.findViewById(R.id.recycler_view1);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(new GridLayoutManager(getContext(), 1,
                RecyclerView.VERTICAL, false));

        ArrayList<DonationItem> hardCode = new ArrayList<>();
        DonationItem one = new DonationItem
                ("10:00", "GoodWill", "Piano",
                        "haha", "$150", "Other");
        DonationItem two = new DonationItem
                ("11:29", "SalvationArmy", "1.0 GPA",
                        "haha", "$-2", "Other");
        hardCode.add(one);
        hardCode.add(two);
        final ItemCardRecyclerViewAdapter adapter = new
                ItemCardRecyclerViewAdapter(hardCode);
        rView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) { }
}
