import java.util.Scanner;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Main {
    
    // ANSI color codes for terminal output
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";
    private static final String RESET = "\u001B[0m";

    private static void printWordAzul() {
        System.out.println(PURPLE + "__    __ ____ _____  ____   ____ _____  __ __  _    " + RESET);
        System.out.println(PURPLE + "\\ \\/\\/ // () \\| () )| _) \\ / () \\' / /_|  |  || |__ " + RESET);
        System.out.println(PURPLE + " \\_/\\_/ \\____/|_\\_\\|____//__/\\__/\\___/ \\___/ |____|" + RESET);
        System.out.println(); 
    }

    private static void displayLoading() {
        // ASCII frames to simulate battery charging
        String[] frames = {
            BLUE + "[          ]" + RESET,
            BLUE + "[L         ]" + RESET,
            BLUE + "[LO        ]" + RESET,
            BLUE + "[LOA       ]" + RESET,
            BLUE + "[LOAD      ]" + RESET,
            BLUE + "[LOADI     ]" + RESET,
            BLUE + "[LOADIN    ]" + RESET,
            BLUE + "[LOADING   ]" + RESET,
            BLUE + "[LOADING.  ]" + RESET,
            BLUE + "[LOADING.. ]" + RESET,
            BLUE + "[LOADING...]" + RESET,
        };

        System.out.print("Loading ");
        for (String frame : frames) {
            System.out.print("\r" + frame); 
            try {
                TimeUnit.MILLISECONDS.sleep(200); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(); 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        displayLoading();
        printWordAzul();

        boolean continueProgram = true;

        while (continueProgram) {
            System.out.print(WHITE + "Enter start word: " + RESET);
            String startWord = scanner.next();
            System.out.print(WHITE + "Enter end word: " + RESET);
            String endWord = scanner.next();

            // Validate the input
            while (startWord.length() != endWord.length()) {
                System.out.println(RED + "Start word and end word must have the same length." + RESET);
                System.out.print(WHITE + "Enter start word: " + RESET);
                startWord = scanner.next();
                System.out.print(WHITE + "Enter end word: " + RESET);
                endWord = scanner.next();
            }

            if (startWord.equalsIgnoreCase(endWord)) {
                System.out.println(RED + "Start word and end word are the same: " + startWord + RESET);
                continue; 
            }

            // Load dictionary
            Set<String> dictionary = WordDictionary.loadDictionary();

            while (!dictionary.contains(startWord.toLowerCase()) || !dictionary.contains(endWord.toLowerCase())) {
                if (!dictionary.contains(startWord.toLowerCase())) {
                    System.out.println(RED + "Start word " + startWord + " not in dictionary." + RESET);
                } else if (!dictionary.contains(endWord.toLowerCase())) {
                    System.out.println(RED + "End word " + endWord + " not in dictionary." + RESET);
                } else {
                    System.out.println(RED + "Start word and/or end word not in dictionary." + RESET);
                }
                System.out.print(WHITE + "Enter start word: " + RESET);
                startWord = scanner.next();
                System.out.print(WHITE + "Enter end word: " + RESET);
                endWord = scanner.next();
            }

            // Algorithm choice
            System.out.print(BLUE + "Choose algorithm (1-UCS, 2-GBFS, 3-A*): " + RESET);
            int algoChoice = scanner.nextInt();

            while (algoChoice < 1 || algoChoice > 3) {
                System.out.println(RED + "Invalid choice. Please select 1 for UCS, 2 for GBFS, or 3 for A*." + RESET);
                System.out.print(BLUE + "Choose algorithm (1-UCS, 2-GBFS, 3-A*): " + RESET);
                algoChoice = scanner.nextInt();
            }

            Runtime runtime = Runtime.getRuntime();
            runtime.gc(); // Garbage collection before measurement
            long memoryBefore = runtime.totalMemory() - runtime.freeMemory(); // Memory used

            List<String> path = null;
            int nodesVisited = 0;
            long startTime = System.currentTimeMillis(); 

            switch (algoChoice) {
                case 1:
                    UCS ucs = new UCS(dictionary);
                    Result ucsResult = ucs.findPath(startWord.toLowerCase(), endWord.toLowerCase());
                    path = ucsResult.getPath();
                    nodesVisited = ucsResult.getNodesVisited();
                    break;
                case 2:
                    GBFS gbfs = new GBFS(dictionary);
                    Result gbfsResult = gbfs.findPath(startWord.toLowerCase(), endWord.toLowerCase());
                    path = gbfsResult.getPath();
                    nodesVisited = gbfsResult.getNodesVisited();
                    break;
                case 3:
                    AStar astar = new AStar(dictionary);
                    Result astarResult = astar.findPath(startWord.toLowerCase(), endWord.toLowerCase());
                    path = astarResult.getPath();
                    nodesVisited = astarResult.getNodesVisited();
                    break;
            }

            long endTime = System.currentTimeMillis(); 
            long executionTime = endTime - startTime; // Calculate execution time

            long memoryAfter = runtime.totalMemory() - runtime.freeMemory(); // Memory used
            long memoryUsed = memoryAfter - memoryBefore; // Calculate memory usage
            double memoryUsedMB = memoryUsed / (1024.0 * 1024.0); // Convert to MB
            String memoryUsedFormatted = String.format("%.2f", memoryUsedMB);

            // Output results 
            System.out.println(YELLOW + "Nodes visited: " + nodesVisited + RESET);
            System.out.println(BLUE + "Execution time (ms): " + executionTime + RESET);
            System.out.println(CYAN + "Memory used (MB): " + memoryUsedFormatted + RESET);

            if (path == null || path.isEmpty()) {
                System.out.println(RED + "No path solution found." + RESET);
            } else {
                int steps = path.size() - 1; 
                System.out.println(PURPLE + "Steps needed: " + steps + RESET);
                System.out.println(CYAN + "Path:" + RESET);
                for (int i = 0; i < path.size(); i++) {
                    String pathElementColor = GREEN; 
                    System.out.println(pathElementColor + (i + 1) + ". " + path.get(i) + RESET);
                }
            }

            boolean validResponse = false;
            while (!validResponse) {
                System.out.print(WHITE + "Do you want to run another test? (y/n): " + RESET);
                String continueInput = scanner.next();
                if (continueInput.trim().equalsIgnoreCase("y")) {
                    continueProgram = true;
                    validResponse = true;
                } else if (continueInput.trim().equalsIgnoreCase("n")) {
                    continueProgram = false;
                    validResponse = true;
                } else {
                    System.out.println(RED + "Invalid response. Please enter 'y' or 'n'." + RESET);
                }
            }
        }

        System.out.println(BLUE + "Program exited. Goodbye!" + RESET);
    }
}