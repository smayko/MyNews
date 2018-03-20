package com.example.androiddevelopment.mynews.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;
import java.util.Date;

/**
 * Created by androiddevelopment on 20.3.18..
 */

@DatabaseTable(tableName = News.TABLE_NAME)
public class News {

    public static final String TABLE_NAME = "news_table";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "news_name";
    public static final String COLUMN_DESCRIPTION = "news_description";
    public static final String COLUMN_PHOTO= "news_photo";
    public static final String COLUMN_AUTHOR = "news_author";
    public static final String COLUMN_DATE = "news_date";
    public static final String COLUMN_LIKES = "news_likes";
    public static final String COLUMN_DISLIKES = "news_dislikes";
    public static final String COLUMN_COMMENTS = "news_comments";

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @DatabaseField (columnName = COLUMN_ID, generatedId = true)
    private int id;
    @DatabaseField(columnName = COLUMN_NAME)
    private String newsName;
    @DatabaseField(columnName = COLUMN_DESCRIPTION)

    private String newsDescription;
    @DatabaseField(columnName = COLUMN_PHOTO)

    private String newsPhoto;
    @DatabaseField(columnName = COLUMN_AUTHOR)

    private String newsAuthor;
    @DatabaseField(columnName = COLUMN_DATE, dataType = DataType.DATE_STRING, format = DATE_FORMAT)
    private Date newsDAte;
    @DatabaseField(columnName = COLUMN_LIKES)

    private int numOfLikes;
    @DatabaseField(columnName = COLUMN_DISLIKES)

    private int numOfDislikes;
    @ForeignCollectionField
    private Collection<Comments> comments;

   public News(){

   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public String getNewsPhoto() {
        return newsPhoto;
    }

    public void setNewsPhoto(String newsPhoto) {
        this.newsPhoto = newsPhoto;
    }

    public String getNewsAuthor() {
        return newsAuthor;
    }

    public void setNewsAuthor(String newsAuthor) {
        this.newsAuthor = newsAuthor;
    }

    public Date getNewsDAte() {
        return newsDAte;
    }

    public void setNewsDAte(Date newsDAte) {
        this.newsDAte = newsDAte;
    }

    public int getNumOfLikes() {
        return numOfLikes;
    }

    public void setNumOfLikes(int numOfLikes) {
        this.numOfLikes = numOfLikes;
    }

    public int getNumOfDislikes() {
        return numOfDislikes;
    }

    public void setNumOfDislikes(int numOfDislikes) {
        this.numOfDislikes = numOfDislikes;
    }

    public Collection<Comments> getComments() {
        return comments;
    }

    public void setComments(Collection<Comments> comments) {
        this.comments = comments;
    }
}
