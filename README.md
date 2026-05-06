📦 Kargo Otomasyonu Yönetim Sistemi

Java ile geliştirilmiş bu masaüstü uygulama, kargo süreçlerini merkezi bir panel üzerinden yönetmeyi sağlar.
Gönderici ve alıcı bilgileri, kargo durumu ve kurye atamaları tek ekranda kontrol edilir.

🚀 Temel Özellikler:


📌 Kargo oluşturma (Kargo Kaydet)


🔄 Durum güncelleme (Durum Güncelle)


❌ Kargo silme


🔍 Kargo No ile arama


👤 Gönderici & alıcı bilgisi yönetimi


🚚 Kurye atama sistemi


📊 Kayıtlı kargoların listelenmesi


Kullanılan Teknolojiler:
☕ Java
🎨 Java Swing (masaüstü arayüz)
🔗 JDBC (veri işlemleri)
🗄️ (Varsa ekle) SQLite / MySQL


⚙️ Nasıl Çalışır? (Gerçek Akış):


1.Kullanıcı kargo numarası ve bilgileri girer


2.Gönderici ve alıcı bilgileri doldurulur


3.Kurye ve başlangıç durumu seçilir


4.Kargo Kaydet ile sistem kaydı oluşturur


5.Sağ panelde tüm kargolar listelenir


6.Seçilen kargo:


   -Güncellenebilir

   
   -Silinebilir

   
   -Arama ile bulunabilir

   

🏗️ Teknik Yapı:

Proje, klasik katmanlı yapı mantığıyla geliştirilmiştir:

-Model → Kargo, Müşteri, Kurye veri yapıları


-Service / Logic → İş kuralları (durum güncelleme vs.)


-UI (Swing) → Kullanıcı etkileşimi


-Database Layer → Veri yönetimi

Bu yapı sayesinde proje:

- Genişletilebilir
- Bakımı kolay
- Modüler
