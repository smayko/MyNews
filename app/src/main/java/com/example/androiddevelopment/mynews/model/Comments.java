package com.example.androiddevelopment.mynews.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import static com.example.androiddevelopment.mynews.model.News.DATE_FORMAT;

/**
 * Created by androiddevelopment on 20.3.18..
 */

@DatabaseTable (tableName = Comments.TABLE_NAME)
public class Comments {

    public static final String TABLE_NAME = "comments";

    public static final String COLUMN_ID = "comment_id";
    public static final String COLUMN_COMMENT_NAME = "comment_name";
    public static final String COLUMN_DESCRIPTION = " comment_desc";
    public static final String COLUMN_AUTHOR = "comment_author";
    public static final String COLUMN_DATE = "comment_date";

    @DatabaseField(columnName = COLUMN_ID, generatedId = true)
    private int id;
    @DatabaseField(columnName = COLUMN_COMMENT_NAME)
    private String comment_name;
    @DatabaseField (columnName = COLUMN_DESCRIPTION)
    private String comment_desc;
    @DatabaseField(columnName = COLUMN_AUTHOR)
    private String comment_author;
    @DatabaseField(columnName = COLUMN_DATE, dataType = DataType.DATE_STRING, format = DATE_FORMAT)
    private Date comment_date;

    public Comments (){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment_name() {
        return comment_name;
    }

    public void setComment_name(String comment_name) {
        this.comment_name = comment_name;
    }

    public String getComment_desc() {
        return comment_desc;
    }

    public void setComment_desc(String comment_desc) {
        this.comment_desc = comment_desc;
    }

    public String getComment_author() {
        return comment_author;
    }

    public void setComment_author(String comment_author) {
        this.comment_author = comment_author;
    }

    public Date getComment_date() {
        return comment_date;
    }

    public void setComment_date(Date comment_date) {
        this.comment_date = comment_date;
    }
}
