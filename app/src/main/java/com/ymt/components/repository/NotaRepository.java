package com.ymt.components.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ymt.components.database.Nota;
import com.ymt.components.database.NotaDao;
import com.ymt.components.database.NotaDatabase;

import java.util.List;

public class NotaRepository {

    NotaDao notaDao;
    LiveData<List<Nota>> list_notas;

    public NotaRepository(Context context) {

        NotaDatabase database = NotaDatabase.getInstance(context);

        notaDao = database.notaDao();

        list_notas = notaDao.listarNotas();
    }

    public LiveData<List<Nota>> listarNotas() {
        return list_notas;
    }

    public void insert(Nota nota) {
        new InsertAsyntask(notaDao).execute(nota);
    }

    public void update(Nota nota) {
        new UpdateAsyntask(notaDao).execute(nota);
    }

    public void delete(Nota nota) {
        new DeleteAsyntask(notaDao).execute(nota);
    }

    public void deleteAll() {
        new DeleteAllAsyntask(notaDao).execute();
    }

    private class DeleteAllAsyntask extends AsyncTask<Void, Void, Void> {

        NotaDao notaDao;

        public DeleteAllAsyntask(NotaDao notaDao) {
            this.notaDao = notaDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            notaDao.deleteAll();
            return null;
        }
    }


    private class DeleteAsyntask extends AsyncTask<Nota, Void, Void> {

        NotaDao notaDao;

        public DeleteAsyntask(NotaDao notaDao) {
            this.notaDao = notaDao;
        }

        @Override
        protected Void doInBackground(Nota... notas) {

            notaDao.delete(notas[0]);
            return null;
        }
    }

    private class UpdateAsyntask extends AsyncTask<Nota, Void, Void> {

        NotaDao notaDao;

        public UpdateAsyntask(NotaDao notaDao) {
            this.notaDao = notaDao;
        }

        @Override
        protected Void doInBackground(Nota... nota) {
            notaDao.update(nota[0]);
            return null;
        }
    }

    private class InsertAsyntask extends AsyncTask<Nota, Void, Void> {

        NotaDao notaDao;

        public InsertAsyntask(NotaDao notaDao) {
            this.notaDao = notaDao;
        }

        @Override
        protected Void doInBackground(Nota... notas) {

            notaDao.insert(notas[0]);

            return null;
        }
    }
}
