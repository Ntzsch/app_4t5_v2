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
import edu.gatech.cs2340.myapplication.models.The_Cloud;
import edu.gatech.cs2340.myapplication.controllers.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private TextInputEditText m_username_text;
    private TextInputEditText m_password_text;
    private TextInputLayout m_password_layout;
    private MaterialButton m_login_button;

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
        m_username_text = view.findViewById(R.id.username_edittext);
        m_password_text = view.findViewById(R.id.password_edittext);
        m_password_layout = view.findViewById(R.id.password_textlayout);
        m_login_button = view.findViewById(R.id.login_button);
        m_login_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View view) {
                String username = m_username_text.getText().toString();
                String password = m_password_text.getText().toString();
                The_Cloud.sign_in(username, password).continueWith(new Continuation<Boolean, Object>() {
                    @Override
                    public Object then(Task<Boolean> task){
                        Log.e("LoginFragment", task.getResult().toString());
                        m_login_button.setEnabled(true);
                        if (task.getResult() == true) {
                            ((MainActivity)getActivity()).update_navigation();
                            Navigation.findNavController(view).navigate(R.id.mainFragment);
                            m_username_text.setText("LOGGED IN");
                        } else {
                            m_password_layout.setError("incorrect username/password");
                        }
                        return null;
                    }
                });
                m_password_text.setText("");
                m_login_button.setEnabled(false);
            }
        });
    }

}
