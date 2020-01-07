package com.ymt.components.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ymt.components.database.Nota;
import com.ymt.components.repository.NotaRepository;

import java.util.List;

public class NotaViewModel extends AndroidViewModel {

    NotaRepository notaRepository;
    LiveData<List<Nota>> list_notas;

    public NotaViewModel(@NonNull Application application) {
        super(application);

        notaRepository = new NotaRepository(application);
        list_notas = notaRepository.listarNotas();
    }

    public LiveData<List<Nota>> listarNotas() {
        return list_notas;
    }

    public void insertar(Nota nota) {
        notaRepository.insert(nota);
    }

    public void delete(Nota nota) {
        notaRepository.delete(nota);
    }

    public void update(Nota nota) {
        notaRepository.update(nota);
    }

    public void deleteAll() {
        notaRepository.deleteAll();
    }
}
