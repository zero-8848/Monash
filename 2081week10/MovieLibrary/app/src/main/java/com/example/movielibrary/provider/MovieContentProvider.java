package com.example.movielibrary.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MovieContentProvider extends ContentProvider {
    MovieDatabase db;
    public static final String CONTENT_AUTHORITY = "fit2081.app.ZIRUI";
    public static final Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static final int MULTIPLE_ROWS = 1;
    private static final int SINGLE_ROW = 2;
    private static final int NAME = 3;


    private static final UriMatcher sUriMatcher = createUriMatcher();

    private static UriMatcher createUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = CONTENT_AUTHORITY;

        //sUriMatcher will return code 1 if uri like authority/tasks
        //MUTI
        uriMatcher.addURI(authority,MovieDb.TABLE_NAME, MULTIPLE_ROWS);

        //sUriMatcher will return code 2 if uri like e.g. authority/tasks/7 (where 7 is id of row in tasks table)
        //SIG
        uriMatcher.addURI(authority, MovieDb.TABLE_NAME + "/#", SINGLE_ROW);

        //sUriMatcher will return code 1 if uri like authority/users

        uriMatcher.addURI(authority, MovieDb.TABLE_NAME+"/*", NAME);



        return uriMatcher;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int deletionCount;

        deletionCount = db
                .getOpenHelper()
                .getWritableDatabase()
                .delete(MovieDb.TABLE_NAME, selection, selectionArgs);

        return deletionCount;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        long rowId = db
                .getOpenHelper()
                .getWritableDatabase()
                .insert(MovieDb.TABLE_NAME, 0, contentValues);

        return ContentUris.withAppendedId(CONTENT_URI, rowId);
    }
    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        db = MovieDatabase.getDatabase(getContext());
        return true;

    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(MovieDb.TABLE_NAME);
        String query = builder.buildQuery(projection, selection, null, null, sortOrder, null);

        final Cursor cursor = db
                .getOpenHelper()
                .getReadableDatabase()
                .query(query, selectionArgs);

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int updateCount;
        updateCount = db
                .getOpenHelper()
                .getWritableDatabase()
                .update(MovieDb.TABLE_NAME, 0, values, selection, selectionArgs);

        return updateCount;
    }
}