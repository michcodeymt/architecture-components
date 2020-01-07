package com.ymt.components.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ymt.components.R;
import com.ymt.components.database.Nota;

import java.util.ArrayList;
import java.util.List;

public class NotaAdapter extends RecyclerView.Adapter<NotaAdapter.NotaAdapterViewHolder> {

    private List<Nota> list_notas = new ArrayList<>();
    OnItemClickListener listener;

    @NonNull
    @Override
    public NotaAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notas, parent, false);
        return new NotaAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotaAdapterViewHolder holder, int position) {
        final Nota nota = list_notas.get(position);

        holder.tv_titulo.setText(nota.getTitulo());
        holder.tv_descripcion.setText(nota.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return list_notas.size();
    }

    public void setNotas(List<Nota> notas) {
        this.list_notas = notas;
        notifyDataSetChanged();
    }

    public Nota obtenerNota(int position) {
        return list_notas.get(position);
    }

    public class NotaAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView tv_titulo, tv_descripcion;


        public NotaAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_titulo = itemView.findViewById(R.id.tv_titulo);
            tv_descripcion = itemView.findViewById(R.id.tv_descripcion);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(list_notas.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
       void onItemClick(Nota nota);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
