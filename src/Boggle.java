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

    private static void dfs(char[][] board, int row, int col, String prefix, TST dictTrie, ArrayList<String> goodWords, boolean[][] visited) {
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || visited[row][col]) {
            return;
        }

        prefix += board[row][col];

        // backtrack if no words in the dictionary start with the prefix
        if (!dictTrie.startsWith(prefix)) {
            return;
        }

        // if its a valid word, add it to the list
        if (dictTrie.lookupHelper(prefix)) {
            goodWords.add(prefix);
        }

        // mark as visited
        visited[row][col] = true;

        // go through all the adjacent cells
        for (int[] dir : DIRECTIONS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            dfs(board, newRow, newCol, prefix, dictTrie, goodWords, visited);
        }

        // Backtrack and revoke visited status
        visited[row][col] = false;
    }
}
