public class No {
    private char[] letras;
    private No[] filhos;
    private int indice;
    private String palavra;

    public No(String palavra) {
        this.letras = new char[26];
        this.filhos = new No[26];
        this.indice = -1;
        this.palavra = palavra;
    }

    public No() {
        this.letras = new char[26];
        this.filhos = new No[26];
        this.indice = -1;
        this.palavra = "";
    }

    public char getLetras(int pos) {
        return letras[pos];
    }

    public char[] getLetras() {
        return letras;
    }

    public void setLetras(char letra, int pos) {
        this.letras[pos] = letra;
    }

    public void setLetras(char[] letras) {
        this.letras = letras;
    }

    public No getFilhos(int pos) {
        return filhos[pos];
    }

    public No[] getFilhos() {
        return filhos;
    }

    public void setFilhos(No filhos, int pos) {
        this.filhos[pos] = filhos;
    }

    public void setFilhos(No[] filhos) {
        this.filhos = filhos;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }
}
