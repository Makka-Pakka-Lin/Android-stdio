package com.example.a202406kaoshi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseManager extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "books";
    //public static final String AMOUNT = "sqlite_sequence";
    //public static final String SEQ_FIELD = "seq";

    public static final String ID_FIELD = "_id";
    public static final String ISBN_FIELD = "isbn";
    public static final String TITLE_FIELD = "title";
    public static final String AUTHOR_FIELD = "author";
    public static final String PUBLISH_FIELD = "publish";

    public DataBaseManager(@Nullable Context context) {
        super(context, "books_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable =
                "CREATE TABLE "
                + TABLE_NAME
                + " ("
                + ID_FIELD + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ISBN_FIELD + " INTEGER, "
                + TITLE_FIELD + " TEXT, "
                + AUTHOR_FIELD + " TEXT, "
                + PUBLISH_FIELD
                + " );"
                ;
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long addBook(String isbn, String title, String author,String publish) {
        if (isbn == null || isbn.isEmpty() || title == null || title.isEmpty() || author == null || author.isEmpty()|| publish == null || publish.isEmpty() ) {
            throw new IllegalArgumentException("请填写全部信息(\\#-_-)\\┯━┯");//可以在文字前加*提醒
        }
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ISBN_FIELD, isbn);
        values.put(TITLE_FIELD, title);
        values.put(AUTHOR_FIELD, author);
        values.put(PUBLISH_FIELD,publish);
        return db.insert(TABLE_NAME, null, values);
    }

    public Cursor getBookByISBN(String isbn) {
        if (isbn == null || isbn.isEmpty()) {
            throw new IllegalArgumentException("ISBN不能为空");
        }
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABLE_NAME, null, ISBN_FIELD + "=?", new String[]{isbn}, null, null, null);
    }

    public int updateBook(long id, String isbn, String title, String author,String publish) {
        if (id <= 0 || isbn == null || isbn.isEmpty() || title == null || title.isEmpty() || author == null || author.isEmpty()) {
            throw new IllegalArgumentException("请正确输入所有数据(╯°Д°)╯︵┻━┻");
        }
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ISBN_FIELD, isbn);
        values.put(TITLE_FIELD, title);
        values.put(AUTHOR_FIELD, author);
        values.put(PUBLISH_FIELD,publish);
        return db.update(TABLE_NAME, values, ID_FIELD + "=?", new String[]{String.valueOf(id)});
    }

    public int deleteBook(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("这个鬼玩意删除了");
        }
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, ID_FIELD + "=?", new String[]{String.valueOf(id)});
    }

//    public void clearDatabase() {
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(SEQ_FIELD, 1);
//        onUpgrade();
//        db.delete(TABLE_NAME, null, null);
//        db.update(AMOUNT, values, null, null);
//    }
}