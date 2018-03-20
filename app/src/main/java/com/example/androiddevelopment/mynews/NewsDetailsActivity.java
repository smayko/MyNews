package com.example.androiddevelopment.mynews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by androiddevelopment on 20.3.18..
 */

public class NewsDetailsActivity extends AppCompatActivity{

    private DrawerLayout mDrawer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details_activity);
        mDrawer = findViewById(R.id.navigation_drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_details_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.add_comment :
                //todo add comment
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private  void implemetnDrawer(){
        mDrawer = findViewById(R.id.navigation_drawer);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawer.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        switch (menuItem.getItemId()) {
                            case R.id.all_news:
                                Intent intent = new Intent(NewsDetailsActivity.this, MainActivity.class);
                                startActivity(intent);

                                return true;

                            case R.id.action_settings:
                                Intent i = new Intent(NewsDetailsActivity.this, MyPreferences.class);
                                startActivity(i);
                                return true;

                            case R.id.action_about:
                                AlertDialog alertDialog = new AlertDialog.Builder(NewsDetailsActivity.this).create();
                                alertDialog.setTitle( "App Info");
                                alertDialog.setMessage(getResources().getString(R.string.created_by));
                                alertDialog.show();
                                return true;
                        }
                        return true;
                    }
                });
    }
}
