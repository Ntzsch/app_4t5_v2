package edu.gatech.cs2340.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout m_drawer_layout;
    private NavigationView m_navigation_view;
    private NavController m_nav_controller;

    private TextView m_header_username;
    private TextView m_header_user_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        m_nav_controller = Navigation.findNavController(findViewById(R.id.nav_host_fragment));
        // NavigationUI.setupActionBarWithNavController(this, m_nav_controller);
        m_navigation_view = findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(m_navigation_view, m_nav_controller);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_white);
        // NavigationUI.setupWithNavController(m_navigation_view, m_nav_controller);

        m_drawer_layout = findViewById(R.id.drawer_layout);

        m_header_username = m_navigation_view.getHeaderView(0).findViewById(R.id.header_username);
        m_header_user_type = m_navigation_view.getHeaderView(0).findViewById(R.id.header_user_type);

        // NavigationUI.setupWithNavController(m_navigation_view, m_nav_controller);



        m_navigation_view.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        // menuItem.setChecked(true);
                        switch (menuItem.getItemId()) {
                            case R.id.nav_login:
                                Log.e("menu_navigation", "nav_login");
                                m_nav_controller.navigate(R.id.nav_login);
                                break;
                            case R.id.nav_logout:
                                Log.e("menu_navigation", "nav_logout");
                                The_Cloud.sign_out();
                                update_navigation();
                                break;
                            case R.id.nav_view_locations:
                                Log.e("menu_navigation", "nav_view_locations");
                                m_nav_controller.navigate(R.id.nav_view_locations);
                                break;
                            case R.id.nav_register:
                                Log.e("menu_navigation", "nav_register");
                                m_nav_controller.navigate(R.id.nav_register);
                            default:
                                break;
                        }
                        m_drawer_layout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
    }

    @Override
    protected void onStart(){
        super.onStart();
        // Make menu match user type
       update_navigation();
    }

    public void update_navigation() {
        m_navigation_view.getMenu().clear();
        switch (The_Cloud.get_user_type()) {
            case GUEST:
                m_navigation_view.inflateMenu(R.menu.nav_guest_items);
                break;
            case EMPLOYEE:
                m_navigation_view.inflateMenu(R.menu.nav_employee_items);
                break;
            case MANAGER:
                m_navigation_view.inflateMenu(R.menu.nav_manager_items);
                break;
            case ADMIN:
                m_navigation_view.inflateMenu(R.menu.nav_admin_items);
                break;
        }
        m_header_username.setText(The_Cloud.get_username());
        m_header_user_type.setText(The_Cloud.get_user_type().toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                m_drawer_layout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
