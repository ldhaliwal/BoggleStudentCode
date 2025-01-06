public class TST_Node {
    private char character;
    private boolean isWord;
    private TST_Node left;
    private TST_Node right;
    private TST_Node center;

    public TST_Node() {
        isWord = false;
        left = null;
        right = null;
        center = null;
        character = '_';
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setWord() {
        isWord = true;
    }

    public TST_Node getLeft() {
        return left;
    }

    public void setLeft(TST_Node left) {
        this.left = left;
    }

    public TST_Node getRight() {
        return right;
    }

    public void setRight(TST_Node right) {
        this.right = right;
    }

    public TST_Node getCenter() {
        return center;
    }

    public void setCenter(TST_Node center) {
        this.center = center;
    }
}
