package com.kelompok5.duadreams.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.kelompok5.duadreams.Adapter.SalatAdapter;
import com.kelompok5.duadreams.Domain.QuranDomain;
import com.kelompok5.duadreams.Domain.SalatDomain;
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

public class BacaanSalatActivity extends AppCompatActivity {
    List<SalatDomain> salatDomains=new ArrayList<>();
    SalatAdapter salatAdapter;
    RecyclerView listSalat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bacaan_salat);
        listSalat=findViewById(R.id.listSalat);
        listSalat.setLayoutManager(new LinearLayoutManager(this));
        getDataSalat();
    }

    private void getDataSalat() {
        try {
            InputStream stream = getAssets().open("bacaanshalat.json");
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
                    SalatDomain dataApi = new SalatDomain();
                    dataApi.setId(object.getString("id"));
                    dataApi.setNama(object.getString("name"));
                    dataApi.setArabic(object.getString("arabic"));
                    dataApi.setTerjemah(object.getString("terjemahan"));
                    salatDomains.add(dataApi);
                }
                salatAdapter = new SalatAdapter(this, salatDomains);
                listSalat.setAdapter(salatAdapter);
                salatAdapter.notifyDataSetChanged();
            } catch (JSONException e){
                e.printStackTrace();
            }
        } catch (IOException ignored){
            Toast.makeText(BacaanSalatActivity.this, "Terjadi Kesalahan. " +
                    "Coba ulangi beberapa saat lagi", Toast.LENGTH_SHORT).show();
        }
    }
}