import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {
    public static Set<String> loadDictionary(int wordLength) {
        // Jika panjang kata >= 16, akses file khusus
        String filePath;
        if (wordLength >= 16) {
            filePath = "dictionary/dictionary-up-to-16-letter-words.txt";
        } else {
            filePath = "dictionary/dictionary-" + wordLength + "-letter-words.txt"; 
        }
        
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
}