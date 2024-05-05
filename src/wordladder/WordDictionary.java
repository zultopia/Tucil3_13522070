import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class WordDictionary {
    public static Set<String> loadDictionary() {
        String filePath = "dictionary/oracle-dictionary.txt"; // File kamus utama dari oracle
        
        Set<String> dictionary = new HashSet<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) { 
                dictionary.add(line.trim().toLowerCase()); // Memastikan semua kata menjadi lowercase
            }
        } catch (IOException e) { 
            System.err.println("Error loading dictionary: " + e.getMessage());
        }
        
        return dictionary; 
    }
}