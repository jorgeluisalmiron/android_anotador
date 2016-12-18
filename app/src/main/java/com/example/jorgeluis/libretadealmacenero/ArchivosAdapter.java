package com.example.jorgeluis.libretadealmacenero;

import android.content.Context;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jorge Luis on 14/09/2016.
 */
public class ArchivosAdapter extends RecyclerView.Adapter<ArchivosAdapter.ViewHolder>{

    private Context context;
    private List<Archivo> archivoList=null;

    public ArchivosAdapter(Context context, List<Archivo> archivoList)
    {
        this.context = context;
        this.archivoList = archivoList;

    }

    public ArchivosAdapter(Context context)
    {
        this.context = context;
    }

    public void setAdapterList(List<Archivo> archivoList)
    {
        this.archivoList=archivoList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.getNombre().setText(archivoList.get(position).getNombre());
        holder.getFecha().setText(archivoList.get(position).getFecha());
    }

    @Override
    public int getItemCount() {
        if (archivoList == null) {
            return 0;
        } else {
            return archivoList.size();

        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombre;
        private TextView fecha;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.TextViewNombre);
            fecha = (TextView) itemView.findViewById(R.id.TextViewFecha);
        }


        public TextView getNombre() {
            return nombre;
        }

        public void setNombre(TextView nombre) {
            this.nombre = nombre;
        }

        public TextView getFecha() {
            return fecha;
        }

        public void setFecha(TextView fecha) {
            this.fecha = fecha;
        }
    }
}
