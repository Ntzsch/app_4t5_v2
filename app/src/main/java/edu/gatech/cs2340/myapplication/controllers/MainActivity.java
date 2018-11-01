package edu.gatech.cs2340.myapplication.controllers;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import edu.gatech.cs2340.myapplication.R;
import edu.gatech.cs2340.myapplication.controllers.fragments.ViewMapsFragment;
import edu.gatech.cs2340.myapplication.models.TheCloud;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import static edu.gatech.cs2340.myapplication.models.TheCloud.getUserType;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private NavController mNavController;

    private TextView mHeaderUsername;
    private TextView mHeaderUserType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNavController = Navigation.findNavController(findViewById(R.id
                .nav_host_fragment));
        // NavigationUI.setupActionBarWithNavController(this, mNavController);
        mNavigationView = findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(mNavigationView, mNavController);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_white);
        // NavigationUI.setupWithNavController(mNavigationView, mNavController);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        mHeaderUsername = mNavigationView.getHeaderView(0).findViewById(R.id
                .header_username);
        mHeaderUserType = mNavigationView.getHeaderView(0).findViewById(R.id
                .header_user_type);

        // NavigationUI.setupWithNavController(mNavigationView, mNavController);



        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        // menuItem.setChecked(true);
                        switch (menuItem.getItemId()) {
                        case R.id.nav_view_inventory:
                            mNavController.navigate(R.id.nav_view_items);
                            break;
                        case R.id.nav_login:
                            Log.e("menu_navigation", "nav_login");
                            mNavController.navigate(R.id.nav_login);
                            break;
                        case R.id.nav_logout:
                            Log.e("menu_navigation", "nav_logout");
                            TheCloud.signOut();
                            updateNavigation();
                            break;
                        case R.id.nav_view_locations:
                            Log.e("menu_navigation", "nav_view_locations");
                            mNavController.navigate(R.id.nav_view_locations);
                            break;
                        case R.id.nav_view_map:
                            Log.e("menu_navigation", "nav_view_maps");
                            mNavController.navigate(R.id.nav_view_map);
                            break;
                        case R.id.nav_edit_inventory:
                            mNavController.navigate(R.id.nav_edit_inventory);
                            break;
                        case R.id.nav_register:
                            Log.e("menu_navigation", "nav_register");
                            mNavController.navigate(R.id.nav_register);
                        default:
                            break;
                        }
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item
                        // selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Make menu match user type
        updateNavigation();
    }

    public void updateNavigation() {
        mNavigationView.getMenu().clear();
        switch (getUserType()) {
        case GUEST:
            mNavigationView.inflateMenu(R.menu.nav_guest_items);
            break;
        case EMPLOYEE:
            mNavigationView.inflateMenu(R.menu.nav_employee_items);
            break;
        case MANAGER:
            mNavigationView.inflateMenu(R.menu.nav_manager_items);
            break;
        case ADMIN:
            mNavigationView.inflateMenu(R.menu.nav_admin_items);
            break;
        }
        mHeaderUsername.setText(TheCloud.getUsername());
        mHeaderUserType.setText(TheCloud.getUserType().toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
