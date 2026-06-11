# SmartDayPlanner

## Proje Amacı

SmartDayPlanner, kullanıcıların günlük görevlerini planlamasına, düzenlemesine ve yapay zeka desteği ile optimize etmesine yardımcı olan Android tabanlı bir kişisel planlama uygulamasıdır.

Uygulamanın temel amacı, kullanıcıların günlük planlarını daha verimli yönetmelerini sağlamak ve planlama sürecini mümkün olduğunca kolaylaştırmaktır. Kullanıcılar görevlerini manuel olarak ekleyebilir, düzenleyebilir veya yapay zekadan yardım alarak mevcut planlarını yeniden oluşturabilirler.

---

## Temel Özellikler

### Görev Yönetimi

* Yeni görev ekleme
* Görev düzenleme
* Görev silme
* Görev detaylarını görüntüleme
* Görev önceliği belirleme
* Görev süresi tanımlama
* Görev tarihi seçme
* Sabah / Öğle / Akşam zaman kategorileri

### Yapay Zeka Destekli Planlama

* Kullanıcının mevcut görevlerini analiz etme
* Yeni görevleri mevcut plana entegre etme
* Gün bazlı plan revizyonu oluşturma
* Çakışan görevleri yeniden düzenleme
* Aynı görevlerin tekrar oluşmasını engelleme
* Tarih ve zaman formatlarını otomatik düzeltme

### Kullanıcı Deneyimi Özellikleri

* Çoklu görev seçimi
* Toplu görev silme
* Tüm görevleri seçme
* Uzun basarak seçim moduna geçiş
* Geri tuşu ile seçim modundan çıkış
* Akıcı liste görüntüleme

### Veri Yönetimi

* Görevlerin cihaz üzerinde saklanması
* Uygulama yeniden açıldığında verilerin korunması
* JSON tabanlı veri serileştirme
* Hızlı toplu veri güncelleme mekanizması

---

## Kullanılan Teknolojiler

### Geliştirme Ortamı

* Android Studio
* Java
* XML

### Android Bileşenleri

* Activities
* RecyclerView
* SharedPreferences
* Dialogs
* Material Components

### Veri Yönetimi

* Gson
* SharedPreferences

### Yapay Zeka Entegrasyonu

* Groq API
* Büyük Dil Modelleri (LLM)

---

## Uygulama Akışı

1. Kullanıcı günlük görevlerini oluşturur.
2. Görevler tarih ve zaman bilgileriyle kaydedilir.
3. Kullanıcı isterse yapay zekaya yeni plan taleplerini iletir.
4. Yapay zeka mevcut planı analiz eder.
5. Gün bazında optimize edilmiş yeni görev listesi oluşturulur.
6. Sistem ilgili günün planını günceller.
7. Kullanıcı görevlerini tamamlayarak ilerlemesini takip eder.

---

## Mevcut Durum

SmartDayPlanner şu anda kararlı (stable) çalışan bir sürüme sahiptir.

Mevcut sürümde:

* Görev oluşturma, düzenleme ve silme işlemleri tamamlanmıştır.
* Yapay zeka destekli günlük planlama sistemi çalışmaktadır.
* Çoklu seçim ve toplu silme özellikleri aktiftir.
* Görev detay ekranı tamamlanmıştır.
* Veri kalıcılığı sağlanmıştır.
* Gün bazlı plan revizyon sistemi çalışmaktadır.

---

## Planlanan Geliştirmeler

* Takvim entegrasyonu
* Bildirim sistemi
* Tarih aralıklı görevler
* AI verimlilik analizi
* İstatistik ve raporlama ekranları
* Room Database desteği
* Bulut senkronizasyonu

---

## Sonuç

SmartDayPlanner, klasik yapılacaklar listesi uygulamalarından farklı olarak yapay zeka destekli planlama yaklaşımını benimseyen bir kişisel üretkenlik uygulamasıdır. Kullanıcının günlük planlarını dinamik olarak güncelleyebilmesi ve yapay zeka ile yeniden organize edebilmesi sayesinde daha verimli ve esnek bir planlama deneyimi sunmayı amaçlamaktadır.
