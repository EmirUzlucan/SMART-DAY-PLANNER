# 📄 05 – VERİ MODELİ VE VERİTABANI TASARIMI (ROOM DB)

## 🎯 Amaç

Bu doküman, Smart Day Planner uygulamasında kullanılacak veri yapısını ve yerel veritabanı (Room Database) mimarisini tanımlar.

Amaç, görev verilerinin kalıcı, güvenli ve düzenli şekilde saklanmasını sağlamaktır.

---

# 🧱 1. VERİ MODELİ (ENTITY TASARIMI)

## 📦 Task Entity

Uygulamanın temel veri modeli “Task” nesnesidir.

```kotlin id="task_entity"
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,

    val isDone: Boolean = false,

    val date: String,

    val priority: Int? = null,

    val duration: Int? = null
)
```

---

## 📌 Alan Açıklamaları

### 🔹 id

* Her görevin benzersiz kimliği
* Auto increment

### 🔹 title

* Görev başlığı
* Zorunlu alan

### 🔹 isDone

* Görevin tamamlanma durumu
* Default: false

### 🔹 date

* Görevin ait olduğu gün
* Format: `YYYY-MM-DD`

### 🔹 priority

* Görev önceliği
* 1 = Low, 2 = Medium, 3 = High

### 🔹 duration

* Görevin tahmini süresi (dakika)

---

# 🗄️ 2. DAO (DATA ACCESS OBJECT)

## 📌 TaskDao

Veritabanı işlemlerini yöneten katman.

```kotlin id="task_dao"
@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks WHERE date = :date ORDER BY id DESC")
    fun getTasksByDate(date: String): LiveData<List<Task>>

    @Insert
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("UPDATE tasks SET isDone = :isDone WHERE id = :taskId")
    suspend fun updateTaskStatus(taskId: Int, isDone: Boolean)
}
```

---

## 📌 DAO Açıklamaları

* getTasksByDate → günlük listeleme
* insertTask → yeni görev ekleme
* updateTask → görev güncelleme
* deleteTask → görev silme
* updateTaskStatus → checkbox işlemi için optimize method

---

# 🏗️ 3. ROOM DATABASE

## 📦 AppDatabase

```kotlin id="app_db"
@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}
```

---

## 📌 Singleton Database Instance

```kotlin id="db_instance"
object DatabaseInstance {

    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "smart_day_planner_db"
            ).build()

            INSTANCE = instance
            instance
        }
    }
}
```

---

# 🔁 4. VERİ AKIŞ MİMARİSİ (DATA FLOW)

Uygulamada veri akışı şu şekilde gerçekleşir:

```text id="flow1"
UI (Activity / Fragment)
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

## 📌 Katman Açıklamaları

### 🔹 UI Layer

* Kullanıcı etkileşimi
* ViewModel çağırır

### 🔹 ViewModel

* UI ile veri katmanı arasında köprü
* LiveData yönetir

### 🔹 Repository

* Veri kaynaklarını yönetir
* DAO çağrılarını soyutlar

### 🔹 DAO

* SQL işlemlerini yapar

### 🔹 Database

* Kalıcı veri saklama

---

# 📊 5. VERİ YAPISI DAVRANIŞI

## 🧩 Günlük veri mantığı

* Her görev bir “date” alanına sahiptir
* Aynı günün görevleri filtrelenerek çekilir

```sql
WHERE date = "2026-06-04"
```

---

## 🧩 Görev durumu yönetimi

* isDone = false → aktif görev
* isDone = true → tamamlanmış görev

---

# ⚙️ 6. PERFORMANS KARARLARI

* LiveData kullanımı ile otomatik UI güncelleme
* Suspend functions ile main thread bloklanmaz
* Query optimizasyonu date bazlı yapılır

---

# 🚀 7. GELECEK UYUMLULUK (AI READY STRUCTURE)

Bu veri modeli ileride AI entegrasyonu için uygundur:

* title → AI input
* duration → scheduling input
* priority → weighting factor
* date → time blocking

Bu yapı Phase 2 (AI Planlama) için genişletilebilir şekilde tasarlanmıştır.

---

# 📌 SONUÇ

Bu veri modeli sayesinde:

* Görevler kalıcı olarak saklanır
* Günlük filtreleme yapılabilir
* UI ile senkron çalışan dinamik yapı oluşur
* AI entegrasyonu için uygun temel hazırlanır
