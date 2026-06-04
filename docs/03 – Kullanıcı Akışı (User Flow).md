# 📄 03 – KULLANICI AKIŞI (USER FLOW)

## 🎯 Amaç

Bu doküman, kullanıcının uygulamayı açtığı andan görevlerini yönetmeye başlayana kadar izleyeceği adımları tanımlamaktadır.

Kullanıcı akışının amacı, uygulamanın kullanım senaryolarını netleştirmek ve ekranlar arasındaki geçişleri belirlemektir.

---

# 1. Uygulama Başlatma Akışı

### Senaryo

Kullanıcı uygulamayı açar ve mevcut görevlerini görüntüler.

### Akış

1. Kullanıcı uygulamayı açar.
2. Ana ekran yüklenir.
3. Veritabanındaki görevler okunur.
4. Görev listesi kullanıcıya gösterilir.
5. Kullanıcı görevlerini görüntüler.

### Sonuç

Kullanıcı mevcut görevlerini görebilir ve yönetmeye başlayabilir.

---

# 2. Yeni Görev Oluşturma Akışı

### Senaryo

Kullanıcı yeni bir görev eklemek ister.

### Akış

1. Kullanıcı ana ekrandaki "+" butonuna basar.
2. Görev Ekle ekranı açılır.
3. Kullanıcı görev bilgilerini girer.
4. Kaydet butonuna basar.
5. Görev doğrulanır.
6. Görev veritabanına kaydedilir.
7. Kullanıcı ana ekrana yönlendirilir.
8. Yeni görev görev listesinde görüntülenir.

### Sonuç

Yeni görev sisteme eklenmiş olur.

---

# 3. Görev Düzenleme Akışı

### Senaryo

Kullanıcı mevcut bir görevin bilgilerini değiştirmek ister.

### Akış

1. Kullanıcı görev listesinde bir göreve tıklar.
2. Görev Detay ekranı açılır.
3. Kullanıcı gerekli alanları günceller.
4. Güncelle butonuna basar.
5. Yeni bilgiler veritabanına kaydedilir.
6. Ana ekrana dönülür.
7. Güncellenmiş görev görüntülenir.

### Sonuç

Görev bilgileri güncellenmiş olur.

---

# 4. Görev Tamamlama Akışı

### Senaryo

Kullanıcı bir görevi tamamlar.

### Akış

1. Kullanıcı görev yanındaki tamamlandı seçeneğini işaretler.
2. Sistem görevin durumunu günceller.
3. Veritabanındaki kayıt güncellenir.
4. Görev tamamlanmış olarak gösterilir.

### Sonuç

Görev tamamlandı durumuna geçer.

---

# 5. Görev Silme Akışı

### Senaryo

Kullanıcı artık gerekli olmayan bir görevi kaldırmak ister.

### Akış

1. Kullanıcı silme işlemini başlatır.
2. Sistem silme onayı ister.
3. Kullanıcı işlemi onaylar.
4. Görev veritabanından kaldırılır.
5. Liste güncellenir.

### Sonuç

Görev sistemden silinmiş olur.

---

# 6. Veri Saklama Akışı

### Senaryo

Kullanıcı uygulamayı kapatır ve daha sonra tekrar açar.

### Akış

1. Kullanıcı uygulamayı kapatır.
2. Tüm görevler Room Database içerisinde saklanır.
3. Kullanıcı uygulamayı yeniden açar.
4. Veritabanındaki kayıtlar yüklenir.
5. Görev listesi yeniden görüntülenir.

### Sonuç

Kullanıcı verileri kaybolmadan uygulamayı kullanmaya devam eder.

---

# Genel Kullanıcı Akışı

```text
Uygulamayı Aç
      │
      ▼
Ana Ekran
      │
 ┌────┼────┐
 ▼    ▼    ▼
Ekle Düzenle Sil
 │     │     │
 └─────┼─────┘
       ▼
 Görev Listesi
       │
       ▼
 Tamamlandı İşaretle
       │
       ▼
 Veritabanına Kaydet
```

---

# Kullanıcı Hedefleri

Uygulamanın kullanıcıya aşağıdaki hedefleri gerçekleştirmede yardımcı olması amaçlanmaktadır:

* Günlük görevleri kayıt altına almak
* Görevleri takip etmek
* Görevleri düzenlemek
* Görevlerin durumunu görüntülemek
* Planlı çalışmayı kolaylaştırmak

Bu kullanıcı akışları, uygulamanın ilk sürümündeki tüm temel kullanım senaryolarını kapsamaktadır.
