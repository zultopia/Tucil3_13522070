import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox mainLayout = new VBox(20);  
        mainLayout.setPadding(new Insets(20, 20, 20, 20));  
        mainLayout.setAlignment(Pos.TOP_CENTER); 

        // Header
        ImageView headerImage = new ImageView(new Image("images/wordazul.png"));
        headerImage.setFitWidth(400); 
        headerImage.setPreserveRatio(true);  
        mainLayout.getChildren().add(headerImage);  

        Label welcomeLabel = new Label("Selamat Datang di WordAzul!");
        welcomeLabel.setFont(Font.font("Monospace", FontWeight.BOLD, 24));  
        mainLayout.getChildren().add(welcomeLabel);

        // Deskripsi WordAzul
        Label descriptionLabel = new Label(
            "WordAzul adalah platform yang dapat memberikan solusi untuk teka-teki word ladder. Di Word Ladder, kalian ditantang untuk menghubungkan dua kata dengan mengubah\n"
            + "satu huruf pada satu waktu, menciptakan rangkaian kata yang menarik dan logis. Setiap perubahan membawa kalian lebih dekat ke tujuan, menawarkan pengalaman yang\n"
            + "mendidik dan menghibur. Dengan berbagai tingkat kesulitan, WordAzul cocok untuk membantu kalian yang mengalami kesulitan. Antarmuka yang menarik dan ramah pengguna\n"
            + "membuat platform ini menjadi tempat yang sempurna untuk mengasah keterampilan kosa kata kalian sambil bersenang-senang.\n"
        );
        descriptionLabel.setPrefWidth(1500);  
        descriptionLabel.setFont(Font.font("Monospace", FontWeight.SEMI_BOLD, 14));
        mainLayout.getChildren().add(descriptionLabel);

        // How To Use
        Label howToUseLabel = new Label("How to Use");
        howToUseLabel.setFont(Font.font("Monospace", FontWeight.BOLD, 20));  
        mainLayout.getChildren().add(howToUseLabel);

        Label stepsLabel = new Label(
            "- Masukkan start word dan end word yang diinginkan.\n"
            + "- Pilih algoritma route planning yang ingin digunakan.\n"
            + "- Klik tombol search, lalu hasilnya akan segera muncul di bagian bawah."
        );
        mainLayout.getChildren().add(stepsLabel);
        stepsLabel.setFont(Font.font("Monospace", FontWeight.SEMI_BOLD, 14));

        // Main Game Solver
        HBox inputLayout = new HBox(20); 
        inputLayout.setAlignment(Pos.CENTER);  

        ImageView gameImage = new ImageView(new Image("images/bossteam.png"));
        gameImage.setFitWidth(250);  
        gameImage.setPreserveRatio(true);  
        mainLayout.getChildren().add(gameImage);  

        Label startWordLabel = new Label("Start Word:");
        startWordLabel.setFont(Font.font("Monospace", FontWeight.BOLD, 16)); 

        TextField startWordField = new TextField();
        startWordField.setPromptText("Enter start word"); 
        startWordField.setPrefWidth(200);  
        startWordField.setFont(Font.font("Monospace", FontWeight.BOLD, 14));

        Label endWordLabel = new Label("End Word:");
        endWordLabel.setFont(Font.font("Monospace", FontWeight.BOLD, 16));  

        TextField endWordField = new TextField();
        endWordField.setPromptText("Enter end word"); 
        endWordField.setPrefWidth(200);  
        endWordField.setFont(Font.font("Monospace", FontWeight.BOLD, 14));

        inputLayout.getChildren().addAll(startWordLabel, startWordField, endWordLabel, endWordField);
        mainLayout.getChildren().add(inputLayout);

        HBox algorithmLayout = new HBox(20); 
        algorithmLayout.setAlignment(Pos.CENTER); 

        Label algorithmLabel = new Label("Choose Algorithm: ");
        algorithmLabel.setFont(Font.font("Monospace", FontWeight.BOLD, 16));  

        ComboBox<String> algorithmDropdown = new ComboBox<>();
        algorithmDropdown.getItems().addAll("UCS", "GBFS", "A*"); 

        algorithmLayout.getChildren().addAll(algorithmLabel, algorithmDropdown);
        mainLayout.getChildren().add(algorithmLayout);

        Button searchButton = new Button("Search");
        searchButton.setFont(Font.font("Monospace", FontWeight.BOLD, 16));  
        mainLayout.getChildren().add(searchButton);

        TextArea resultBox = new TextArea();  
        resultBox.setEditable(false);  
        resultBox.setPrefHeight(200); 
        resultBox.setWrapText(true);  
        resultBox.setFont(Font.font("Monospace",  FontWeight.BOLD, 14));
        mainLayout.getChildren().add(resultBox);

        searchButton.setOnAction(event -> {
            String startWord = startWordField.getText().trim();
            String endWord = endWordField.getText().trim();
            String selectedAlgorithm = algorithmDropdown.getValue();  

            if (selectedAlgorithm == null) { 
                resultBox.setText("No algorithm selected.");
                return;
            }

            if (startWord.isEmpty() || endWord.isEmpty()) {
                resultBox.setText("Enter the start word and the end word!");
                return;
            }

            if (startWord.length() != endWord.length()) {
                resultBox.setText("Start word and end word must be of the same length.");
                return;
            }

            if (startWord.equalsIgnoreCase(endWord)) {  
                resultBox.setText("Start word and end word are the same: " + startWord);
                return;  
            }

            Set<String> dictionary = WordDictionary.loadDictionary(); 

            if (!dictionary.contains(startWord.toLowerCase()) || !dictionary.contains(endWord.toLowerCase())) {
                resultBox.setText("Start word and/or end word not in dictionary.");
                return;
            }

            List<String> path = null;  
            int nodesVisited = 0;  
            long startTime = System.currentTimeMillis();  

            switch (selectedAlgorithm) {
                case "UCS":
                    UCS ucs = new UCS(dictionary);
                    Result ucsResult = ucs.findPath(startWord.toLowerCase(), endWord.toLowerCase());
                    path = ucsResult.getPath(); 
                    nodesVisited = ucsResult.getNodesVisited();  
                    break;
                case "GBFS":
                    GBFS gbfs = new GBFS(dictionary);
                    Result gbfsResult = gbfs.findPath(startWord.toLowerCase(), endWord.toLowerCase());
                    path = gbfsResult.getPath(); 
                    nodesVisited = gbfsResult.getNodesVisited();  
                    break;
                case "A*":
                    AStar astar = new AStar(dictionary);
                    Result astarResult = astar.findPath(startWord.toLowerCase(), endWord.toLowerCase());
                    path = astarResult.getPath(); 
                    nodesVisited = astarResult.getNodesVisited();  
                    break;
                default:
                    resultBox.setText("Invalid Algorithm");
                    return;
            }

            long endTime = System.currentTimeMillis();  
            long executionTime = endTime - startTime;

            StringBuilder result = new StringBuilder();
            result.append("Nodes visited: " + nodesVisited + "\n");
            result.append("Execution time (ms): " + executionTime + "\n");
            if (path == null || path.isEmpty()) {
                result.append("No Path Found!");
                resultBox.setText(result.toString());
                return;
            }

            int steps = path.size() - 1;
            result.append("Steps needed: " + steps + "\n");
            result.append("Path:\n");  
            for (int i = 0; i < path.size(); i++) {
                result.append((i + 1) + ". " + path.get(i) + "\n");  
            }

            resultBox.setText(result.toString());
        });

        // Penjelasan tentang algoritma
        Label algorithmExplanationTitle = new Label("Get to Know the Algorithm");
        algorithmExplanationTitle.setFont(Font.font("Monospace", FontWeight.BOLD, 20));  
        Label algorithmExplanation = new Label(
            "1. UCS: Uniform Cost Search\n"
            + "Uniform Cost Search (UCS) adalah algoritma yang menggunakan pendekatan Breadth-First Search (BFS), namun dengan biaya yang seragam. Algoritma ini mencari jalur \n" 
            + "dengan biaya paling rendah antara titik awal dan titik tujuan. Langkah-Langkah: \n"
            + "- Mulai dari titik awal.\n"
            + "- Telusuri semua tetangga dengan biaya terendah.\n" 
            + "- Pilih node dengan biaya terendah untuk dilanjutkan.\n"
            + "- Ulangi hingga mencapai titik tujuan.\n"
            + "Kelebihan: Menemukan jalur dengan biaya terendah.\n"
            + "Kekurangan: Mungkin lambat pada graf yang besar.\n"
            + "\n 2. GBFS: Greedy Best First Search Search\n"
            + "Greedy Best-First Search menggunakan heuristik untuk menentukan node mana yang akan dijelajahi terlebih dahulu. Algoritma ini tidak memperhitungkan biaya\n" 
            + "keseluruhan, tetapi fokus pada node yang terlihat paling dekat dengan tujuan. Langkah-Langkah: \n"
            + "- Mulai dari titik awal.\n"
            + "- Gunakan heuristik untuk mengevaluasi tetangga.\n" 
            + "- Pilih node dengan nilai heuristik terendah.\n"
            + "- Ulangi hingga mencapai titik tujuan.\n"
            + "Kelebihan: Cepat pada graf yang sederhana.\n"
            + "Kekurangan: Bisa menghasilkan jalur yang tidak optimal.\n"
            + "\n 3. Algoritma A*\n"
            + "A* Search adalah kombinasi UCS dan Greedy Best-First Search. Algoritma ini menggunakan biaya total dari titik awal hingga tujuan, memperhitungkan biaya dan\n" 
            + "heuristik. Langkah-langkah: \n"
            + "- Mulai dari titik awal.\n"
            + "- Gunakan heuristik untuk mengevaluasi tetangga.\n" 
            + "- Pilih node dengan biaya total terendah.\n"
            + "- Ulangi hingga mencapai titik tujuan.\n"
            + "Kelebihan: Biasanya menemukan jalur optimal.\n"
            + "Kekurangan: Bisa lebih lambat dari Greedy Best-First Search.\n"
        );
        algorithmExplanation.setTextAlignment(TextAlignment.LEFT);  
        algorithmExplanation.setFont(Font.font("Monospace", FontWeight.SEMI_BOLD, 14));
        mainLayout.getChildren().add(algorithmExplanationTitle);
        mainLayout.getChildren().add(algorithmExplanation);

        // About Me
        Label aboutMeTitle = new Label("About Me");
        aboutMeTitle.setFont(Font.font("Monospace", FontWeight.BOLD, 20)); 
        mainLayout.getChildren().add(aboutMeTitle);

        ImageView aboutMeImage = new ImageView(new Image("images/azul.png")); 
        aboutMeImage.setFitWidth(200);  
        aboutMeImage.setPreserveRatio(true);  
        mainLayout.getChildren().add(aboutMeImage);

        Label personalInfo = new Label(
            "Nama : Marzuli Suhada M\n"
            + "NIM  : 13522070"
        );
        mainLayout.getChildren().add(personalInfo);
        personalInfo.setFont(Font.font("Monospace", FontWeight.BOLD, 14));

        Label quoteLabel = new Label(
            "\"Computer Science isn't just about machines and code; it's about solving problems and shaping the future\" - Azul"
        );
        quoteLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 14));  
        mainLayout.getChildren().add(quoteLabel);

        applyGradientBackground(mainLayout);

        ScrollPane scrollPane = new ScrollPane(mainLayout);  
        scrollPane.setFitToWidth(true);  

        Scene scene = new Scene(scrollPane, 800, 600);  
        primaryStage.setScene(scene);  
        primaryStage.show();  
    }

    private void applyGradientBackground(Pane pane) {
        LinearGradient gradient = new LinearGradient(
            0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.WHITE),
            new Stop(1, Color.LIGHTBLUE)
        );
        pane.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));  
    }

    public static void main(String[] args) {
        launch(args);
    }
}