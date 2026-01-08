package com.example.eczane_envanteri;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class IlacAdapter extends RecyclerView.Adapter<IlacAdapter.IlacViewHolder> {

    Context context;
    ArrayList<IlacModel> ilacListesi;

    public IlacAdapter(Context context, ArrayList<IlacModel> ilacListesi) {
        this.context = context;
        this.ilacListesi = ilacListesi;
    }

    @NonNull
    @Override
    public IlacViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ilac, parent, false);
        return new IlacViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IlacViewHolder holder, int position) {
        IlacModel ilac = ilacListesi.get(position);

        holder.tvIlacAdi.setText(ilac.isim);
        holder.tvStokAdedi.setText("Stok: " + ilac.stok + " Adet");
        holder.tvFiyat.setText("Fiyat: " + ilac.fiyat + " TL");

        if (ilac.stok < ilac.kritikSinir) {
            holder.tvDurumBadge.setText("KRİTİK");
            holder.tvDurumBadge.setTextColor(Color.RED);
            holder.viewDurumSeridi.setBackgroundColor(Color.RED);
        } else {
            holder.tvDurumBadge.setText("NORMAL");
            holder.tvDurumBadge.setTextColor(Color.parseColor("#009688"));
            holder.viewDurumSeridi.setBackgroundColor(Color.parseColor("#009688"));
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, StokHareketiActivity.class);
            intent.putExtra("id", ilac.id);
            intent.putExtra("isim", ilac.isim);
            intent.putExtra("stok", ilac.stok);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return ilacListesi.size();
    }

    public static class IlacViewHolder extends RecyclerView.ViewHolder {
        TextView tvIlacAdi, tvStokAdedi, tvFiyat, tvDurumBadge;
        View viewDurumSeridi;

        public IlacViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIlacAdi = itemView.findViewById(R.id.tvIlacAdi);
            tvStokAdedi = itemView.findViewById(R.id.tvStokAdedi);
            tvFiyat = itemView.findViewById(R.id.tvFiyat);
            tvDurumBadge = itemView.findViewById(R.id.tvDurumBadge);
            viewDurumSeridi = itemView.findViewById(R.id.viewDurumSeridi);
        }
    }
}