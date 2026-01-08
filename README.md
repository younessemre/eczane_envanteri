# 💊 Eczane Envanter Takip Sistemi (Pharmacy Stock App)

Eczanelerin ilaç giriş ve çıkışlarını yönetmesini, kritik stok seviyelerini takip etmesini ve satış analizi yapmasını sağlar.

## 📱 Ekran Görüntüleri

| 🏠 Ana Katalog & Liste | 🔄 Stok Giriş/Çıkış | 📊 Raporlar & Analiz |
|:---:|:---:|:---:|
| <img width="1440" height="3120" alt="1" src="https://github.com/user-attachments/assets/4bfa9d78-a04f-4aac-9c3b-2495c33b5574" /> | <img width="1440" height="3120" alt="2" src="https://github.com/user-attachments/assets/707aa7c6-a5f9-4143-b33b-f709a31717a5" /> | <img width="1440" height="3120" alt="3" src="https://github.com/user-attachments/assets/c21f61ff-2153-4aa8-b786-90f34fefa0a2" /> |

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

* **Dil:** Java 
* **Veritabanı:** SQLite 
* **Arayüz (UI):** XML, Material Design Components 
* **Mimari:** MVC (Model-View-Controller)

## 📂 Proje Yapısı (Kısa Bakış)

* `DatabaseHelper.java`: Tüm veritabanı CRUD işlemleri ve özel SQL rapor sorguları burada yönetilir.
* `StokHareketiActivity.java`: Ürün giriş/çıkışlarının iş mantığı (Business Logic) buradadır.
* `IlacAdapter.java`: Stok durumuna göre satırların rengini (Yeşil/Kırmızı) dinamik değiştiren adaptör sınıfı.
