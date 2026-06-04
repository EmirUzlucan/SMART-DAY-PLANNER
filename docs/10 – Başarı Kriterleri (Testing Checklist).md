# 📄 10 – BAŞARI KRİTERLERİ (TESTING CHECKLIST)

## 🎯 Amaç

Bu doküman, Smart Day Planner uygulamasının geliştirme süreci sonunda başarılı kabul edilmesi için gerekli kriterleri tanımlar.

Amaç, uygulamanın tüm temel fonksiyonlarının doğru ve hatasız çalıştığını doğrulamaktır.

---

# ✅ 1. GÖREV YÖNETİMİ TESTLERİ

## 🔹 1.1 Görev Ekleme Testi

* [ ] Kullanıcı yeni görev oluşturabiliyor mu?
* [ ] Boş görev eklenmesi engelleniyor mu?
* [ ] Eklenen görev listeye anında düşüyor mu?
* [ ] Veri veritabanına kaydediliyor mu?

---

## 🔹 1.2 Görev Listeleme Testi

* [ ] Görevler doğru şekilde listeleniyor mu?
* [ ] Sadece ilgili güne ait görevler gösteriliyor mu?
* [ ] Liste scroll performansı düzgün mü?

---

## 🔹 1.3 Görev Güncelleme Testi

* [ ] Görev bilgileri düzenlenebiliyor mu?
* [ ] Güncelleme sonrası veri kalıcı mı?
* [ ] UI değişiklikleri anında yansıyor mu?

---

## 🔹 1.4 Görev Silme Testi

* [ ] Görev silinebiliyor mu?
* [ ] Silme sonrası listeden kaldırılıyor mu?
* [ ] Veri veritabanından tamamen siliniyor mu?
* [ ] Onay mekanizması çalışıyor mu?

---

## 🔹 1.5 Görev Tamamlama Testi

* [ ] Görev tamamlandı olarak işaretlenebiliyor mu?
* [ ] UI üzerinde durum değişiyor mu?
* [ ] Veritabanı güncelleniyor mu?

---

# 💾 2. VERİ KALICILIK TESTLERİ

## 🔹 2.1 Uygulama Kapatma Testi

* [ ] Uygulama kapatıldığında veriler kayboluyor mu? (Beklenen: HAYIR)
* [ ] Tekrar açıldığında veriler geri geliyor mu?

---

## 🔹 2.2 Room Database Testi

* [ ] Veri ekleme sorunsuz çalışıyor mu?
* [ ] Veri okuma hatasız mı?
* [ ] Güncelleme ve silme işlemleri doğru mu?

---

# 📱 3. KULLANICI ARAYÜZ TESTLERİ

## 🔹 3.1 Navigasyon Testi

* [ ] Ekranlar arası geçiş sorunsuz mu?
* [ ] Home → Add → Detail akışı düzgün mü?

---

## 🔹 3.2 UI Stabilite Testi

* [ ] Uygulama sırasında crash oluyor mu?
* [ ] RecyclerView düzgün render ediyor mu?
* [ ] Scroll sırasında takılma var mı?

---

# ⚡ 4. PERFORMANS TESTLERİ

## 🔹 4.1 Açılış Performansı

* [ ] Uygulama hızlı açılıyor mu? (1–2 saniye hedef)
* [ ] Ana ekran gecikmesiz yükleniyor mu?

---

## 🔹 4.2 UI Akıcılığı

* [ ] Listeleme akıcı mı?
* [ ] Kullanıcı etkileşimleri gecikmesiz mi?

---

## 🔹 4.3 Background İşlemler

* [ ] Veritabanı işlemleri UI thread’i bloklamıyor mu?
* [ ] Coroutine kullanımı doğru mu?

---

# 📦 5. VERİ DOĞRULUK TESTLERİ

## 🔹 5.1 Veri Tutarlılığı

* [ ] Aynı veri tekrar ekleniyor mu?
* [ ] Silinen veri geri geliyor mu?
* [ ] Güncellenen veri doğru kaydediliyor mu?

---

# 🧪 6. GENEL SİSTEM TESTLERİ

## 🔹 6.1 Stabilite Testi

* [ ] Uygulama uzun süre açık kalınca çöküyor mu?
* [ ] Çok sayıda görev eklenince performans düşüyor mu?

---

## 🔹 6.2 Cihaz Uyumluluk Testi

* [ ] Farklı ekran boyutlarında düzgün çalışıyor mu?
* [ ] Küçük ekranlarda UI bozuluyor mu?

---

# 🚀 7. BAŞARI KRİTERLERİ (PASS CONDITION)

Uygulama başarılı sayılır eğer:

* Tüm CRUD işlemleri hatasız çalışıyorsa
* Veriler kalıcı olarak saklanıyorsa
* UI stabil ve akıcıysa
* Ekranlar arası geçiş sorunsuzsa
* Uygulama crash vermeden çalışıyorsa

---

# 📌 SONUÇ

Bu test kriterleri sağlandığında Smart Day Planner uygulaması:

* Minimum gereksinimleri karşılar
* Teslim edilebilir bir proje haline gelir
* Geliştirmeye açık stabil bir temel sunar
