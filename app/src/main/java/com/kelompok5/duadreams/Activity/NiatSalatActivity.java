package com.kelompok5.duadreams.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.kelompok5.duadreams.Adapter.NiatSalatAdapter;
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

public class NiatSalatActivity extends AppCompatActivity {
    List<NiatSalatDomain> niatSalatDomains = new ArrayList<>();
    NiatSalatAdapter niatSalatAdapter;
    RecyclerView listSalat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niat_salat);
        listSalat = findViewById(R.id.listSalat);
        listSalat.setLayoutManager(new LinearLayoutManager(this));
        getDataNiatSalat();
    }

    private void getDataNiatSalat() {
        try {
            InputStream stream = getAssets().open("niatshalat.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            String strContent = new String(buffer, StandardCharsets.UTF_8);
            try {
                JSONObject jsonObject = new JSONObject(strContent);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    NiatSalatDomain dataApi = new NiatSalatDomain();
                    dataApi.setId(object.getString("id"));
                    dataApi.setNama(object.getString("name"));
                    dataApi.setArabic(object.getString("arabic"));
                    dataApi.setTerjemah(object.getString("terjemahan"));
                    niatSalatDomains.add(dataApi);
                }
                niatSalatAdapter = new NiatSalatAdapter(this, niatSalatDomains);
                listSalat.setAdapter(niatSalatAdapter);
                niatSalatAdapter.notifyDataSetChanged();
            }catch (JSONException e){
                e.printStackTrace();
            }
        }catch (IOException ignored){
            Toast.makeText(NiatSalatActivity.this, "Terjadi Kesalahan. " +
                    "Coba ulangi beberapa saat lagi", Toast.LENGTH_SHORT).show();
        }
    }
}