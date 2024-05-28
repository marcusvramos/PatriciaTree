public class PatriciaTree {
    private No raiz;

    public PatriciaTree() {
        this.raiz = new No();
    }

    public void inserir(String palavra) {
        if (raiz == null) {
            raiz = new No(palavra, 0);
            return;
        }
        inserir(raiz, palavra, 0);
    }

    private void inserir(No atual, String palavra, int pos) {
        if (atual.getIndice() == -1) { // Caso 1: Nó atual é vazio
            atual.setPalavra(palavra);
            atual.setIndice(pos);
            return;
        }

        int indiceLetra = palavra.charAt(pos) - 'a';

        // Caso 2: Segue e não tem ligação na letra
        if (atual.getFilhos(indiceLetra) == null) {
            No novoNo = new No(palavra, pos + 1);
            atual.setLetras(palavra.charAt(pos), indiceLetra);
            atual.setFilhos(novoNo, indiceLetra);
            return;
        }

        No prox = atual.getFilhos(indiceLetra);
        int i = prox.getIndice();

        // Encontrar a primeira diferença entre a palavra e o próximo nó
        while (i < palavra.length() && i < prox.getPalavra().length() && palavra.charAt(i) == prox.getPalavra().charAt(i)) {
            i++;
        }

        if (i == palavra.length() && i == prox.getPalavra().length()) {
            return; // Palavra já está na árvore
        }

        // Caso 3: Não é seguido e a diferença está antes (entre as palavras)
        if (i < prox.getIndice()) {
            No novoNoIntermediario = new No();
            novoNoIntermediario.setIndice(i);

            novoNoIntermediario.setLetras(prox.getPalavra().charAt(i), prox.getPalavra().charAt(i) - 'a');
            novoNoIntermediario.setFilhos(prox, prox.getPalavra().charAt(i) - 'a');

            No novoNo = new No(palavra, i + 1);
            novoNoIntermediario.setLetras(palavra.charAt(i), palavra.charAt(i) - 'a');
            novoNoIntermediario.setFilhos(novoNo, palavra.charAt(i) - 'a');

            atual.setFilhos(novoNoIntermediario, indiceLetra);
            return;
        }

        // Caso 4: Não é seguido e a diferença está depois (prefixo)
        if (i < palavra.length()) {
            No novoNo = new No(palavra, i + 1);
            prox.setFilhos(novoNo, palavra.charAt(i) - 'a');
            return;
        }

        inserir(prox, palavra, i);
    }
}
