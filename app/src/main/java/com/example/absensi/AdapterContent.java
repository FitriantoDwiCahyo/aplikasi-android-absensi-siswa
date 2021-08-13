package com.example.absensi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterContent extends RecyclerView.Adapter<AdapterContent.MyViewHolder>{

    Context context;

    ArrayList<AdapterListNama>AdapterListNama;


    //Membuat Interfece
    public interface dataListener{
        void onDeleteData(AdapterListNama data, int position);
    }

    dataListener listener;

    public AdapterContent(Context c,  ArrayList<AdapterListNama> p) {
        this.context = c;
        AdapterListNama = p;
        listener = (DaftarNamaSiswa2)context;

    }



    @NonNull
    @Override
    public AdapterContent.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.data_list_siswa,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.xnama.setText(AdapterListNama.get(position).getNama_lengkap());
        holder.xnis.setText(AdapterListNama.get(position).getNis());
        holder.xjk.setText(AdapterListNama.get(position).getJk());
        Picasso.with(context).load(AdapterListNama.get(position).getUrl_photo_profile()).centerCrop().fit().into(holder.xphoto);

        final String getNamaSiswa = AdapterListNama.get(position).getNama_lengkap();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle1 =new Bundle();
                bundle1.putString("nama",AdapterListNama.get(position).getNama_lengkap());
                bundle1.putString("nis",AdapterListNama.get(position).getNis());
                bundle1.putString("dataPhoto",AdapterListNama.get(position).getUrl_photo_profile());
                bundle1.putString("kelas",AdapterListNama.get(position).getKelas());
                bundle1.putString("jk",AdapterListNama.get(position).getJk());
                bundle1.putString("getPrimaryKey",AdapterListNama.get(position).getNis());
                Intent tiketdetail = new Intent(context,DetaildanAbsen.class);
                tiketdetail.putExtras(bundle1 );
                context.startActivity(tiketdetail);
            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                final String[] action = {"Update","Delete"};
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                alert.setItems(action, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Bundle bundle = new Bundle();
                                bundle.putString("dataNama",AdapterListNama.get(position).getNama_lengkap());
                                bundle.putString("dataNis",AdapterListNama.get(position).getNis());
                                bundle.putString("dataPhoto",AdapterListNama.get(position).getUrl_photo_profile());
                                bundle.putString("dataKelas",AdapterListNama.get(position).getKelas());
                                bundle.putString("getPrimaryKey",AdapterListNama.get(position).getNis());
                                Intent intent = new Intent(view.getContext(), UpdateData.class);
                                intent.putExtras(bundle);
                                context.startActivity(intent);
                                break;
                            case 1:
                                //Menggunakan interface untuk mengirim data mahasiswa, yang akan dihapus
                                listener.onDeleteData(AdapterListNama.get(position), position);
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
        return (AdapterListNama != null) ? AdapterListNama.size() : 0;
    }

     class MyViewHolder extends RecyclerView.ViewHolder{
         TextView xnama,xnis,xjk;
         ImageView xphoto;
         public MyViewHolder(@NonNull View itemView) {
             super(itemView);

             xnama=itemView.findViewById(R.id.nama_siswa);
             xnis=itemView.findViewById(R.id.nis_siswa);
             xjk=itemView.findViewById(R.id.jk);
             xphoto=itemView.findViewById(R.id.photo_profile);
//             xnis=itemView.findViewById(R.id.nis_siswa);
//             btn_hapus=itemView.findViewById(R.id.btn_hapus);
//
//             btn_hapus.setOnClickListener(new View.OnClickListener() {
//                 @Override
//                 public void onClick(View view) {
//                    listener.onRecyclerImageSelected(AdapterListNama1.get(getAdapterPosition()));
//                 }
//             });
//
//             itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                 @Override
//                 public boolean onLongClick(View view) {
//                     context.getEdit(AdapterListNama.get(getAdapterPosition()));
//                     return true;
//                 }
//             });

         }
     }
//    public interface RecyclerImageAdapter {
////        void onRecyclerImageSelected(HashMap adapterListNama);
//        void getEdit(AdapterListNama c);
//    }
}
