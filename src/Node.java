public class Node {
    private char[] letters;
    private Node[] links;
    private int index;
    private String word;
    private int letterCount;

    public Node(String word) {
        this.letters = new char[26];
        this.links = new Node[26];
        this.index = -1;
        this.word = word;
        this.letterCount = 0;
    }

    public Node() {
        this.letters = new char[26];
        this.links = new Node[26];
        this.index = -1;
        this.word = "";
        this.letterCount = 0;
    }

    public int searchLetter(char letter) {
        int indice = -1;
        int j = 0;
        while (j < letterCount && indice == -1) {
            if (letters[j] == letter) {
                indice = j;
            }
            j++;
        }
        return indice;
    }

    public void insertLetter(char letter, Node link) {
        int i = letterCount - 1;
        while (i >= 0 && letters[i] > letter) {
            letters[i + 1] = letters[i];
            links[i + 1] = links[i];
            i--;
        }
        letters[i + 1] = letter;
        links[i + 1] = link;
        letterCount++;
    }

    public char[] getLetters() {
        return letters;
    }

    public void setLetter(char letter, int pos) {
        this.letters[pos] = letter;
    }

    public Node getLink(int pos) {
        return links[pos];
    }

    public Node[] getLinks() {
        return links;
    }

    public void setLink(Node link, int pos) {
        this.links[pos] = link;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getLetterCount() {
        return letterCount;
    }

    public void setLetterCount(int letterCount) {
        this.letterCount = letterCount;
    }
}
