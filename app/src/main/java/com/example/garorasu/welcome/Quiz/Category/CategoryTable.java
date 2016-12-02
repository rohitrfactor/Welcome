package com.example.garorasu.welcome.Quiz.Category;

import android.provider.BaseColumns;

/**
 * Created by garorasu on 2/12/16.
 */

public interface CategoryTable {

    String NAME = "category";

    String COLUMN_ID = BaseColumns._ID;
    String COLUMN_NAME = "name";
    String COLUMN_THEME = "theme";
    String COLUMN_SCORES = "scores";
    String COLUMN_SOLVED = "solved";

    String[] PROJECTION = new String[]{COLUMN_ID, COLUMN_NAME,
            COLUMN_THEME, COLUMN_SOLVED, COLUMN_SCORES};

    String CREATE = "CREATE TABLE " + NAME + " ("
            + COLUMN_ID + " TEXT PRIMARY KEY, "
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_THEME + " TEXT NOT NULL, "
            + COLUMN_SOLVED + " TEXT NOT NULL, "
            + COLUMN_SCORES + " TEXT);";
}
