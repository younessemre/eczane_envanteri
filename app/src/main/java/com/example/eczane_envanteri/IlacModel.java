package com.example.eczane_envanteri;

public class IlacModel {
    public int id;
    public String isim;
    public String barkod;
    public int stok;
    public int kritikSinir;
    public double fiyat;
    public int stokDusukMu;
    public int toplamSatilan;

    public IlacModel() {}

    public IlacModel(int id, String isim, String barkod, int stok, int kritikSinir, double fiyat, int stokDusukMu, int toplamSatilan) {
        this.id = id;
        this.isim = isim;
        this.barkod = barkod;
        this.stok = stok;
        this.kritikSinir = kritikSinir;
        this.fiyat = fiyat;
        this.stokDusukMu = stokDusukMu;
        this.toplamSatilan = toplamSatilan;
    }
}