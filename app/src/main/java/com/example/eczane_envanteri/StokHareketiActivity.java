package com.example.eczane_envanteri;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar; // Toolbar eklendi

public class StokHareketiActivity extends AppCompatActivity {

    TextView tvAd, tvMevcut;
    EditText etMiktar;
    RadioButton rbGiris;
    Button btnOnayla;
    DatabaseHelper dbHelper;
    int ilacId, mevcutStok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stok_hareketi);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Stok Hareketi");
        }


        dbHelper = new DatabaseHelper(this);
        tvAd = findViewById(R.id.tvStokIlacAdi);
        tvMevcut = findViewById(R.id.tvStokMevcut);
        etMiktar = findViewById(R.id.etStokMiktar);
        rbGiris = findViewById(R.id.rbGiris);
        btnOnayla = findViewById(R.id.btnOnayla);

        ilacId = getIntent().getIntExtra("id", -1);
        String isim = getIntent().getStringExtra("isim");
        mevcutStok = getIntent().getIntExtra("stok", 0);

        tvAd.setText("İlaç Adı: " + isim);
        tvMevcut.setText("Mevcut Stok: " + mevcutStok + " Adet");

        btnOnayla.setOnClickListener(v -> {
            String miktarStr = etMiktar.getText().toString();
            if (miktarStr.isEmpty()) return;

            int miktar = Integer.parseInt(miktarStr);
            int yeniStok;
            int satilanAdet = 0;

            if (rbGiris.isChecked()) {
                yeniStok = mevcutStok + miktar;
            } else {
                if (mevcutStok < miktar) {
                    Toast.makeText(this, "Yetersiz Stok!", Toast.LENGTH_SHORT).show();
                    return;
                }
                yeniStok = mevcutStok - miktar;
                satilanAdet = miktar;
            }

            dbHelper.stokGuncelle(ilacId, yeniStok, satilanAdet);
            Toast.makeText(this, "Stok Güncellendi!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}