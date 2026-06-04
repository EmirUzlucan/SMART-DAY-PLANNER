# 📄 06 – UYGULAMA MİMARİSİ (MVVM STRUCTURE)

## 🎯 Amaç

Bu doküman, Smart Day Planner uygulamasında kullanılan yazılım mimarisini tanımlar.

Amaç, kodun sürdürülebilir, modüler ve ölçeklenebilir olmasını sağlamaktır.

---

# 🧱 1. MİMARİ YAKLAŞIM: MVVM

Uygulama **MVVM (Model - View - ViewModel)** mimarisi kullanılarak geliştirilmiştir.

Bu yapı, UI ile veri katmanını birbirinden ayırarak daha temiz ve yönetilebilir bir kod yapısı sağlar.

---

## 📌 MVVM Katmanları

### 🔹 Model

* Veri yapıları (Task entity)
* Room Database yapısı
* Repository katmanı

### 🔹 View

* Activity / Fragment
* UI bileşenleri
* Kullanıcı etkileşimleri

### 🔹 ViewModel

* UI ile veri katmanı arasında köprü
* LiveData yönetimi
* İş mantığı

---

# 🔁 2. GENEL VERİ AKIŞI

```text id="mvvm_flow"
View (UI)
   ↓
ViewModel
   ↓
Repository
   ↓
Room DAO
   ↓
SQLite Database
```

---

# 📁 3. PROJE KLASÖR YAPISI

Uygulama aşağıdaki şekilde organize edilmiştir:

```text id="project_structure"
app/
 ├── data/
 │    ├── local/
 │    │     ├── TaskDao.kt
 │    │     ├── AppDatabase.kt
 │    │
 │    ├── repository/
 │          ├── TaskRepository.kt
 │
 ├── model/
 │     ├── Task.kt
 │
 ├── ui/
 │    ├── home/
 │    │     ├── HomeActivity.kt
 │    │
 │    ├── addtask/
 │    │     ├── AddTaskActivity.kt
 │    │
 │    ├── detail/
 │          ├── TaskDetailActivity.kt
 │
 ├── viewmodel/
 │    ├── TaskViewModel.kt
 │
 ├── utils/
 │    ├── DateUtils.kt
 │    ├── Constants.kt
```

---

# 🧠 4. VIEWMODEL YAPISI

## 📦 TaskViewModel

ViewModel, UI katmanının tüm veri ihtiyaçlarını yönetir.

```kotlin id="viewmodel"
class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    fun getTasksByDate(date: String): LiveData<List<Task>> {
        return repository.getTasksByDate(date)
    }

    fun insertTask(task: Task) = viewModelScope.launch {
        repository.insertTask(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        repository.updateTask(task)
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        repository.deleteTask(task)
    }

    fun updateStatus(taskId: Int, isDone: Boolean) = viewModelScope.launch {
        repository.updateTaskStatus(taskId, isDone)
    }
}
```

---

## 📌 ViewModel Görevleri

* UI verisini yönetmek
* Repository çağrılarını yapmak
* Coroutine ile async işlemleri yürütmek
* UI güncellemelerini LiveData üzerinden sağlamak

---

# 🏗️ 5. REPOSITORY KATMANI

Repository, veri kaynaklarını soyutlar ve ViewModel’e temiz bir API sunar.

```kotlin id="repository"
class TaskRepository(private val taskDao: TaskDao) {

    fun getTasksByDate(date: String): LiveData<List<Task>> {
        return taskDao.getTasksByDate(date)
    }

    suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    suspend fun updateTaskStatus(taskId: Int, isDone: Boolean) {
        taskDao.updateTaskStatus(taskId, isDone)
    }
}
```

---

## 📌 Repository Görevleri

* Veri kaynaklarını yönetmek
* DAO ile ViewModel arasındaki bağı kurmak
* Gerekirse ileride API entegrasyonunu desteklemek

---

# 🧩 6. UI KATMANI (VIEW)

## 📱 Activity Yapısı

Uygulamada 3 temel Activity bulunur:

* HomeActivity → görev listesi
* AddTaskActivity → görev ekleme
* TaskDetailActivity → görev düzenleme

---

## 📌 UI – ViewModel Bağlantısı

UI doğrudan database ile iletişim kurmaz.

Sadece ViewModel çağrılır:

```kotlin
viewModel.getTasksByDate(today).observe(this) { tasks ->
    adapter.submitList(tasks)
}
```

---

# ⚙️ 7. TEKNİK TASARIM KARARLARI

* MVVM pattern kullanımı
* LiveData ile reactive UI
* Coroutine ile background işlemler
* Repository pattern ile veri soyutlama
* Room database ile local storage

---

# 🚀 8. GELECEK GENİŞLETME UYUMLULUĞU

Bu mimari ileride şu özelliklere uygundur:

* GPT API entegrasyonu (ViewModel üzerinden)
* Cloud database ekleme (Repository genişletilir)
* JSON sync sistemi
* Notification system

---

# 📌 SONUÇ

Bu mimari sayesinde:

* Kod modüler hale gelir
* UI ve veri katmanı ayrılır
* Bakım kolaylaşır
* Yeni özellikler eklemek kolaylaşır
* Proje profesyonel yazılım standardına yaklaşır
