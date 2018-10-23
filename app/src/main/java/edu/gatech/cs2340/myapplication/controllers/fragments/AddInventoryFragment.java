package edu.gatech.cs2340.myapplication.controllers.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import edu.gatech.cs2340.myapplication.R;
import edu.gatech.cs2340.myapplication.models.InventoryEntry;
import edu.gatech.cs2340.myapplication.models.TheCloud;
import edu.gatech.cs2340.myapplication.controllers.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddInventoryFragment extends Fragment {

    private TextInputEditText mtimeStamp;
    private TextInputEditText mlocation;
    private TextInputEditText mshortDescription;
    private TextInputEditText mlongDescription;
    private TextInputEditText mvalue; //in dollars
    private TextInputEditText mcategory;
    private MaterialButton mCancelButton;
    private MaterialButton mConfirmButton;

    public AddInventoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_inventory, container, false);
    } // CHECK TO SEE IF CONSISTENT ACROSS ALL FRAGMENTS

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        mlocation = view.findViewById(R.id.edit_location);
        mshortDescription = view.findViewById(R.id.edit_short_description);
        mlongDescription = view.findViewById(R.id.edit_long_description);
        mvalue = view.findViewById(R.id.edit_price);
        mcategory = view.findViewById(R.id.edit_category);
        mtimeStamp = view.findViewById(R.id.edit_time);
        mCancelButton = view.findViewById(R.id.cancel_button);
        mConfirmButton = view.findViewById(R.id.confirm_button);

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Navigation.findNavController(view).navigate(R.id.nav_edit_inventory);
                mCancelButton.setEnabled(false);
            }
        });

        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String location = mlocation.getText().toString();
                String shortDescription = mshortDescription.getText().toString();
                String longDescription = mlongDescription.getText().toString();
                String value = mvalue.getText().toString();
                String category = mcategory.getText().toString();
                String timeStamp = mtimeStamp.getText().toString();

                InventoryEntry entry = new InventoryEntry(timeStamp, location, shortDescription,
                        longDescription, value, category);
                /* this "bundle" stuff is for front end display only. After
                 * hitting the "confirm" button, the item's detail page
                 * will be brought up.*/
                Bundle bundle = new Bundle();
                bundle.putString("time", timeStamp);
                bundle.putString("location", location);
                bundle.putString("smallDescript", shortDescription);
                bundle.putString("longDescript", longDescription);
                bundle.putString("value", value);
                bundle.putString("category", category);

                TheCloud.addInventoryEntry(entry).continueWith(new
                                                                         Continuation<Boolean, Object>() {
                                                                             @Override
                                                                             public Object then(Task<Boolean> task) {
                     Log.e("AddInventoryFragment", task.getResult().toString());
                     mConfirmButton.setEnabled(true);
                     if (task.getResult()) {
                         ((MainActivity) getActivity()).updateNavigation();




                     }
                     return null;
                 }
             });
                Navigation.findNavController(view).navigate(R.id
                        .nav_view_inventory_details, bundle);

                mConfirmButton.setEnabled(false);
            }
        });
    }

}
