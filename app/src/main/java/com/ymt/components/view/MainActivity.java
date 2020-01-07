package com.ymt.components.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ymt.components.R;
import com.ymt.components.adapter.NotaAdapter;
import com.ymt.components.database.Nota;
import com.ymt.components.viewmodel.NotaViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    NotaViewModel notaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_agregar_notas = findViewById(R.id.btn_agregar_notas);
        Button btn_eliminar_todo = findViewById(R.id.btn_eliminar_todos);

        RecyclerView recyclerView = findViewById(R.id.recycler_notas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final NotaAdapter notaAdapter = new NotaAdapter();
        recyclerView.setAdapter(notaAdapter);

        notaViewModel = ViewModelProviders.of(this).get(NotaViewModel.class);

        notaViewModel.listarNotas().observe(this, new Observer<List<Nota>>() {
            @Override
            public void onChanged(List<Nota> notas) {

                notaAdapter.setNotas(notas);
            }
        });

        btn_eliminar_todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createLoginDialogo().show();
            }
        });

        btn_agregar_notas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AgregarNotaActivity.class);
                startActivityForResult(intent, 1000);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                notaViewModel.delete(notaAdapter.obtenerNota(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        notaAdapter.setOnItemClickListener(new NotaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Nota nota) {
                Intent intent = new Intent(MainActivity.this, AgregarNotaActivity.class);
                intent.putExtra("id", nota.getId());
                intent.putExtra("titulo", nota.getTitulo());
                intent.putExtra("descripcion", nota.getDescripcion());
                startActivityForResult(intent, 2000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK) {
            String titulo = data.getStringExtra("titulo");
            String descripcion = data.getStringExtra("descripcion");

            Nota nota = new Nota(titulo, descripcion);

            notaViewModel.insertar(nota);

            Toast.makeText(this, "Agregado correctamente", Toast.LENGTH_SHORT).show();
        }

        if (requestCode == 2000 && resultCode == RESULT_OK) {

            int id = data.getIntExtra("id", -1);

            if (id == -1) {
                Toast.makeText(this, "No se pudo actualizar", Toast.LENGTH_SHORT).show();
                return;
            }

            String titulo = data.getStringExtra("titulo");
            String descripcion = data.getStringExtra("descripcion");

            Nota nota = new Nota(titulo, descripcion);
            nota.setId(id);

            notaViewModel.update(nota);
            Toast.makeText(this, "Actualizado", Toast.LENGTH_SHORT).show();
        }
    }

    public AlertDialog createLoginDialogo() {

        final AlertDialog alertDialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_eliminar_todos, null);

        builder.setView(v);

        Button btn_aperturar_no =  v.findViewById(R.id.btn_aperturar_no);
        Button btn_aperturar_si =  v.findViewById(R.id.btn_aperturar_si);

        alertDialog = builder.create();


        btn_aperturar_si.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        notaViewModel.deleteAll();
                        alertDialog.dismiss();

                    }
                }
        );

        btn_aperturar_no.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        alertDialog.dismiss();

                    }
                }

        );

        return alertDialog;
    }
}
