#!/bin/bash
# Module-path diatur ke lokasi JavaFX
MODULE_PATH="./javafx-sdk-22.0.1/lib"
# Jalankan program GUI
javac --module-path ./javafx-sdk-22.0.1/lib --add-modules javafx.controls,javafx.fxml AStar.java GBFS.java UCS.java WordDictionary.java Node.java Utility.java Result.java GUI.java
java --module-path ./javafx-sdk-22.0.1/lib --add-modules javafx.controls,javafx.fxml GUI

# javac AStar.java GBFS.java UCS.java WordDictionary.java Node.java Utility.java Result.java WordAzulSwing.java