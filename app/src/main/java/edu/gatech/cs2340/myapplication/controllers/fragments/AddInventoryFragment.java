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

    private TextInputEditText mTimeStamp;
    private TextInputEditText mLocation;
    private TextInputEditText mShortDescription;
    private TextInputEditText mLongDescription;
    private TextInputEditText mValue; //in dollars
    private TextInputEditText mCategory;
    private MaterialButton mCancelButton;
    private MaterialButton mConfirmButton;

    /**
     * Required empty public constructor for AddInventoryFragment.
     */
    public AddInventoryFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_inventory, container, false);
    } // CHECK TO SEE IF CONSISTENT ACROSS ALL FRAGMENTS

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        mLocation = view.findViewById(R.id.edit_location);
        mShortDescription = view.findViewById(R.id.edit_short_description);
        mLongDescription = view.findViewById(R.id.edit_long_description);
        mValue = view.findViewById(R.id.edit_price);
        mCategory = view.findViewById(R.id.edit_category);
        mTimeStamp = view.findViewById(R.id.edit_time);
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
                // TODO: Add handling for if the user doesn't fill all text boxes
                String location = mLocation.getText().toString();
                String shortDescription = mShortDescription.getText().toString();
                String longDescription = mLongDescription.getText().toString();
                String value = mValue.getText().toString();
                String category = mCategory.getText().toString();
                String timeStamp = mTimeStamp.getText().toString();

                InventoryEntry entry = new InventoryEntry(timeStamp, location, shortDescription,
                        longDescription, value, category);

                /* this "bundle" stuff is for front end display only. After
                 * hitting the "confirm" button, the item's detail page
                 * will be brought up.*/
                Bundle bundle = new Bundle();
                bundle.putString("time", timeStamp);
                bundle.putString("location", location);
                bundle.putString("smallDescription", shortDescription);
                bundle.putString("longDescription", longDescription);
                bundle.putString("value", value);
                bundle.putString("category", category);

                TheCloud.addInventoryEntry(entry).continueWith(new Continuation<Boolean, Object>() {
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
