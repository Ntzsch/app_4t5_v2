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
public class LoginFragment extends Fragment {

    private TextInputEditText mUsernameText;
    private TextInputEditText mPasswordText;
    private TextInputLayout mPasswordLayout;
    private MaterialButton mLoginButton;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mUsernameText = view.findViewById(R.id.username_edittext);
        mPasswordText = view.findViewById(R.id.password_edittext);
        mPasswordLayout = view.findViewById(R.id.password_textlayout);
        mLoginButton = view.findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String username = mUsernameText.getText().toString();
                String password = mPasswordText.getText().toString();
                TheCloud.signIn(username, password).continueWith(new
                                    Continuation<Boolean, Object>() {
                    @Override
                    public Object then(Task<Boolean> task) {
                        Log.e("LoginFragment", task.getResult().toString());
                        mLoginButton.setEnabled(true);
                        if (task.getResult()) {
                            ((MainActivity) getActivity()).updateNavigation();
                            Navigation.findNavController(view).navigate(R.id
                                    .mainFragment);
                            mUsernameText.setText("LOGGED IN");
                        } else {
                            mPasswordLayout.setError("incorrect "
                                    + "username/password");
                        }
                        return null;
                    }
                });
                mPasswordText.setText("");
                mLoginButton.setEnabled(false);
            }
        });
    }

}
