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
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout m_drawer_layout;
    private NavigationView m_navigation_view;
    private User m_user;
    private NavController m_nav_controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_user = new User();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        m_nav_controller = Navigation.findNavController(findViewById(R.id.nav_host_fragment));
        // NavigationUI.setupActionBarWithNavController(this, m_nav_controller);
        m_navigation_view = findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(m_navigation_view, m_nav_controller);

        // ActionBar actionbar = getSupportActionBar();
        // actionbar.setDisplayHomeAsUpEnabled(true);
        // actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_white);
        // NavigationUI.setupWithNavController(m_navigation_view, m_nav_controller);

        m_drawer_layout = findViewById(R.id.drawer_layout);
        // NavigationUI.setupWithNavController(m_navigation_view, m_nav_controller);
        /**
        m_navigation_view.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);

                        switch (menuItem.getItemId()) {
                            case R.id.nav_login:
                                m_nav_controller.navigate(R.id.loginFragment);
                                break;
                            default:
                                m_nav_controller.navigate(R.id.mainFragment);
                                break;
                        }
                        // close drawer when item is tapped
                        m_drawer_layout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
         */
    }

    @Override
    protected void onStart(){
        super.onStart();

        /*
        // Make menu match user type
        m_navigation_view.getMenu().clear();
        switch (m_user.getType()) {
            case GUEST:
                //
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
        */

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
