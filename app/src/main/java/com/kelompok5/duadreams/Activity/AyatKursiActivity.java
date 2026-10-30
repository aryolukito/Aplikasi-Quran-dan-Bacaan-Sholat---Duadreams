package com.kelompok5.duadreams.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.kelompok5.duadreams.Adapter.AyatKursiAdapter;
import com.kelompok5.duadreams.Adapter.NiatSalatAdapter;
import com.kelompok5.duadreams.Domain.AyatKursiDomain;
import com.kelompok5.duadreams.Domain.NiatSalatDomain;
import com.kelompok5.duadreams.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AyatKursiActivity extends AppCompatActivity {
    List<AyatKursiDomain> ayatKursiDomains = new ArrayList<>();
    AyatKursiAdapter ayatKursiAdapter;
    RecyclerView listSalat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayat_kursi);
        listSalat = findViewById(R.id.listSalat);
        listSalat.setLayoutManager(new LinearLayoutManager(this));
        getDataAyatKursi();
    }

    private void getDataAyatKursi() {
        try {
            InputStream stream = getAssets().open("ayatkursi.json");
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
                    AyatKursiDomain dataApi = new AyatKursiDomain();
                    dataApi.setId(object.getString("id"));
                    dataApi.setArabic(object.getString("arabic"));
                    dataApi.setTerjemah(object.getString("translation"));
                    ayatKursiDomains.add(dataApi);
                }
                ayatKursiAdapter = new AyatKursiAdapter(this, ayatKursiDomains);
                listSalat.setAdapter(ayatKursiAdapter);
                ayatKursiAdapter.notifyDataSetChanged();
            }catch (JSONException e){
                e.printStackTrace();
            }
        }catch (IOException ignored){
            Toast.makeText(AyatKursiActivity.this, "Terjadi Kesalahan. " +
                    "Coba ulangi beberapa saat lagi", Toast.LENGTH_SHORT).show();
        }
    }
}