package com.kelompok5.duadreams.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok5.duadreams.Domain.SalatDomain;
import com.kelompok5.duadreams.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SalatAdapter extends RecyclerView.Adapter<SalatAdapter.Viewholder> implements Serializable {
    Context context;
    private List<SalatDomain> salatDomainList;
    private List<SalatDomain> salatDomainFilterList;

    public SalatAdapter(Context context, List<SalatDomain> salatDomains) {
        this.context = context;
        this.salatDomainList = salatDomains;
        this.salatDomainFilterList = new ArrayList<>(salatDomains);
    }

    @NonNull
    @Override
    public SalatAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.salat_list_box, parent, false);
        context=parent.getContext();
        return new Viewholder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull SalatAdapter.Viewholder holder, int position) {
    SalatDomain items = salatDomainFilterList.get(position);
    holder.nomor.setText(items.getId());
    holder.nama.setText(items.getNama());
    holder.arabic.setText(items.getArabic());
    holder.terjemahan.setText(items.getTerjemah());
    }

    @Override
    public int getItemCount() {
        return salatDomainList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
    TextView nomor, nama, arabic, terjemahan;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.tvNama);
            nomor = itemView.findViewById(R.id.tvNomorAyat);
            arabic = itemView.findViewById(R.id.tvArabic);
            terjemahan = itemView.findViewById(R.id.tvTerjemahan);
        }
    }
}
