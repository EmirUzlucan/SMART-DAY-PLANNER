# 📄 04 – EKRAN TASARIMLARI (UI/UX SPECIFICATION)

# 1. Amaç

Bu doküman Smart Day Planner uygulamasının kullanıcı arayüzü tasarımını, ekran yapılarını ve kullanıcı etkileşimlerini tanımlamaktadır.

Tasarımın temel hedefleri:

* Basit kullanım
* Hızlı erişim
* Modern görünüm
* Minimum dikkat dağıtıcı öğe
* Gelecekte eklenecek yapay zeka destekli planlama sistemine uygun yapı

---

# 2. Genel Tasarım Prensipleri

## 2.1 Tasarım Yaklaşımı

Uygulama minimalist bir tasarıma sahip olacaktır.

Kullanıcı uygulamayı açtığında doğrudan görevlerini görebilmelidir.

Ana odak noktası görev yönetimi ve günlük plan takibidir.

---

## 2.2 Navigasyon Yapısı

Uygulama içerisinde Bottom Navigation kullanılacaktır.

Sekmeler:

* Ana Sayfa
* Ayarlar

Görev ekleme işlemi Floating Action Button (FAB) ile gerçekleştirilecektir.

---

## 2.3 Ekran Geçiş Diyagramı

```text
Home Screen
    │
 ┌──┴─────────────┐
 ▼                ▼
Add Task      Task Detail
    │               │
    └──────┬────────┘
           ▼
      Home Screen

Home Screen
     │
     ▼
 Settings
```

---

# 3. SCREEN 1 – HOME SCREEN

## Amaç

Kullanıcının günlük görevlerini görüntülediği ve yönettiği ana ekrandır.

Uygulama açıldığında ilk olarak bu ekran görüntülenir.

---

## Wireframe

```text
┌────────────────────────────┐
│ Smart Day Planner          │
│ 12 Haziran 2026            │
├────────────────────────────┤
│ Günün Görevleri            │
│                            │
│ ┌───────────────────────┐  │
│ │ ☑ Spor Yap            │  │
│ │ Süre: 60 dk           │  │
│ │ Öncelik: Orta         │  │
│ └───────────────────────┘  │
│                            │
│ ┌───────────────────────┐  │
│ │ ☐ Mobil Proje         │  │
│ │ Süre: 120 dk          │  │
│ │ Öncelik: Yüksek       │  │
│ └───────────────────────┘  │
│                            │
│                      (+)   │
├────────────────────────────┤
│ Home              Settings │
└────────────────────────────┘
```

---

## Gösterilecek Bilgiler

Her görev kartında:

* Görev adı
* Tahmini süre
* Öncelik seviyesi
* Tamamlanma durumu

---

## Kullanıcı Etkileşimleri

### Göreve Tıklama

Görev detay ekranına geçiş yapılır.

```text
Home → Task Detail
```

### Checkbox İşaretleme

Görevin tamamlanma durumu güncellenir.

### FAB Butonu

Yeni görev oluşturma ekranını açar.

```text
Home → Add Task
```

---

# 4. SCREEN 2 – ADD TASK SCREEN

## Amaç

Kullanıcının sisteme yeni görev eklemesini sağlar.

---

## Wireframe

```text
┌────────────────────────────┐
│ ← Yeni Görev               │
├────────────────────────────┤
│ Görev Adı                  │
│ [______________________]   │
│                            │
│ Tahmini Süre (dk)          │
│ [______________________]   │
│                            │
│ Öncelik                    │
│ [ Düşük ▼ ]                │
│                            │
│ Tarih                      │
│ [ 12/06/2026 ]             │
│                            │
│                            │
│      [ Kaydet ]            │
└────────────────────────────┘
```

---

## Form Alanları

### Görev Adı

* Zorunlu alan
* Metin girişi

### Tahmini Süre

* Sayısal giriş
* Dakika cinsinden

### Öncelik

Seçenekler:

* Düşük
* Orta
* Yüksek

### Tarih

Görevin hangi gün yapılmasının planlandığını belirtir.

---

## Kaydet İşlemi

Görev veritabanına kaydedilir.

Kullanıcı ana ekrana yönlendirilir.

---

# 5. SCREEN 3 – TASK DETAIL SCREEN

## Amaç

Seçilen görevin detaylarını görüntülemek ve düzenlemek.

---

## Wireframe

```text
┌────────────────────────────┐
│ ← Görev Detayı             │
├────────────────────────────┤
│ Görev Adı                  │
│ [ Mobil Programlama ]      │
│                            │
│ Süre                       │
│ [ 120 ]                    │
│                            │
│ Öncelik                    │
│ [ Yüksek ▼ ]               │
│                            │
│ Tarih                      │
│ [ 12/06/2026 ]             │
│                            │
│ [ Güncelle ]               │
│                            │
│ [ Sil ]                    │
└────────────────────────────┘
```

---

## Güncelle İşlemi

Görev bilgilerini veritabanında günceller.

---

## Sil İşlemi

Silme işleminden önce kullanıcıdan onay alınır.

Örnek:

```text
Bu görevi silmek istediğinize emin misiniz?
```

---

# 6. SCREEN 4 – SETTINGS SCREEN

## Amaç

Uygulama ayarlarını ve veri yönetimini sağlamak.

---

## Wireframe

```text
┌────────────────────────────┐
│ Ayarlar                    │
├────────────────────────────┤
│ Veri Yönetimi              │
│                            │
│ JSON Dışa Aktar            │
│ JSON İçe Aktar             │
│                            │
├────────────────────────────┤
│ Uygulama Hakkında          │
│                            │
│ Smart Day Planner          │
│ Versiyon 1.0              │
│                            │
│ Geliştirici Bilgileri      │
└────────────────────────────┘
```

---

# 7. Gelecekteki AI Entegrasyonu İçin Hazırlık

Mevcut tasarım, ileride eklenecek yapay zeka modülü için uygun şekilde hazırlanmıştır.

İlerleyen sürümlerde:

* Günlük plan oluşturma
* Görev önceliklendirme
* Otomatik yeniden planlama
* Ertelenen görevleri başka günlere taşıma

özellikleri sisteme eklenebilecektir.

Bu geliştirmeler mevcut ekran yapısını değiştirmeden gerçekleştirilebilecektir.

---

# 8. Kullanılabilirlik Hedefleri

* Kullanıcı yeni görev ekleme işlemini 30 saniyeden kısa sürede tamamlayabilmelidir.
* Görev listesi tek ekranda görüntülenebilmelidir.
* Görev düzenleme işlemleri en fazla iki dokunuş ile yapılabilmelidir.
* Kullanıcı uygulamayı öğrenmeden kullanabilecek kadar basit bir arayüzle karşılaşmalıdır.
* Uygulama mobil cihazlarda akıcı ve hızlı çalışmalıdır.
* Arayüz gelecekte eklenecek yeni özelliklere uyum sağlayabilecek şekilde tasarlanmalıdır.
