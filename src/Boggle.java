import java.util.ArrayList;
import java.util.Arrays;

public class Boggle {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static String[] findWords(char[][] board, String[] dictionary) {

        ArrayList<String> goodWords = new ArrayList<String>();

        // Create a TST for the dictionary
        TST dictTrie = new TST();

        for (String word : dictionary) {
            dictTrie.insertHelper(word);
        }

        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];


        // loop through each letter on the board and call dfs
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dfs(board, i, j, "", dictTrie, goodWords, visited);
            }
        }

        // some way to remove duplicates or do that in the dfs method?

        // Convert the list into a sorted array of strings, then return the array.
        String[] sol = new String[goodWords.size()];
        goodWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
    }
}
