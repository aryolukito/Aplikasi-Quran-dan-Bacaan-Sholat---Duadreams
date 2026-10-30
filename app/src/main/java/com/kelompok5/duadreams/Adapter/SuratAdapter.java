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


import com.kelompok5.duadreams.Activity.SuratListActivity;
import com.kelompok5.duadreams.Domain.QuranDomain;
import com.kelompok5.duadreams.Domain.SuratDomain;
import com.kelompok5.duadreams.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SuratAdapter extends RecyclerView.Adapter<SuratAdapter.Viewholder> implements Serializable {
    Context context;
    private List<SuratDomain> suratDomainList;
    private List<SuratDomain> suratDomainFilterList;

    public SuratAdapter(Context context, List<SuratDomain> suratDomains){
        this.context=context;
        this.suratDomainList=suratDomains;
        this.suratDomainFilterList=new ArrayList<>(suratDomains);
    }

    @NonNull
    @Override
    public SuratAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator= LayoutInflater.from(parent.getContext()).inflate(R.layout.ayat_list_box, parent, false);
        context=parent.getContext();
        return new Viewholder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull SuratAdapter.Viewholder holder, int position) {
    SuratDomain items = suratDomainFilterList.get(position);
    holder.nomor_ayat.setText(items.getNomor_ayat());
    holder.arabic.setText(items.getArabic());
    holder.terjemahan.setText(items.getTerjemahan());

    }
    @Override
    public int getItemCount() {
        return suratDomainList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView nomor_ayat, arabic, terjemahan;
        ImageView bg_listpath, ornamenpath;
        CardView layout;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            nomor_ayat = itemView.findViewById(R.id.tvNomorAyat);
            arabic = itemView.findViewById(R.id.tvArabic);
            terjemahan = itemView.findViewById(R.id.tvTerjemahan);
            bg_listpath = itemView.findViewById(R.id.bg_list);
            ornamenpath = itemView.findViewById(R.id.ornamen);
            layout = itemView.findViewById(R.id.surat_layout);
        }
    }
}
