package com.ymt.components.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ymt.components.R;

public class AgregarNotaActivity extends AppCompatActivity {

    EditText edt_titulo_nuevo, edt_descripcion_nuevo;
    Button btn_grabar_nuevo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_nota);

        edt_titulo_nuevo = findViewById(R.id.edt_titulo_nuevo);
        edt_descripcion_nuevo = findViewById(R.id.edt_descripcion_nuevo);
        btn_grabar_nuevo = findViewById(R.id.btn_grabar_notas);


        Intent intent = getIntent();

        if (intent.hasExtra("id")) {
            setTitle("Editar nota");
            edt_titulo_nuevo.setText(intent.getStringExtra("titulo"));
            edt_descripcion_nuevo.setText(intent.getStringExtra("descripcion"));
        } else {
            setTitle("Grabar nota");
        }


        btn_grabar_nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = edt_titulo_nuevo.getText().toString();
                String descripcion = edt_descripcion_nuevo.getText().toString();

                if (titulo.equals("") || descripcion.equals("")) {
                    Toast.makeText(AgregarNotaActivity.this, "Debes ingresar los campos",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent i = new Intent();
                i.putExtra("titulo", titulo);
                i.putExtra("descripcion", descripcion);

                int id = getIntent().getIntExtra("id", -1);

                if (id != -1) {
                    i.putExtra("id", id);
                }

                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}
