import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;
import java.util.List; 

public class GUI2 extends JFrame {

    private JTextField startWordField;
    private JTextField endWordField;
    private JComboBox<String> algorithmDropdown;
    private JTextArea resultBox;

    public GUI2() {
        super("WordAzul");

        GradientPanel mainPanel = new GradientPanel(new Color(255, 255, 255), new Color(173, 216, 230));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

        // Intro section
        JLabel headerLabel = new JLabel(loadAndResizeImage("images/wordazul.png", 400, 100));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(headerLabel);

        JLabel welcomeLabel = new JLabel("Selamat Datang di WordAzul!");
        welcomeLabel.setFont(new Font("Monospace", Font.BOLD, 24));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(welcomeLabel);

        mainPanel.add(Box.createVerticalStrut(25)); 

        JLabel descriptionLabel = new JLabel("<html>"
            + "WordAzul adalah platform yang dapat memberikan solusi untuk teka-teki word ladder. Di Word Ladder, kalian ditantang untuk menghubungkan dua kata dengan mengubah satu huruf pada satu waktu, "
            + "menciptakan rangkaian kata yang menarik dan logis. Setiap perubahan membawa kalian lebih dekat ke tujuan, menawarkan pengalaman mendidik dan menghibur. Namun, jika kalian kesulitan kalian "
            + "bisa memanfaatkan WordAzul dalam menemukan steps-stepsnya."
            + "</html>");
        descriptionLabel.setFont(new Font("Monospace", Font.PLAIN, 14));
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(descriptionLabel);

        mainPanel.add(Box.createVerticalStrut(25)); 

        // How To use Section
        JLabel howToUseLabel = new JLabel("How to Use");
        howToUseLabel.setFont(new Font("Monospace", Font.BOLD, 20));
        howToUseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(howToUseLabel);

        mainPanel.add(Box.createVerticalStrut(25)); 

        JLabel stepsLabel = new JLabel("<html>"
            + "- Masukkan start word dan end word yang diinginkan.<br>"
            + "- Pilih algoritma route planning yang ingin digunakan.<br>"
            + "- Klik tombol search, lalu hasilnya akan segera muncul di bagian bawah."
            + "</html>");
        stepsLabel.setFont(new Font("Monospace", Font.PLAIN, 14));
        stepsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(stepsLabel);

        mainPanel.add(Box.createVerticalStrut(25)); 

        JLabel headerBoss = new JLabel(loadAndResizeImage("images/bossteam.png", 280, 120));
        headerBoss.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(headerBoss);

        mainPanel.add(Box.createVerticalStrut(25)); 

        // Input Section
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0)); 
        inputPanel.setOpaque(false);
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel startWordLabel = new JLabel("Start Word:");
        startWordField = new JTextField(10);
        inputPanel.add(startWordLabel);
        startWordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputPanel.add(startWordField);

        JLabel endWordLabel = new JLabel("End Word:");
        endWordField = new JTextField(10);
        inputPanel.add(endWordLabel);
        inputPanel.add(endWordField);

        mainPanel.add(inputPanel); 

        // Algorithm Selection
        JPanel algorithmPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JLabel algorithmLabel = new JLabel("Choose Algorithm:");
        algorithmDropdown = new JComboBox<>(new String[]{"UCS", "GBFS", "A*"}); 
        algorithmPanel.setOpaque(false);
        algorithmPanel.add(algorithmLabel);
        algorithmPanel.add(algorithmDropdown);
        algorithmLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(algorithmPanel);

        // Search Button and Result Area
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Monospace", Font.BOLD, 16));
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        Color originalBackground = searchButton.getBackground();
        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                searchButton.setBackground(Color.LIGHT_GRAY); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                searchButton.setBackground(originalBackground); 
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeAlgorithm(); 
            }
        });

        resultBox = new JTextArea();
        resultBox.setEditable(false);
        resultBox.setLineWrap(true);
        resultBox.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultBox);
        scrollPane.setPreferredSize(new Dimension(700, 300));

        mainPanel.add(searchButton); 
        mainPanel.add(scrollPane); 

        setContentPane(mainPanel);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); 
    }

    // Game section
    private void executeAlgorithm() {
        String startWord = startWordField.getText().trim();
        String endWord = endWordField.getText().trim();
        String selectedAlgorithm = (String) algorithmDropdown.getSelectedItem();

        if (selectedAlgorithm == null) {
            resultBox.setText("No algorithm selected.");
            return;
        }

        if (startWord.isEmpty() || endWord.isEmpty()) {
            resultBox.setText("Enter both start word and end word!");
            return;
        }

        if (startWord.length() != endWord.length()) {
            resultBox.setText("Start word and end word must have the same length.");
            return;
        }

        if (startWord.equalsIgnoreCase(endWord)) {
            resultBox.setText("Start word and end word are the same: " + startWord);
            return;
        }

        Set<String> dictionary = WordDictionary.loadDictionary(); 

        if (!dictionary.contains(startWord.toLowerCase())) {
            resultBox.setText("Start word " + startWord + " not in dictionary.");
            return;

        } else if (!dictionary.contains(endWord.toLowerCase())) {
            resultBox.setText("End word " + endWord + " not in dictionary.");
            return;
        }

        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

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

        long memoryAfter = runtime.totalMemory() - runtime.freeMemory(); 
        long memoryUsed = memoryAfter - memoryBefore; 
        double memoryUsedMB = memoryUsed / (1024.0 * 1024.0); 
        String memoryUsedFormatted = String.format("%.2f", memoryUsedMB);

        StringBuilder result = new StringBuilder();
        result.append("Nodes visited: " + nodesVisited + "\n");
        result.append("Execution time (ms): " + executionTime + "\n");
        result.append("Memory used (MB): " + memoryUsedFormatted + "\n");

        if (path == null || path.size() == 0) {
            result.append("No path found!");
        } else {
            int steps = path.size() - 1;
            result.append("Steps needed: " + steps + "\n");

            result.append("Path:\n");
            for (int i = 0; i < path.size(); i++) {
                result.append((i + 1) + ". " + path.get(i) + "\n");
            }
        }

        resultBox.setText(result.toString()); 
    }

    private ImageIcon loadAndResizeImage(String filePath, int width, int height) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(filePath));
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        } catch (IOException e) {
            e.printStackTrace(); 
            return null;
        }
    }

    class GradientPanel extends JPanel {
        private Color startColor;
        private Color endColor;

        public GradientPanel(Color startColor, Color endColor) {
            this.startColor = startColor;
            this.endColor = endColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, getHeight(), endColor);
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public static void main(String[] args) {
        new GUI2(); 
    }
}