package com.rohit.swipeimage;

/**
 * Created by Rohit Kumar on 17-02-2018.
 */

public class ImageTable {
    public static final String TABLE="image_table";
    public static final String _id="_id";
    public static final String image="image";
    public static final String CREATE="CREATE TABLE "+TABLE+"("+_id+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+image+" blob)";
}
