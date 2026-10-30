//validasi surat list box
package com.kelompok5.duadreams.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok5.duadreams.Activity.SuratListActivity;
import com.kelompok5.duadreams.R;
import com.kelompok5.duadreams.Domain.QuranDomain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuranAdapter extends RecyclerView.Adapter<QuranAdapter.Viewholder> implements Serializable {

    private List<QuranDomain> quranDomainList;
    private List<QuranDomain> quranDomainFilterList;
    Context context;

    public QuranAdapter(Context context, List<QuranDomain> quranDomains) {
        this.context=context;
        this.quranDomainList=quranDomains;
        this.quranDomainFilterList = new ArrayList<>(quranDomains);
    }


    @NonNull
    @Override
    public QuranAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.surat_list_box, parent, false);
        context = parent.getContext();
        return new Viewholder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull QuranAdapter.Viewholder holder, int position) {
    QuranDomain items = quranDomainList.get(position);
    holder.no_surat.setText(items.getNo_surat());
    holder.surat.setText(items.getSurat());
    holder.info.setText(items.getInfo());
    holder.arab.setText(items.getArab());
    holder.bg_listpath.setImageResource(R.drawable.btn_background_1);
    holder.ornamenpath.setImageResource(R.drawable.frame);


    holder.layout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Intent intent = new Intent(context, SuratListActivity.class);
        intent.putExtra(SuratListActivity.Detail_Surat, quranDomainList.get(holder.getAdapterPosition()));
        context.startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount() {
        return quranDomainList.size();
    }
    public class Viewholder extends RecyclerView.ViewHolder {
        TextView no_surat, surat, info, arab;
        ImageView bg_listpath, ornamenpath;
        CardView layout;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            no_surat = itemView.findViewById(R.id.no_suratTxt);
            surat = itemView.findViewById(R.id.suratTxt);
            info = itemView.findViewById(R.id.infoTxt);
            arab = itemView.findViewById(R.id.arabTxt);
            bg_listpath = itemView.findViewById(R.id.bg_list);
            ornamenpath = itemView.findViewById(R.id.ornamen);
            layout = itemView.findViewById(R.id.surat_layout);
        }
    }
}
