# 📄 08 – FONKSİYONEL ÖZELLİKLER (FEATURES SPECIFICATION)

## 🎯 Amaç

Bu doküman, Smart Day Planner uygulamasının sahip olduğu tüm fonksiyonel özellikleri detaylı şekilde tanımlar.

Amaç, uygulamanın kullanıcıya sunduğu işlevlerin açık ve teknik olarak anlaşılır şekilde belirtilmesidir.

---

# 🧩 1. GÖREV YÖNETİMİ ÖZELLİKLERİ

## 🔹 1.1 Görev Ekleme

Kullanıcı yeni görev oluşturabilir.

### Özellikler:

* Görev başlığı girilir
* Opsiyonel olarak süre eklenebilir
* Opsiyonel öncelik seçilebilir
* Görev veritabanına kaydedilir

### Sistem Davranışı:

* Boş görev eklenemez
* Eklenen görev otomatik olarak bugünün listesine eklenir

---

## 🔹 1.2 Görev Listeleme

Kullanıcı mevcut görevlerini görüntüleyebilir.

### Özellikler:

* Günlük filtreleme (date-based)
* Tamamlanmış / tamamlanmamış ayrımı
* RecyclerView ile listeleme

---

## 🔹 1.3 Görev Güncelleme

Kullanıcı mevcut görevleri düzenleyebilir.

### Özellikler:

* Başlık değiştirilebilir
* Süre güncellenebilir
* Öncelik değiştirilebilir

---

## 🔹 1.4 Görev Silme

Kullanıcı görevleri sistemden kaldırabilir.

### Özellikler:

* Onay mekanizması (confirmation dialog)
* Kalıcı silme işlemi (Room DB üzerinden)

---

## 🔹 1.5 Görev Tamamlama

Kullanıcı görevleri tamamlandı olarak işaretleyebilir.

### Özellikler:

* Checkbox sistemi
* Anlık UI güncellemesi
* Veritabanı güncellemesi

---

# 📅 2. GÜNLÜK PLANLAMA ÖZELLİKLERİ

## 🔹 2.1 Günlük Görev Görüntüleme

Kullanıcı sadece seçilen güne ait görevleri görebilir.

### Özellikler:

* Date-based filtering
* Bugün / geçmiş gün ayrımı

---

## 🔹 2.2 Günlük Plan Yönetimi

Kullanıcı gün içindeki görevlerini organize edebilir.

### Özellikler:

* Görev sıralama (opsiyonel)
* Tamamlanan görevleri işaretleme
* Günlük ilerleme takibi

---

# 💾 3. VERİ YÖNETİMİ ÖZELLİKLERİ

## 🔹 3.1 Kalıcı Veri Saklama

* Tüm görevler Room Database içinde saklanır
* Uygulama kapansa bile veri korunur

---

## 🔹 3.2 Veri Güncelleme Senkronizasyonu

* UI değişiklikleri anında veritabanına yansır
* LiveData ile otomatik güncelleme sağlanır

---

# ⚙️ 4. KULLANICI ETKİLEŞİM ÖZELLİKLERİ

## 🔹 4.1 Sezgisel Kullanım

* Minimal ve anlaşılır arayüz
* Tek dokunuşla görev ekleme
* Basit navigasyon yapısı

---

## 🔹 4.2 Hızlı İşlem

* Görev ekleme ve silme işlemleri anında gerçekleşir
* Liste güncellemeleri gecikmesiz yapılır

---

# 📦 5. JSON IMPORT / EXPORT (BONUS ÖZELLİK)

## 🔹 5.1 Veri Dışa Aktarma

* Tüm görevler JSON formatında dışa aktarılabilir
* Kullanıcı verisini yedekleyebilir

## 🔹 5.2 Veri İçe Aktarma

* JSON dosyasından veri yüklenebilir
* Mevcut görev listesine entegre edilir

---

# 🚀 6. GELECEK ÖZELLİKLER (PHASE 2 UYUMLU)

Bu sistem ileride aşağıdaki özellikleri destekleyecek şekilde tasarlanmıştır:

* GPT tabanlı akıllı planlama
* Otomatik görev zamanlama
* Bildirim sistemi
* Takvim entegrasyonu
* Kullanıcı bazlı analizler

---

# 📌 SONUÇ

Smart Day Planner uygulaması:

* Temel görev yönetim sistemini sağlar
* Kullanıcıya basit ve hızlı kullanım sunar
* Veri kaybı olmadan çalışır
* Gelecekte akıllı sistemlere genişletilebilir yapıdadır
