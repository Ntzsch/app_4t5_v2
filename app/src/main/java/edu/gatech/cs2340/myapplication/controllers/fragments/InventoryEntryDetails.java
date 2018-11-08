package edu.gatech.cs2340.myapplication.controllers.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.gatech.cs2340.myapplication.R;
/**
 * InventoryEntryDetails
 *
 * Shows item name, description, location, category, price, and time donated
 *
 * @author Team4t5
 * @version 1.0
 *
 */
public class InventoryEntryDetails extends Fragment {



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup
            container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inventory_details,
                container, false);



    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        TextView itemCategory = view.findViewById(R.id.itemCategory);
        TextView itemName = view.findViewById(R.id.itemName);
        TextView itemDescription = view.findViewById(R.id.itemDescription);
        TextView itemLocation = view.findViewById(R.id.itemLocation);
        TextView itemValue = view.findViewById(R.id.itemValue);
        TextView itemTime = view.findViewById(R.id.itemTime);

        if (getArguments() != null) {
            itemName.setText(getArguments().getString("smallDescription"));
            itemDescription.setText(getArguments().getString
                    ("longDescription"));
            itemLocation.setText(getArguments().getString("location"));
            itemValue.setText(getArguments().getString("value"));
            itemTime.setText(getArguments().getString("time"));
            itemCategory.setText(getArguments().getString("category"));
        }

    }

}
