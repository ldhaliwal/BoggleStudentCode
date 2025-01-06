import java.util.ArrayList;
import java.util.Arrays;

public class Boggle {

    public static String[] findWords(char[][] board, String[] dictionary) {

        ArrayList<String> goodWords = new ArrayList<String>();

        // TODO: Complete the function findWords(). Add all words that are found both on the board
        //  and in the dictionary.

        // Create a TST for the dictionary
        TST dictTrie = new TST();

        for (String word : dictionary) {
            dictTrie.insertHelper(word);
        }


        // Use DFS as you go through the board to find possible words
        // set T or F to track if the letter has been visited.
        // check if there are any words in the TST that start with the current string of letters
            // if there are not, set the current last letter's visited status to F and backtrack to the last letter



        // Convert the list into a sorted array of strings, then return the array.
        String[] sol = new String[goodWords.size()];
        goodWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
    }
}
