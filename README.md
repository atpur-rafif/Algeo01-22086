
## Command

### Compiling Library
```
javac -d bin/class src/**/*.java && jar cf bin/Matrix.jar -C ./bin/class .
```

### Running with Library
Misalkan ingin menjalankan `Test.java` pada folder `test` dengan menggunakan library yang dibuat, maka kita bisa melakukan command berikut
```
javac -cp bin/Matrix.jar -d bin test/Test.java && java -cp bin/Matrix.jar:bin Test
```
*) Ubah `:` dengan `;` apabila menggunakan windows