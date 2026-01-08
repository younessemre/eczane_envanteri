package com.example.eczane_envanteri;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar; // Toolbar kütüphanesi eklendi
import java.util.ArrayList;

public class RaporAnalizActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    TextView tvToplam, tvDusuk, tvEnCokSatanlar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapor_analiz);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Rapor ve Analiz");
        }

        dbHelper = new DatabaseHelper(this);
        tvToplam = findViewById(R.id.tvToplamIlac);
        tvDusuk = findViewById(R.id.tvDusukStokSayisi);
        tvEnCokSatanlar = findViewById(R.id.tvEnCokSatanlar);

        tvToplam.setText(String.valueOf(dbHelper.getToplamIlacSayisi()));
        tvDusuk.setText(String.valueOf(dbHelper.getDusukStokSayisi()));
        ArrayList<IlacModel> enCoklar = dbHelper.getEnCokSatanlar();
        StringBuilder sb = new StringBuilder();


        if (enCoklar.isEmpty()) {
            sb.append("Henüz satış verisi yok.");
        } else {
            for (int i = 0; i < enCoklar.size(); i++) {
                sb.append((i + 1) + ". " + enCoklar.get(i).isim + " (" + enCoklar.get(i).toplamSatilan + " Adet)\n");
            }
        }
        tvEnCokSatanlar.setText(sb.toString());
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}