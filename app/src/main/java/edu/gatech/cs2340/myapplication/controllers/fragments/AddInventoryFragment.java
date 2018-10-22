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

        // Do some parsing for the time stamp
        //mtimeStamp

        mlocation = view.findViewById(R.id.location);
        mshortDescription = view.findViewById(R.id.description_short);
        mlongDescription = view.findViewById(R.id.description_long);
        mvalue = view.findViewById(R.id.value);
        mcategory = view.findViewById(R.id.category);
        mtimeStamp = view.findViewById(R.id.timestamp);

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {


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

                mConfirmButton.setEnabled(false);
            }
        });
    }

}
