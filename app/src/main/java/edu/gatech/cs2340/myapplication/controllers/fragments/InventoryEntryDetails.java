package edu.gatech.cs2340.myapplication.controllers.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import androidx.navigation.Navigation;
import edu.gatech.cs2340.myapplication.R;
import edu.gatech.cs2340.myapplication.controllers.MainActivity;
import edu.gatech.cs2340.myapplication.models.InventoryEntry;

public class InventoryEntryDetails extends Fragment {

    public InventoryEntryDetails() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_inventory_details, container,
                false);
        TextView itemName = getView().findViewById(R.id.itemName);
        TextView itemDescription = getView().findViewById(R.id.itemDescription);
        TextView itemLocation = getView().findViewById(R.id.itemLocation);
        TextView itemValue = getView().findViewById(R.id.itemValue);
        TextView itemTime = getView().findViewById(R.id.itemTime);
        TextView itemCategory = getView().findViewById(R.id.itemCategory);

        //InventoryEntry item
        itemName.setText(item.name);
        itemDescription.setText(item.description);
        itemLocation.setText(item.location);
        itemValue.setText(item.value);
        itemTime.setText(item.time);
        itemCategory.setText(item.category);

        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mEditButton = view.findViewById(R.id.edit_item_button);
        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                ((MainActivity) getActivity()).updateNavigation();
                Navigation.findNavController(view).navigate(R.id
                        .nextfragname);

            }
        });
    }




}
