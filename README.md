# RoomWordSample

[Android Room with a View - Kotlin codelab](https://developer.android.com/codelabs/android-room-with-a-view-kotlin)

## Rangkuman yang Dipelajari

- __LiveData__: sebuah kelas pemegang data yang bisa diamati perubahannya. Selalu memiliki versi terbaru dari data dan memberitahu observer ketika ada data yang berubah. Bersifat lifecycle-aware sehingga dapat menangani data sesuai lifecycle UI component yang menjadi observer
- __ViewMode__: Bertindak sebagai penghubung antara repository dengan UI component. UI component tidak perlu lagi mengetahui asal dari data. Selain itu, ViewModel dapat bertahan jika UI component mengalami recreation.
- __Repository__: Kelas yang dibuat dengan tujuan utama mengatur berbagai kelas data source/sumber data.
- __Entity__: Kelas tyang dianotasikan dengan ini akan mendefinisikan tabel pada database yang dibuat dengan Room.
- __Room DB__: Abstraksi di atas library SQLite untuk menyederhanakan dan memudahkan pengerjaan database. Menggunakan DAO untuk melakukan query ke SQLite.
- __DAO__: Data Access Object, pemetaan dari query SQL ke fungsi/method. Ketika menggunakan ini, cukup panggil method yang sudah diberikan anotasinya dan Room yang akan mengaturnya di SQLite.
