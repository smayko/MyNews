package com.example.androiddevelopment.mynews;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androiddevelopment.mynews.db.DatabaseHelper;
import com.example.androiddevelopment.mynews.model.Comments;
import com.example.androiddevelopment.mynews.model.News;
import com.example.androiddevelopment.mynews.utils.Utils;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewContacts;
    private ArrayAdapter<String> adapter;
    private DatabaseHelper mDatabaseHelper;
    private List<News> news;

    private DrawerLayout mDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawer = findViewById(R.id.navigation_drawer);
        implemetnDrawer();
        insertNews();
        getNews();

    }


    private void insertNews(){



        try {
            ArrayList<News> newsList = new ArrayList<>();
            ArrayList <Comments> comments = new ArrayList<>();




            for(int i = 0; i<15; i++){
                News newss = new News();
                newss.setComments(comments);
                newss.setNewsAuthor("autor");
                newss.setNewsDAte(new Date());
                newss.setNewsDescription("neki opis");
                newss.setNumOfDislikes(2 * i);
                newss.setNumOfLikes(i);
                newss.setNewsName("ime vesti");
                newsList.add(newss);
                mDatabaseHelper.getNewsDao().create(newss);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getNews() {
        try {
            news = mDatabaseHelper.getNewsDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<String> info = new ArrayList();
        if (news != null) {
            for (News c : news) {
                info.add(c.getNewsName() + " " + c.getNewsDescription());
            }
            listViewContacts = findViewById(R.id.listViewContacts);
            adapter = new ArrayAdapter<String>(this, R.layout.single_news, info);
            listViewContacts.setAdapter(adapter);
            listViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    int id = news.get(i).getId();
                    Intent intent = new Intent(MainActivity.this, NewsDetailsActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            });
        }
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
                               Intent intent = new Intent(MainActivity.this, MainActivity.class);
                               startActivity(intent);

                                return true;

                            case R.id.action_settings:
                                Intent i = new Intent(MainActivity.this, MyPreferences.class);
                                startActivity(i);
                                return true;

                            case R.id.action_about:
                                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
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
