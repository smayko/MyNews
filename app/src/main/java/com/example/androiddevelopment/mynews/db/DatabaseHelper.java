package com.example.androiddevelopment.mynews.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.androiddevelopment.mynews.model.Comments;
import com.example.androiddevelopment.mynews.model.News;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by androiddevelopment on 20.3.18..
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper{

    private static final String DATABASE_NAME = "my_news.db";

    private static final int DATABASE_VERSION = 1;

    private Dao<News, Integer> newsDao = null;
    private Dao<Comments, Integer> commentsDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, News.class);
            TableUtils.createTable(connectionSource, Comments.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ConnectionSource connectionSource = null;
    @Override
    public ConnectionSource getConnectionSource() {
        if (connectionSource == null) {
            connectionSource = super.getConnectionSource();
        }
        return connectionSource;
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {
            TableUtils.dropTable(connectionSource, News.class, true);
            TableUtils.dropTable(connectionSource, Comments.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    //create Dao Object
    public Dao<News, Integer> getNewsDao() throws SQLException {
        if (newsDao == null) {
            newsDao = getDao(News.class);
        }
        return newsDao;
    }

    public Dao<Comments, Integer> getCommentsDao() throws SQLException {
        if (commentsDao == null) {
            commentsDao = getDao(Comments.class);
        }
        return commentsDao;
    }

    //close resources when done with db!
    @Override
    public void close() {

        newsDao = null;
        super.close();
    }
}
