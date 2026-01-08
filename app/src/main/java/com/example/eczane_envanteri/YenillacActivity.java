package com.example.eczane_envanteri;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar; // Toolbar kütüphanesi eklendi

public class YenillacActivity extends AppCompatActivity {

    EditText etIlacAdi, etBarkod, etStok, etKritik, etFiyat;
    Button btnKaydet;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeni_ilac);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Yeni İlaç Kaydı");
        }


        dbHelper = new DatabaseHelper(this);
        etIlacAdi = findViewById(R.id.etIlacAdi);
        etBarkod = findViewById(R.id.etBarkod);
        etStok = findViewById(R.id.etStok);
        etKritik = findViewById(R.id.etKritik);
        etFiyat = findViewById(R.id.etFiyat);
        btnKaydet = findViewById(R.id.btnKaydet);
        btnKaydet.setOnClickListener(v -> kaydet());
    }


    private void kaydet() {
        String isim = etIlacAdi.getText().toString();
        String barkod = etBarkod.getText().toString();
        String stokStr = etStok.getText().toString();
        String kritikStr = etKritik.getText().toString();
        String fiyatStr = etFiyat.getText().toString();

        if (isim.isEmpty() || stokStr.isEmpty() || kritikStr.isEmpty() || fiyatStr.isEmpty()) {
            Toast.makeText(this, "Lütfen tüm alanları doldurun!", Toast.LENGTH_SHORT).show();
            return;
        }

        IlacModel yeniIlac = new IlacModel();
        yeniIlac.isim = isim;
        yeniIlac.barkod = barkod;
        yeniIlac.stok = Integer.parseInt(stokStr);
        yeniIlac.kritikSinir = Integer.parseInt(kritikStr);
        yeniIlac.fiyat = Double.parseDouble(fiyatStr);

        dbHelper.ilacEkle(yeniIlac);
        Toast.makeText(this, "İlaç başarıyla eklendi!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}