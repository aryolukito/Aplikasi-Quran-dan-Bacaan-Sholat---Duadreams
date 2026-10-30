package com.kelompok5.duadreams.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.kelompok5.duadreams.Adapter.QuranAdapter;
import com.kelompok5.duadreams.Domain.QuranDomain;
import com.kelompok5.duadreams.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class QuranListActivity extends AppCompatActivity {
    List<QuranDomain> quranDomains = new ArrayList<>();
    QuranAdapter quranAdapter;
    RecyclerView listQuran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran_list);
        listQuran = findViewById(R.id.listSalat);
        listQuran.setLayoutManager(new LinearLayoutManager(this));
        getDataQuran();
    }

    private void getDataQuran() {
        try {
            InputStream stream = getAssets().open("list_surah.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            String strContent = new String(buffer, StandardCharsets.UTF_8);
            try {
                JSONObject jsonObject = new JSONObject(strContent);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    QuranDomain dataApi = new QuranDomain();
                    dataApi.setNo_surat(object.getString("nomor"));
                    dataApi.setSurat(object.getString("nama_latin"));
                    dataApi.setInfo(object.getString("jumlah_ayat"));
                    dataApi.setArab(object.getString("nama"));
                    quranDomains.add(dataApi);
                }
                quranAdapter = new QuranAdapter(this, quranDomains);
                listQuran.setAdapter(quranAdapter);
                quranAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException ignored) {
            Toast.makeText(QuranListActivity.this, "Terjadi Kesalahan. " +
                    "Coba ulangi beberapa saat lagi", Toast.LENGTH_SHORT).show();
        }
    }
}