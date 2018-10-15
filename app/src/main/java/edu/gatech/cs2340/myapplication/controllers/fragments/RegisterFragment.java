package edu.gatech.cs2340.myapplication.controllers.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.fragment.app.Fragment;
import edu.gatech.cs2340.myapplication.R;
import edu.gatech.cs2340.myapplication.models.TheCloud;
import edu.gatech.cs2340.myapplication.models.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private TextInputEditText mUsernameText;
    private TextInputEditText mPasswordText;
    private TextInputLayout mPasswordLayout;
    private MaterialButton mRegisterButton;
    Spinner mSpinner;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_register, container,
                false);
        mSpinner = mView.findViewById(R.id.register_spinner);
        ArrayAdapter<CharSequence> adapter;
        if (TheCloud.getUserType() == User.Type.ADMIN) {
            adapter = ArrayAdapter.createFromResource(this.getActivity(), R
                    .array.admin_user_register_options, android.R.layout
                    .simple_spinner_item);
        } else {
            adapter = ArrayAdapter.createFromResource(this.getActivity(), R
                    .array.manager_user_register_options, android.R.layout
                    .simple_spinner_item);
        }
        adapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        return mView;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mUsernameText = view.findViewById(R.id.username_edittext);
        mPasswordText = view.findViewById(R.id.password_edittext);
        mPasswordLayout = view.findViewById(R.id.password_textlayout);
        mRegisterButton = view.findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String username = mUsernameText.getText().toString();
                String password = mPasswordText.getText().toString();
                String userType = mSpinner.getSelectedItem().toString();
                mRegisterButton.setEnabled(false);
                TheCloud.registerUser(username, password, userType)
                        .continueWith(new Continuation<Boolean, Object>() {
                            @Override
                                public Object then(Task<Boolean> task) {
                                mRegisterButton.setEnabled(true);
                                if (task.getResult()) {
                                    mPasswordLayout.setError("registered "
                                            + "user!");
                                } else {
                                    mPasswordLayout.setError("could not"
                                            + "register user");
                                }
                                mRegisterButton.setEnabled(true);
                                return null;
                            }
                        });
                mUsernameText.setText(userType);
                // mRegisterButton.setEnabled(false);
            }
        });
    }

}
