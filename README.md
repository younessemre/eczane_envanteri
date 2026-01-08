# 💊 Eczane Envanter Takip Sistemi (Pharmacy Stock App)

Java ve XML kullanılarak geliştirilmiş, **Offline-First** (internet gerektirmeyen) prensibiyle çalışan kapsamlı bir stok yönetim uygulamasıdır. Eczane veya depoların ilaç giriş-çıkışlarını yönetmesini, kritik stok seviyelerini takip etmesini ve satış analizi yapmasını sağlar.

## 📱 Ekran Görüntüleri

| 🏠 Ana Katalog & Liste | 🔄 Stok Giriş/Çıkış | 📊 Raporlar & Analiz |
|:---:|:---:|:---:|
| <img width="1440" height="3120" alt="1" src="https://github.com/user-attachments/assets/335aa4ad-5500-4e36-bb4b-71ddd0f84496" />
 | <img width="1440" height="3120" alt="2" src="https://github.com/user-attachments/assets/9a208aaa-07e4-40ec-b353-9988c013efa2" /> | <img width="1440" height="3120" alt="3" src="https://github.com/user-attachments/assets/2fc079b1-797c-4b5d-ac3b-3581d80539e7" />
 |

## ✨ Öne Çıkan Özellikler

* **⚡ Kritik Stok Algoritması:** Her ürün için belirlenen kritik sınırın (Örn: 10 adet) altına düşüldüğünde:
    * Liste elemanı otomatik olarak **Kırmızı Uyarı Rozeti** alır.
    * Kullanıcı görsel olarak uyarılır.
* **📉 Akıllı Filtreleme:** Tek dokunuşla stoğu tükenmek üzere olan ürünleri listeleme özelliği.
* **💾 Native Veritabanı (SQLite):** Harici bir kütüphane (ORM) kullanılmadan, saf SQL sorguları ile `SQLiteOpenHelper` üzerinde tam performanslı veri yönetimi.
* **🚫 Satış Kontrolü:** Stok çıkışı yapılırken, mevcut stok adedi kontrol edilir. Yetersiz stok durumunda satış engellenir ve kullanıcı uyarılır.
* **📊 Finansal Raporlama:**
    * Toplam envanter ve kritik ürün sayısı.
    * SQL `ORDER BY` sorgusu ile hesaplanan **"En Çok Satanlar"** listesi.

## 🛠️ Kullanılan Teknolojiler

* **Dil:** Java (Android Native)
* **Veritabanı:** SQLite (Yerel Depolama)
* **Arayüz (UI):** XML, Material Design Components (CardView, RecyclerView, FloatingActionButton)
* **Mimari:** MVC (Model-View-Controller)

## 📂 Proje Yapısı (Kısa Bakış)

* `DatabaseHelper.java`: Tüm veritabanı CRUD işlemleri ve özel SQL rapor sorguları burada yönetilir.
* `StokHareketiActivity.java`: Ürün giriş/çıkışlarının iş mantığı (Business Logic) buradadır.
* `IlacAdapter.java`: Stok durumuna göre satırların rengini (Yeşil/Kırmızı) dinamik değiştiren adaptör sınıfı.

## 🚀 Kurulum ve Çalıştırma

1.  Projeyi bilgisayarınıza klonlayın:
    ```bash
    git clone [https://github.com/KULLANICI_ADIN/eczane-envanteri.git](https://github.com/KULLANICI_ADIN/eczane-envanteri.git)
    ```
2.  Android Studio'yu açın ve **File > Open** diyerek klasörü seçin.
3.  Gradle senkronizasyonunun bitmesini bekleyin.
4.  Emulator veya fiziksel cihaz seçerek **Run (▶)** tuşuna basın.

---
*Geliştirici: [Senin Adın]*
