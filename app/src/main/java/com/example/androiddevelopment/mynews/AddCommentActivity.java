package com.example.androiddevelopment.mynews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androiddevelopment.mynews.db.DatabaseHelper;
import com.example.androiddevelopment.mynews.model.Comments;
import com.example.androiddevelopment.mynews.model.News;
import com.example.androiddevelopment.mynews.utils.Utils;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;

/**
 * Created by androiddevelopment on 20.3.18..
 */

public class AddCommentActivity extends AppCompatActivity {


    private EditText etComent;
    private Button btnAdd;
    private Button btnCancel;
    private String commentString;

    DatabaseHelper databaseHelper;

    private int newsId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_comment_activity);
        etComent = (EditText) findViewById(R.id.etAddComment);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        //get info from previous activity
        Intent intent = getIntent();
        newsId = intent.getIntExtra("id", 0);

        databaseHelper  = OpenHelperManager.getHelper(this, DatabaseHelper.class);

        //btnAdd commentString to selected news
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    News news = null;
                    news = databaseHelper.getNewsDao().queryForId(newsId);
                    commentString = etComent.getText().toString();
                    Comments comments = new Comments();
                    comments.setComment_name(commentString);
                    comments.setNews(news);
                    databaseHelper.getCommentsDao().create(comments);
                    if(Utils.isShowToast(AddCommentActivity.this)){
                        Toast.makeText(AddCommentActivity.this, "Comment Added", Toast.LENGTH_SHORT).show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
