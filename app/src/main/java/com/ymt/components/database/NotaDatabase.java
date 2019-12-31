package com.ymt.components.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * @author michcode
 */
@Database(entities = {Nota.class}, version = 1, exportSchema = false)
public abstract class NotaDatabase extends RoomDatabase {

    private static NotaDatabase instance;
    public abstract NotaDao notaDao();

    public static synchronized NotaDatabase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NotaDatabase.class, "bd_notas")
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }
}
