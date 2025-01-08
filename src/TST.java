public class TST {
    private TST_Node root = new TST_Node();

    public void insertHelper(String s) {
        root = insert(root, s, 0);
    }

    public TST_Node insert(TST_Node current, String s, int index) {
        // If it's at end of the string, return the node
        if (index == s.length()) {
            return current;
        }

        // Get the current character
        char c = s.charAt(index);

        // If the current node is null, create a new node and set it to the current character
        if (current == null) {
            current = new TST_Node();
            current.setCharacter(c);
        }

        // If the current node is blank, set it to the current character
        if (current.getCharacter() == '_') {
            current.setCharacter(c);
        }

        // Move to the left if the character is smaller
        if (c < current.getCharacter()) {
            current.setLeft(insert(current.getLeft(), s, index));
        }

        // Move to the right if the character is larger
        else if (c > current.getCharacter()) {
            current.setRight(insert(current.getRight(), s, index));
        }

        else {
            // If it's the last character, mark the current node as a word
            if (index + 1 == s.length()) {
                current.setWord();
            }
            else {
                current.setCenter(insert(current.getCenter(), s, index + 1));
            }
        }
        return current;
    }

    public boolean lookupHelper(String s) {
        return lookup(root, s, 0);
    }

    // Overload the method to add in the T/F thing we were talking about for dfs.
    private boolean lookup(TST_Node current, String s, int index) {
        return lookup(current, s, index, true);
    }

    public boolean lookup(TST_Node current, String s, int index, boolean checkWord) {
        // If the current node is null, the word doesn't exist
        if (current == null) {
            return false;
        }

        // Get the current character from the string
        char c = s.charAt(index);

        // If the character in the node is greater than the current character in the string, go left
        if (c < current.getCharacter()) {
            return lookup(current.getLeft(), s, index, checkWord);
        }

        // If the character in the node is less than the current character in the string, go right
        else if (c > current.getCharacter()) {
            return lookup(current.getRight(), s, index, checkWord);
        }

        // If the characters are equal
        else {
            // If we're at the end of the string, check if it's a word
            if (index == s.length() - 1) {
                return !checkWord || current.isWord();
            }

            // Otherwise, move to the center and continue to the next character
            return lookup(current.getCenter(), s, index + 1, checkWord);
        }
    }

    public boolean startsWith(String prefix) {
        return lookup(root, prefix, 0, false);
    }

}
