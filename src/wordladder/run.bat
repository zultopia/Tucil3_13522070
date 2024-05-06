@echo off
REM Atur module-path ke lokasi JavaFX
set MODULE_PATH=".\javafx-sdk-22.0.1\lib"

REM Kompilasi semua file Java
javac --module-path %MODULE_PATH% --add-modules javafx.controls,javafx.fxml -d .\bin src\wordladder\*.java

REM Jalankan program GUI
java --module-path %MODULE_PATH% --add-modules javafx.controls,javafx.fxml -cp .\bin GUI