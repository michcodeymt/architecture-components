package com.ymt.components;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.ymt.components.database.Nota;
import com.ymt.components.viewmodel.NotaViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    NotaViewModel notaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notaViewModel = ViewModelProviders.of(this).get(NotaViewModel.class);

        notaViewModel.listarNotas().observe(this, new Observer<List<Nota>>() {
            @Override
            public void onChanged(List<Nota> notas) {


            }
        });
    }
}
