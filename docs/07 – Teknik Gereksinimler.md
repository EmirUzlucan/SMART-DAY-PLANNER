# 📄 07 – TEKNİK GEREKSİNİMLER

## 🎯 Amaç

Bu doküman, Smart Day Planner uygulamasının geliştirilmesi sırasında kullanılan teknolojileri, kütüphaneleri ve sistem gereksinimlerini açıklamaktadır.

Amaç, projenin teknik altyapısının net ve anlaşılır şekilde sunulmasıdır.

---

# 📱 1. PLATFORM VE DİL

## 🔹 Android Platformu

Uygulama Android işletim sistemi için geliştirilmiştir.

## 🔹 Programlama Dili

* Kotlin

### Neden Kotlin?

* Modern ve güvenli dil yapısı
* Null safety desteği
* Android tarafından resmi olarak desteklenmesi
* Daha az boilerplate code

---

# 🏗️ 2. ANDROID MİMARİ BİLEŞENLERİ

## 🔹 Activity

* Kullanıcı arayüzünü yönetir
* Ekran geçişlerini kontrol eder

Kullanılan Activity’ler:

* HomeActivity
* AddTaskActivity
* TaskDetailActivity

---

## 🔹 ViewModel

* UI verilerini yönetir
* Lifecycle-aware yapı sağlar
* Veri kaybını önler

---

## 🔹 LiveData

* Veri değişimlerini UI’a otomatik yansıtır
* Reactive programlama sağlar

---

## 🔹 Repository Pattern

* Veri kaynaklarını soyutlar
* UI ve database arasındaki bağı koparır
* Kod tekrarını azaltır

---

# 💾 3. VERİTABANI TEKNOLOJİSİ

## 🔹 Room Database

Android Jetpack bileşeni olarak kullanılmıştır.

### Avantajları:

* SQLite üzerine abstraction sağlar
* Compile-time query kontrolü
* Kolay veri yönetimi

---

## 🔹 SQLite (arka plan)

Room, SQLite üzerine inşa edilmiştir ancak doğrudan SQL kullanımını azaltır.

---

# ⚙️ 4. ASENKRON İŞLEMLER

## 🔹 Kotlin Coroutines

* Background işlemleri yönetir
* UI thread bloklanmasını engeller

Kullanım alanları:

* Veri ekleme
* Veri güncelleme
* Veri silme

---

# 🧩 5. UI TEKNOLOJİLERİ

## 🔹 RecyclerView

* Görev listesini performanslı şekilde göstermek için kullanılmıştır

## 🔹 Material Design Components

* Modern ve kullanıcı dostu arayüz sağlamak için kullanılmıştır

Örnek bileşenler:

* Floating Action Button
* CardView
* TextInputLayout

---

# 📦 6. PROJE BAĞIMLILIKLARI (DEPENDENCIES)

## 🔹 Temel Android Kütüphaneleri

* androidx.core
* androidx.appcompat
* material components

## 🔹 Architecture Components

* lifecycle-viewmodel
* lifecycle-livedata
* room-runtime
* room-ktx

## 🔹 Kotlin Support

* kotlin-stdlib
* coroutines-core
* coroutines-android

---

# 📂 7. DOSYA YÖNETİMİ

* Local storage kullanılmıştır
* Room Database ile kalıcı veri saklanmaktadır
* JSON export/import için Gson kullanılabilir (opsiyonel)

---

# 🚫 8. HARİCİ SERVİSLER

Phase 1 kapsamında:

* Backend servisi kullanılmamıştır
* API entegrasyonu yapılmamıştır

Bu tercih, uygulamanın offline çalışmasını ve daha stabil olmasını sağlamaktadır.

---

# 📈 9. PERFORMANS HEDEFLERİ

* UI thread bloklanmaz
* Veri işlemleri background thread’de yapılır
* Listeleme işlemleri RecyclerView ile optimize edilmiştir
* Room sayesinde hızlı veri erişimi sağlanır

---

# 🚀 10. GELECEK TEKNOLOJİ UYUMLULUĞU

Bu teknik yapı ileride şu teknolojilere uyumludur:

* GPT API entegrasyonu (Retrofit ile)
* Firebase Firestore geçişi
* Notification system (WorkManager)
* Cloud sync sistemi

---

# 📌 SONUÇ

Bu teknolojik yapı sayesinde uygulama:

* Modern Android standartlarına uygundur
* Genişletilebilir mimariye sahiptir
* Performanslı ve stabil çalışır
* Gelecek özellikler için hazır altyapı sunar
