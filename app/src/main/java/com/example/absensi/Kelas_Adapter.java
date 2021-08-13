package com.example.absensi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Kelas_Adapter extends RecyclerView.Adapter<Kelas_Adapter.MyViewHolder>{

        Context context;
        ArrayList<Mykelas> Mykelas;

    public Kelas_Adapter(Context c , ArrayList<Mykelas>p){
            context = c;
            Mykelas = p;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_data_absen,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, final int position) {

            viewHolder.xkelas.setText(Mykelas.get(position).getKelas());
            viewHolder.tgl.setText(Mykelas.get(position).getTgl_absen());
            final String getkelas = Mykelas.get(position).getId_absen();

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Bundle bundle =new Bundle();
//                    bundle.putString("kelas",Mykelas.get(position).getKelas());
                    Intent kelas = new Intent(context,ListNamaSiswa.class);

                    kelas.putExtra("id_absen",getkelas);
                    context.startActivity(kelas);
                }
            });
    }

    @Override
    public int getItemCount() {
            return (Mykelas!=null)?Mykelas.size():0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView xkelas,tgl;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            xkelas = itemView.findViewById(R.id.xkelas);
            tgl = itemView.findViewById(R.id.tgl);
        }
    }
}

