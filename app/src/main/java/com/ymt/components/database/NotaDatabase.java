package com.ymt.components.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                    .addCallback(room)
                    .fallbackToDestructiveMigration()
                    // .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback room = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateBDAsyntask(instance).execute();

        }
    };

    private static class  PopulateBDAsyntask extends AsyncTask<Void, Void, Void> {

        NotaDatabase instance;

        public PopulateBDAsyntask(NotaDatabase instance) {

            this.instance = instance;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            instance.notaDao().insert(new Nota("Nota01", "Practicar Android"));
            instance.notaDao().insert(new Nota("Nota02", "Practicar IOS"));
            instance.notaDao().insert(new Nota("Nota03", "Practicar Backend"));

            return null;
        }
    }
}