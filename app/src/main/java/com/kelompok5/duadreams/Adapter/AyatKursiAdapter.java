package com.kelompok5.duadreams.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok5.duadreams.Domain.AyatKursiDomain;
import com.kelompok5.duadreams.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AyatKursiAdapter extends RecyclerView.Adapter<AyatKursiAdapter.Viewholder> implements Serializable {
    Context context;
    private List<AyatKursiDomain> ayatKursiDomainList;
    private List<AyatKursiDomain> ayatKursiDomainFilterList;

    public AyatKursiAdapter(Context context, List<AyatKursiDomain> ayatKursiDomains) {
        this.context = context;
        this.ayatKursiDomainList = ayatKursiDomains;
        this.ayatKursiDomainFilterList = new ArrayList<>(ayatKursiDomains);
    }

    @NonNull
    @Override
    public AyatKursiAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.ayat_kursi_list_box, parent, false);
        context=parent.getContext();
        return new Viewholder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull AyatKursiAdapter.Viewholder holder, int position) {
    AyatKursiDomain items = ayatKursiDomainFilterList.get(position);
    holder.nomor_ayat.setText(items.getId());
    holder.arabic.setText(items.getArabic());
    holder.terjemahan.setText(items.getTerjemah());
    }

    @Override
    public int getItemCount() {
        return ayatKursiDomainList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView  nomor_ayat, arabic, terjemahan;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            nomor_ayat = itemView.findViewById(R.id.tvNomorAyat);
            arabic = itemView.findViewById(R.id.tvArabic);
            terjemahan = itemView.findViewById(R.id.tvTerjemahan);
        }
    }
}
