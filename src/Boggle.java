import java.util.ArrayList;
import java.util.Arrays;

public class Boggle {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static String[] findWords(char[][] board, String[] dictionary) {

        ArrayList<String> goodWords = new ArrayList<>();

        // Create a TST for the goodWords to help with lookup time
        TST goodWordsTrie = new TST();

        // Create a TST for the dictionary
        TST dictTrie = new TST();

        for (String word : dictionary) {
            dictTrie.insertHelper(word);
        }

        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];

        // Loop through each letter on the board and call dfs
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dfs(board, i, j, "", dictTrie, goodWords, goodWordsTrie, visited);
            }
        }

        // Convert the list into a sorted array of strings, then return the array.
        String[] sol = new String[goodWords.size()];
        goodWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
    }

    private static void dfs(char[][] board, int row, int col, String prefix, TST dictTrie, ArrayList<String> goodWords, TST goodWordsTrie, boolean[][] visited) {
        // Base case checks that the current cell isn't out of bounds, already visited,
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || visited[row][col]) {
            return;
        }

        // Update prefix
        prefix += board[row][col];

        // Check if there are no words starting with the prefix
        if(!dictTrie.startsWith(prefix)){
            return;
        }

        // If it's a valid word and not a duplicate, add it to the list
        if (!goodWordsTrie.lookupHelper(prefix) && dictTrie.lookupHelper(prefix)) {
            goodWords.add(prefix);
            goodWordsTrie.insertHelper(prefix);
        }

        // Mark as visited
        visited[row][col] = true;

        // Go through all the adjacent cells
        for (int[] dir : DIRECTIONS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            dfs(board, newRow, newCol, prefix, dictTrie, goodWords, goodWordsTrie, visited);
        }

        // Backtrack and revoke visited status
        visited[row][col] = false;
    }
}
