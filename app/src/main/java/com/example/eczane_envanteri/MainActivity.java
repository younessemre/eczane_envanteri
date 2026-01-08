package com.example.eczane_envanteri;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseHelper dbHelper;
    IlacAdapter adapter;
    ArrayList<IlacModel> ilacListesi;
    ExtendedFloatingActionButton fabYeniUrun;
    ImageView btnMenu;
    Button btnTumu, btnKritik;
    EditText etArama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerViewKatalog);
        fabYeniUrun = findViewById(R.id.fabYeniUrun);
        btnMenu = findViewById(R.id.btnMenu);
        btnTumu = findViewById(R.id.btnFiltreTumu);
        btnKritik = findViewById(R.id.btnFiltreKritik);
        etArama = findViewById(R.id.etArama);

        etArama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ilacListesi = dbHelper.tumIlaclariGetir(s.toString(), false);
                adapter = new IlacAdapter(MainActivity.this, ilacListesi);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        btnTumu.setOnClickListener(v -> {
            ilacListesi = dbHelper.tumIlaclariGetir("", false);
            adapter = new IlacAdapter(MainActivity.this, ilacListesi);
            recyclerView.setAdapter(adapter);
        });

        btnKritik.setOnClickListener(v -> {
            ilacListesi = dbHelper.tumIlaclariGetir("", true);
            adapter = new IlacAdapter(MainActivity.this, ilacListesi);
            recyclerView.setAdapter(adapter);
        });

        fabYeniUrun.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, YenillacActivity.class));
        });

        btnMenu.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(MainActivity.this, v);
            popup.getMenuInflater().inflate(R.menu.main_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                if (id == R.id.action_yeni_urun) {
                    startActivity(new Intent(MainActivity.this, YenillacActivity.class));
                    return true;
                } else if (id == R.id.action_rapor) {
                    startActivity(new Intent(MainActivity.this, RaporAnalizActivity.class));
                    return true;
                }
                return false;
            });
            popup.show();
        });

        listeyiGuncelle();
    }

    private void listeyiGuncelle() {
        if (dbHelper != null && recyclerView != null) {
            ilacListesi = dbHelper.tumIlaclariGetir("", false);
            adapter = new IlacAdapter(this, ilacListesi);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        listeyiGuncelle();
    }
}