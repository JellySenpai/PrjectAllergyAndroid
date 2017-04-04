package com.uat.foodmeister;

import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.uat.foodmeister.Permissions.Permissions;
import com.uat.foodmeister.Registration.SignInActivity;
import com.uat.foodmeister.User.UserAccount;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager manager;
    protected LocationManager locationManager;
    protected UserAccount userAccount;
    TextView usersNameLabel;
    private SyncInfo syncInfo;
    private final String TAG ="MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nv_activity_main);
        syncInfo = new SyncInfo();
        Thread syncInfoThread = new Thread(syncInfo);


        userAccount = (UserAccount) getIntent().getSerializableExtra("UserAccount");



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         manager = getSupportFragmentManager();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        usersNameLabel = (TextView) header.findViewById(R.id.lblUsersName);
        usersNameLabel.setText("Raymond Harding");
        //usersNameLabel.setText(userAccount.getName());



        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Permissions permissions = Permissions.getInstance(this, this, locationManager);

        if (Permissions.isGooglePlayServicesAvailable()) {

            if (Permissions.isLocationServicePermissionsDenied())
            {
                Log.i("Permissions", "Location Services Denied");
                Permissions.requestLocationPermissions();
            }
        }
        this.changeFragment(R.id.nav_map, null);
        syncInfoThread.start();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        this.changeFragment(id, null);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void changeFragment(int id, String name){
        Fragment fragment = null;
        if (id == R.id.nav_map)
        {
            fragment = new MapFragment();


        }
        else if (id == R.id.nav_profile)
        {
            fragment = new ProfileFragment();
        }
        else if (id == R.id.nav_slideshow)
        {
            //BlankFragment blankFragment = BlankFragment.newInstance(5);
            //manager.beginTransaction().replace(R.id.relativelayout_for_fragment, blankFragment,blankFragment.getTag()).commit();
        }
        else if (id == R.id.nav_logout)
        {
            FMSharedPrefs.deleteEverything(this, AppConfig.ACCOUNT_HOLDER_EMAIL);
            FMSharedPrefs.deleteEverything(this, AppConfig.ACCOUNT_HOLDER_NAME);
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
            syncInfo.stopThread();

        }
        else if (id == R.id.nav_contact)
        {
            fragment = new ContactFragment();
        }
        else if (id == R.id.nav_license)
        {
            fragment = new LicenseFragment();
        }
        //
        else if(id == AppConfig.LOAD_ADD_PROFILE_FRAGMENT){
            fragment = new AddProfileFragment();
            Bundle bundle = new Bundle();
            bundle.putString("profileName", name);
            fragment.setArguments(bundle);
        }
        if(id != R.id.nav_logout)
            manager.beginTransaction().replace(R.id.relativelayout_for_fragment, fragment, fragment.getTag()).commit();
    }
    public void onFragmentInteraction(String data) {
        Toast.makeText(this,data, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        syncInfo.stopThread();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Thread syncInfoThread = new Thread(syncInfo);
        syncInfoThread.start();
    }


}