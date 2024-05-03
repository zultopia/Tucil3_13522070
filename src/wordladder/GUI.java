import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import java.util.*;
import java.io.*;

public class GUI extends Application {

    private ComboBox<String> navigationDropdown;

    @Override
    public void start(Stage primaryStage) {
        navigationDropdown = createNavigationDropdown(primaryStage);  
    
        Scene homeScene = createHomeScene();  
        Scene gameScene = createGameScene();
    
        setupNavigationLogic(primaryStage, homeScene, gameScene);
    
        primaryStage.setTitle("WordLadder");
        primaryStage.setScene(homeScene);  
        primaryStage.show();  
    }

    private ComboBox<String> createNavigationDropdown(Stage primaryStage) {
        ComboBox<String> dropdown = new ComboBox<>();
        dropdown.getItems().addAll("Home", "Game", "How to Use", "Algorithm", "About Me");
        dropdown.setPromptText("Navigate");
        return dropdown;
    }

    private Scene createHomeScene() {
        BorderPane homeLayout = new BorderPane(); 
        homeLayout.setPadding(new Insets(20, 20, 20, 20));
    
        homeLayout.setTop(navigationDropdown);  
        homeLayout.setCenter(new Label("Welcome to WordLadder!"));  
    
        applyGradientBackground(homeLayout);  
        return new Scene(homeLayout, 600, 400); 
    }

    private void setupNavigationLogic(Stage primaryStage, Scene homeScene, Scene gameScene) {
        navigationDropdown.setOnAction(event -> {
            String selected = navigationDropdown.getValue();  
            switch (selected) {
                case "Home":
                    primaryStage.setScene(homeScene);  
                    break;
                case "Game":
                    primaryStage.setScene(gameScene); 
                    break;
                case "How to Use":
                    Scene howToUseScene = createHowToUseScene();  
                    primaryStage.setScene(howToUseScene);  
                    break;
                case "Algorithm":
                    Scene algorithmScene = createAlgorithmScene();  
                    primaryStage.setScene(algorithmScene);  
                    break;
                case "About Me":
                    Scene aboutMeScene = createAboutMeScene();  
                    primaryStage.setScene(aboutMeScene);  
                    break;
            }
        });
    }

    private Scene createGameScene() {
        VBox gameLayout = new VBox(10);
        gameLayout.setPadding(new Insets(20, 20, 20, 20));

        TextField startWordField = new TextField();
        startWordField.setPromptText("Enter start word");

        TextField endWordField = new TextField();
        endWordField.setPromptText("Enter end word");

        ComboBox<String> algorithmDropdown = new ComboBox<>();
        algorithmDropdown.getItems().addAll("UCS", "GBFS", "A*");

        Button searchButton = new Button("Search");
        TextArea resultBox = new TextArea();
        resultBox.setEditable(false);

        searchButton.setOnAction(event -> {
            String startWord = startWordField.getText().trim();
            String endWord = endWordField.getText().trim();
            String selectedAlgorithm = algorithmDropdown.getValue();

            if (startWord.isEmpty() || endWord.isEmpty()) {
                resultBox.setText("Please enter both start and end words.");
                return;
            }

            if (startWord.length() != endWord.length()) {
                resultBox.setText("Start word and end word must be of the same length.");
                return;
            }

            Set<String> dictionary = loadDictionary(startWord.length());

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
                    UCS.Result ucsResult = ucs.findPath(startWord.toLowerCase(), endWord.toLowerCase());
                    path = ucsResult.getPath();
                    nodesVisited = ucsResult.getNodesVisited();
                    break;
                case "GBFS":
                    GBFS gbfs = new GBFS(dictionary);
                    GBFS.Result gbfsResult = gbfs.findPath(startWord.toLowerCase(), endWord.toLowerCase());
                    path = gbfsResult.getPath();
                    nodesVisited = gbfsResult.getNodesVisited();
                    break;
                case "A*":
                    AStar astar = new AStar(dictionary);
                    AStar.Result astarResult = astar.findPath(startWord.toLowerCase(), endWord.toLowerCase());
                    path = astarResult.getPath();
                    nodesVisited = astarResult.getNodesVisited();
                    break;
                default:
                    resultBox.setText("Invalid algorithm choice.");
                    return;
            }

            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            if (path == null || path.isEmpty()) {
                resultBox.setText("No path solution found.");
                return;
            }

            StringBuilder result = new StringBuilder();
            result.append("Path: ").append(path).append("\n");
            result.append("Nodes visited: ").append(nodesVisited).append("\n");
            result.append("Execution time (ms): ").append(executionTime).append("\n");

            resultBox.setText(result.toString());
        });

        gameLayout.getChildren().addAll(
            new Label("Start Word:"), startWordField,
            new Label("End Word:"), endWordField,
            new Label("Algorithm:"), algorithmDropdown,
            searchButton,
            new Label("Results:"), resultBox
        );

        applyGradientBackground(gameLayout);
        return new Scene(gameLayout, 600, 400);
    }

    private Set<String> loadDictionary(int wordLength) {
        String filePath = "dictionary/dictionary-" + wordLength + "-letter-words.txt"; 
        Set<String> dictionary = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                dictionary.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            System.err.println("Error loading dictionary: " + e.getMessage());
        }

        return dictionary;
    }

    private Scene createHowToUseScene() {
        VBox howToUseLayout = new VBox(10);
        howToUseLayout.setPadding(new Insets(20, 20, 20, 20));

        Label instructionLabel = new Label("How to use the game:\n- Enter start word\n- Enter end word\n- Choose an algorithm\n- Click search.");
        howToUseLayout.getChildren().add(instructionLabel);

        applyGradientBackground(howToUseLayout);
        return new Scene(howToUseLayout, 600, 400);
    }

    private Scene createAlgorithmScene() {
        VBox algorithmLayout = new VBox(10);
        algorithmLayout.setPadding(new Insets(20, 20, 20, 20));

        Label explanationLabel = new Label("Explanation about UCS, GBFS, and A* algorithms.");
        algorithmLayout.getChildren().add(explanationLabel);

        applyGradientBackground(algorithmLayout);
        return new Scene(algorithmLayout, 600, 400);
    }

    private Scene createAboutMeScene() {
        VBox aboutMeLayout = new VBox(10);
        aboutMeLayout.setPadding(new Insets(20, 20, 20, 20));

        Label aboutMeLabel = new Label("About the author: This game was created by Azul Suhada.");
        aboutMeLayout.getChildren().add(aboutMeLabel);

        applyGradientBackground(aboutMeLayout);
        return new Scene(aboutMeLayout, 600, 400);
    }

    private void applyGradientBackground(Pane pane) {
        LinearGradient gradient = new LinearGradient(
            0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.LIGHTBLUE),
            new Stop(1, Color.WHITE)
        );
        pane.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public static void main(String[] args) {
        launch(args);
    }
}