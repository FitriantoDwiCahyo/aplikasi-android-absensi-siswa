package com.example.absensi;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;




public class TableViewAdapter extends RecyclerView.Adapter<TableViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<ListModelSiswa> ListModelSiswa;

    public TableViewAdapter(Context c, ArrayList<ListModelSiswa> p) {
        context = c;
        ListModelSiswa = p;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.table_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, final int i) {

//        viewHolder.No.setText(ListModelSiswa.get(i).getNo().toString());
        viewHolder.NamaSiswa.setText(ListModelSiswa.get(i).getNama());
        viewHolder.valuekehadiran.setText(ListModelSiswa.get(i).getKehadiran());
        viewHolder.jk.setText(ListModelSiswa.get(i).getJk());
        viewHolder.nis.setText(ListModelSiswa.get(i).getNis());
        viewHolder.kelas.setText(ListModelSiswa.get(i).getKelas());
        viewHolder.kethadir.setText(ListModelSiswa.get(i).getKeterangan());


        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                final String[] action = {"Dapatkan Pdf"};
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setItems(action, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        switch (i){
                            case 0:
                            Bundle bundle = new Bundle();
                            bundle.putString("dataNama",ListModelSiswa.get(i).getNama());
                            bundle.putString("dataKehadiran",ListModelSiswa.get(i).getKehadiran());
                            bundle.putString("dataJk",ListModelSiswa.get(i).getJk());
                            bundle.putString("dataKeterangan",ListModelSiswa.get(i).getKeterangan());
                            bundle.putString("getNis",ListModelSiswa.get(i).getNis());
                            bundle.putString("getKelas",ListModelSiswa.get(i).getKelas());
                            Intent intent = new Intent(view.getContext(), ReadPdfAbsen.class);
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                            break;
                        }


                    }
                });
                alert.create();
                alert.show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return ListModelSiswa.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView No, NamaSiswa,valuekehadiran,jk,nis,kelas,kethadir;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

//            No = itemView.findViewById(R.id.No);
            NamaSiswa = itemView.findViewById(R.id.Nama);
            valuekehadiran =itemView.findViewById(R.id.valuehadir);
            jk=itemView.findViewById(R.id.jk);
            nis = itemView.findViewById(R.id.nis);
            kelas =itemView.findViewById(R.id.kelas);
            kethadir=itemView.findViewById(R.id.kethadir);
        }
    }

}





