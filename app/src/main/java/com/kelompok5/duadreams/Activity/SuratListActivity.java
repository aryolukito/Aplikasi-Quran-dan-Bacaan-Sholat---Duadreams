package com.kelompok5.duadreams.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.kelompok5.duadreams.Adapter.QuranAdapter;
import com.kelompok5.duadreams.Adapter.SuratAdapter;
import com.kelompok5.duadreams.Domain.QuranDomain;
import com.kelompok5.duadreams.Domain.SuratDomain;
import com.kelompok5.duadreams.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SuratListActivity extends AppCompatActivity {
    public static final String Detail_Surat = "Detail_Surat";
    List<SuratDomain> suratDomains = new ArrayList<>();
    String strNomor, strSurat, strArab;
    QuranDomain quranDomain;
    SuratAdapter suratAdapter;
    TextView nomor, surat, arab;
    RecyclerView listSurat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surat_list);
        nomor = findViewById(R.id.no_suratTxt);
        surat = findViewById(R.id.suratTxt);
        arab = findViewById(R.id.arabTxt);
        listSurat = findViewById(R.id.listSurat);
        listSurat.setLayoutManager(new LinearLayoutManager(this));

        quranDomain = (QuranDomain) getIntent().getSerializableExtra(Detail_Surat);
        if (quranDomain !=null) {
            strNomor = quranDomain.getNo_surat();
            strSurat = quranDomain.getSurat();
            strArab = quranDomain.getArab();
            nomor.setText(strNomor);
            surat.setText(strSurat);
            arab.setText(strArab);
        }
        getDataSurat();
    }

    private void getDataSurat() {
        try {
            InputStream stream=getAssets().open("1.json");
            int size=stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            String strContent1 =new String(buffer, StandardCharsets.UTF_8);
            try {
                JSONObject jsonObject =new JSONObject(strContent1);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i=0;i<jsonArray.length(); i++){
                    JSONObject object = jsonArray.optJSONObject(i);
                    SuratDomain dataApi = new SuratDomain();
                    dataApi.setNomor_ayat(object.getString("nomor"));
                    dataApi.setArabic(object.getString("arabic"));
                    dataApi.setTerjemahan(object.getString("terjemahan"));
                    suratDomains.add(dataApi);
                }
                suratAdapter=new SuratAdapter(this,suratDomains);
                listSurat.setAdapter(suratAdapter);
                suratAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }catch (IOException ignored) {
            Toast.makeText(SuratListActivity.this, "Terjadi Kesalahan. " +
                    "Coba ulangi beberapa saat lagi", Toast.LENGTH_SHORT).show();
        }
    }


}