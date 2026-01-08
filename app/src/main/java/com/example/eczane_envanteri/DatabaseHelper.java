package com.example.eczane_envanteri;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "EczaneDB";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ilaclar (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "isim TEXT, barkod TEXT, stok INTEGER, kritik_sinir INTEGER, " +
                "fiyat REAL, stok_dusuk_mu INTEGER, toplam_satilan INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ilaclar");
        onCreate(db);
    }


    public void ilacEkle(IlacModel ilac) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("isim", ilac.isim);
        values.put("barkod", ilac.barkod);
        values.put("stok", ilac.stok);
        values.put("kritik_sinir", ilac.kritikSinir);
        values.put("fiyat", ilac.fiyat);
        values.put("stok_dusuk_mu", ilac.stok < ilac.kritikSinir ? 1 : 0);
        values.put("toplam_satilan", 0);
        db.insert("ilaclar", null, values);
    }

    public ArrayList<IlacModel> tumIlaclariGetir(String aramaKelimesi, boolean sadeceKritik) {
        ArrayList<IlacModel> liste = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM ilaclar WHERE isim LIKE '%" + aramaKelimesi + "%'";
        if (sadeceKritik) query += " AND stok_dusuk_mu = 1";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                liste.add(new IlacModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                        cursor.getInt(3), cursor.getInt(4), cursor.getDouble(5),
                        cursor.getInt(6), cursor.getInt(7)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return liste;
    }

    public void stokGuncelle(int id, int yeniStok, int eklenenSatilan) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT kritik_sinir, toplam_satilan FROM ilaclar WHERE id=" + id, null);
        if (c.moveToFirst()) {
            int kritik = c.getInt(0);
            int eskiSatilan = c.getInt(1);
            ContentValues v = new ContentValues();
            v.put("stok", yeniStok);
            v.put("stok_dusuk_mu", yeniStok < kritik ? 1 : 0);

            if (eklenenSatilan > 0) {
                v.put("toplam_satilan", eskiSatilan + eklenenSatilan);
            }

            db.update("ilaclar", v, "id=?", new String[]{String.valueOf(id)});
        }
        c.close();
    }

    public int getToplamIlacSayisi() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM ilaclar", null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public int getDusukStokSayisi() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM ilaclar WHERE stok_dusuk_mu = 1", null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public ArrayList<IlacModel> getEnCokSatanlar() {
        ArrayList<IlacModel> liste = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ilaclar ORDER BY toplam_satilan DESC LIMIT 3", null);

        if (cursor.moveToFirst()) {
            do {
                liste.add(new IlacModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                        cursor.getInt(3), cursor.getInt(4), cursor.getDouble(5),
                        cursor.getInt(6), cursor.getInt(7)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return liste;
    }
}