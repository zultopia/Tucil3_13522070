import java.util.*;

public class Utility {
    public static List<String> getNeighbors(String word, Set<String> dictionary) {
        List<String> neighbors = new ArrayList<>();
        char[] wordArr = word.toCharArray();

        for (int i = 0; i < wordArr.length; i++) {
            char originalChar = wordArr[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != originalChar) {
                    wordArr[i] = c;
                    String newWord = new String(wordArr);
                    if (dictionary.contains(newWord)) {
                        neighbors.add(newWord);
                    }
                }
            }
            wordArr[i] = originalChar;
        }

        return neighbors;
    }

    public static int heuristic(String word, String endWord) {
        int diff = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != endWord.charAt(i)) {
                diff++;
            }
        }
        return diff;
    }
}
