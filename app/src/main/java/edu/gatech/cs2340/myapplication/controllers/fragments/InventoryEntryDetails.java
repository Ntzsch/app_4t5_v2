package edu.gatech.cs2340.myapplication.controllers.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import androidx.navigation.Navigation;
import edu.gatech.cs2340.myapplication.R;
import edu.gatech.cs2340.myapplication.models.InventoryEntry;

public class InventoryEntryDetails extends Fragment {

    public InventoryEntryDetails() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inventory_details,
                container, false);



    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView itemCategory = view.findViewById(R.id.itemCategory);
        TextView itemName = view.findViewById(R.id.itemName);
        TextView itemDescription = view.findViewById(R.id.itemDescription);
        TextView itemLocation = view.findViewById(R.id.itemLocation);
        TextView itemValue = view.findViewById(R.id.itemValue);
        TextView itemTime = view.findViewById(R.id.itemTime);

        InventoryEntry item = new InventoryEntry("a", "b",
                "c", "d", "e", "f");
        itemName.setText(item.getSmallDescription());
        itemDescription.setText(item.getFullDescription());
        itemLocation.setText(item.getLocation());
        itemValue.setText(item.getValue());
        itemTime.setText(item.getTimeStamp());
        itemCategory.setText(item.getCategory());


    }

//        mEditButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View view) {
//                ((MainActivity) getActivity()).updateNavigation();
//                Navigation.findNavController(view).navigate(R.id
//                        .nextfragname);
//
//            }
//        });


}
