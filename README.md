
# Tugas Besar 1 IF2123 Aljabar Linear dan Geometri
> Sistem Persamaan Linear, Matriks, dan Aplikasinya

Tujuan dari dibuatnya tugas ini adalah untuk merealisasikan teori-teori matriks yang telah diajarkan di kelas. 


## Command

### Compiling Library
```
javac -d bin/class src/**/*.java && jar cfm bin/Matrix.jar ./src/Manifest.txt -C ./bin/class .
```

### Running with Library
Misalkan ingin menjalankan `Test.java` pada folder `test` dengan menggunakan library yang dibuat, maka kita bisa melakukan command berikut
```
javac -cp bin/Matrix.jar -d bin test/Test.java && java -cp bin/Matrix.jar:bin Test
```
*) Ubah `:` dengan `;` apabila menggunakan windows

### Running Program
```
java -jar ./bin/Matrix.jar  
```

## Features

Program ini dapat melakukan beberapa operasi matriks sederhana seperti:
* Menyelesaikan Sistem Persamaan Linier
* Mencari Determinan dan Balikan Matriks
* Interpolasi Polinom
* Regresi Linier Berganda
* Interpolasi Bicubic Spline
* Resize Ukuran Gambar

## Anggota Kelompok

| Nama                         | NIM      |
|------------------------------|----------|
| M. Hanief Fatkhan Nashrullah | 13522100 |
| Muhammad Atpur Rafif         | 13522086 |
| Indraswara Galih Jayanegara  | 13522119 |


