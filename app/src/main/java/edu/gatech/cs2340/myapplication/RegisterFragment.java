package edu.gatech.cs2340.myapplication;


import android.os.Bundle;
import android.util.Log;
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
import androidx.navigation.Navigation;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private TextInputEditText m_username_text;
    private TextInputEditText m_password_text;
    private TextInputLayout m_password_layout;
    private MaterialButton m_register_button;
    Spinner m_spinner;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View m_view = inflater.inflate(R.layout.fragment_register, container, false);
        m_spinner = m_view.findViewById(R.id.register_spinner);
        ArrayAdapter<CharSequence> adapter;
        if (The_Cloud.get_user_type() == User.Type.ADMIN) {
            adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.admin_user_register_options, android.R.layout.simple_spinner_item);
        } else {
            adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.manager_user_register_options, android.R.layout.simple_spinner_item);
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m_spinner.setAdapter(adapter);
        return m_view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        m_username_text = view.findViewById(R.id.username_edittext);
        m_password_text = view.findViewById(R.id.password_edittext);
        m_password_layout = view.findViewById(R.id.password_textlayout);
        m_register_button = view.findViewById(R.id.register_button);
        m_register_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View view) {
                String username = m_username_text.getText().toString();
                String password = m_password_text.getText().toString();
                String user_type = m_spinner.getSelectedItem().toString();
                m_register_button.setEnabled(false);
                The_Cloud.register_user(username, password, user_type).continueWith(new Continuation<Boolean, Object>() {
                    @Override
                    public Object then(Task<Boolean> task){
                        m_register_button.setEnabled(true);
                        if (task.getResult() == true) {
                            m_password_layout.setError("registered user!");
                        } else {
                            m_password_layout.setError("could not register user");
                        }
                        m_register_button.setEnabled(true);
                        return null;
                    }
                });
                m_username_text.setText(user_type);
                // m_register_button.setEnabled(false);
            }
        });
    }

}
