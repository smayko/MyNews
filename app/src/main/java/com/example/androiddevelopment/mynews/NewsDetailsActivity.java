package com.example.androiddevelopment.mynews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androiddevelopment.mynews.db.DatabaseHelper;
import com.example.androiddevelopment.mynews.model.Comments;
import com.example.androiddevelopment.mynews.model.News;
import com.example.androiddevelopment.mynews.utils.Utils;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by androiddevelopment on 20.3.18..
 */

public class NewsDetailsActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private TextView tvname;
    private TextView tvdesc;
    private TextView tvdate;
    private TextView tvauthor;
    private ImageView imageView;
    private Button btnLike;
    private Button btnDisLike;

    private ArrayAdapter<String> adapter;
    private List<Comments> comments;

    ListView listViewComments;

    DatabaseHelper databaseHelper;
    int newsId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details_activity);
        mDrawer = findViewById(R.id.navigation_drawer);
        tvname = (TextView) findViewById(R.id.tvNewsname);
        tvdesc = (TextView) findViewById(R.id.tvNewsDesc);
        tvdate = (TextView) findViewById(R.id.tvNewsDate);
        tvauthor = (TextView) findViewById(R.id.tvNewsAuthor);
        imageView = (ImageView) findViewById(R.id.ivNewsImage);
        listViewComments = (ListView) findViewById(R.id.lcComments);
        btnDisLike = (Button) findViewById(R.id.btnDisLike);
        btnLike = (Button) findViewById(R.id.btnLike);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);


        databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);

        //get info from previous activity
        Intent intent = getIntent();
        newsId = intent.getIntExtra("id", 0);

        implemetnDrawer();

    }

    @Override
    protected void onResume() {
        super.onResume();

        getComments();

        File imageFile;
        try {
            News news = databaseHelper.getNewsDao().queryForId(newsId);
            tvname.setText(news.getNewsName());
            tvdesc.setText(news.getNewsDescription());
            tvauthor.setText(news.getNewsAuthor());
            tvdate.setText(news.getNewsDAte().toString());
            if (news.getNewsPhoto() != null && news.getNewsPhoto() == "") {
                String path = news.getNewsPhoto();
                imageFile = new File(path);
                imageFile.getAbsolutePath();
                Picasso.with(NewsDetailsActivity.this).load(imageFile).fit().centerCrop().into(imageView);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.add_comment:
                try {

                    Intent intent = new Intent(NewsDetailsActivity.this, AddCommentActivity.class);
                    intent.putExtra("id", newsId);
                    startActivity(intent);

                    if (Utils.isShowToast(NewsDetailsActivity.this)) {
                        Toast.makeText(NewsDetailsActivity.this, "Coment Added", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void implemetnDrawer() {
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
                                alertDialog.setTitle("App Info");
                                alertDialog.setMessage(getResources().getString(R.string.created_by));
                                alertDialog.show();
                                return true;
                        }
                        return true;
                    }
                });
    }

    private void getComments() {

        try {
            comments = databaseHelper.getCommentsDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<String> info = new ArrayList();
        if (comments != null) {
            for (Comments c : comments) {
                info.add(c.getComment_name() + " " + c.getComment_desc());
            }
            listViewComments = findViewById(R.id.lcComments);
            adapter = new ArrayAdapter<String>(this, R.layout.single_news, info);
            listViewComments.setAdapter(adapter);

        }
    }
}
