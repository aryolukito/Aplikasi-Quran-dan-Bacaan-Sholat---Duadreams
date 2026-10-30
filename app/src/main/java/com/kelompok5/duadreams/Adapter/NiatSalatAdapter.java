package com.kelompok5.duadreams.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok5.duadreams.Domain.NiatSalatDomain;
import com.kelompok5.duadreams.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NiatSalatAdapter extends RecyclerView.Adapter<NiatSalatAdapter.Viewholder> implements Serializable {
    Context context;
    private List<NiatSalatDomain> niatSalatDomainList;
    private List<NiatSalatDomain> niatSalatDomainFilterList;

    public NiatSalatAdapter(Context context, List<NiatSalatDomain> niatSalatDomains) {
        this.context = context;
        this.niatSalatDomainList = niatSalatDomains;
        this.niatSalatDomainFilterList = new ArrayList<>(niatSalatDomains);
    }

    @NonNull
    @Override
    public NiatSalatAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.niat_salat_list_box, parent, false);
        context=parent.getContext();
        return new Viewholder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull NiatSalatAdapter.Viewholder holder, int position) {
    NiatSalatDomain items = niatSalatDomainFilterList.get(position);
    holder.nomor_ayat.setText(items.getId());
    holder.nama.setText(items.getNama());
    holder.arabic.setText(items.getArabic());
    holder.terjemahan.setText(items.getTerjemah());

    }

    @Override
    public int getItemCount() {
        return niatSalatDomainList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView nama, nomor_ayat, arabic, terjemahan;
        ImageView bg_listpath, ornamenpath;
        CardView layout;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            nomor_ayat = itemView.findViewById(R.id.tvNomorAyat);
            nama = itemView.findViewById(R.id.tvNama);
            arabic = itemView.findViewById(R.id.tvArabic);
            terjemahan = itemView.findViewById(R.id.tvTerjemahan);
            bg_listpath = itemView.findViewById(R.id.bg_list);
            ornamenpath = itemView.findViewById(R.id.ornamen);
            layout = itemView.findViewById(R.id.surat_layout);
        }
    }
}
