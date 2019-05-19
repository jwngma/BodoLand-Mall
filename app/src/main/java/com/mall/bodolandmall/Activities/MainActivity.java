package com.mall.bodolandmall.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mall.bodolandmall.Fragments.HomeFragment;
import com.mall.bodolandmall.Fragments.MyCartFragment;
import com.mall.bodolandmall.Fragments.MyOrderFragment;
import com.mall.bodolandmall.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FrameLayout frameLayout;
    private NavigationView navigationView;

    private static final int Home_Fragment = 0;
    private static final int Cart_Fragment = 1;
    private static final int Order_Fragment = 2;
    private static int currentFragment=-1;
    private TextView app_bar_logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        app_bar_logo = findViewById(R.id.app_bar_logo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar().setTitle("Bodoland Mall");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        frameLayout = findViewById(R.id.main_framelayout);
        setFragment(new HomeFragment(), Home_Fragment);
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
        if (currentFragment == Home_Fragment) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_search:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_notification:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_cart:
                GoToFragment("My Cart", new MyCartFragment(),Cart_Fragment);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void GoToFragment(String title, Fragment fragment, int fragmentNo) {
        invalidateOptionsMenu();
        app_bar_logo.setVisibility(View.INVISIBLE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        setFragment(fragment, fragmentNo);
        if (fragmentNo==Cart_Fragment){
            navigationView.getMenu().getItem(3).setChecked(true);
        }

    }

    private void myOrder(){
        invalidateOptionsMenu();
        app_bar_logo.setVisibility(View.INVISIBLE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("My Order");
        setFragment(new MyCartFragment(), Cart_Fragment);
        navigationView.getMenu().getItem(1).setChecked(true);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_my_mall:
                invalidateOptionsMenu();
                app_bar_logo.setVisibility(View.VISIBLE);
                setFragment(new HomeFragment(), Home_Fragment);
                break;
            case R.id.nav_my_order:
                GoToFragment("My Order", new MyOrderFragment(),Order_Fragment);
                break;
            case R.id.nav_reward:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_cart:
                GoToFragment("My Cart", new MyCartFragment(),Cart_Fragment);
                break;
            case R.id.nav_wishes:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_account:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_logout:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_share:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_rate:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(Fragment fragment, int fragmentNo) {
        if (fragmentNo!=currentFragment){
            currentFragment = fragmentNo;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            transaction.replace(frameLayout.getId(), fragment);
            transaction.commit();
        }

    }
}
