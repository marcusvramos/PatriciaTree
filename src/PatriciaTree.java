public class PatriciaTree {
    private final No raiz;

    public PatriciaTree() {
        this.raiz = new No();
    }

    public void inserir(String palavra) {
        No atual = raiz;
        No aux;
        int pos = 0;
        boolean inserido = false;

        if (raiz.getIndice() == -1) { // Caso 1: Raiz nula
            tratarRaizNula(palavra);
        } else {
            while (!inserido) {
                int indiceLetra = palavra.charAt(pos) - 'a';
                aux = atual.getFilho(indiceLetra);

                if (aux == null) { // Caso 2: Segue e não tem ligação na letra
                    inserirSemLigacao(atual, palavra, pos, indiceLetra);
                    inserido = true;
                } else {
                    String p2 = encontrarProximoNoNaoVazio(aux).getPalavra();
                    int i = pos + 1;
                    while (i < palavra.length() && i < p2.length() && palavra.charAt(i) == p2.charAt(i)) {
                        i++;
                    }

                    if (i == palavra.length() && i == p2.length()) {
                        inserido = true; // Palavra já está na árvore
                    } else if (i < p2.length() && i < palavra.length()) { // Caso 3: Inserindo 'galo' e posteriormente 'gel'
                        criarNovoNoIntermediario(atual, aux, palavra, p2, i, indiceLetra);
                        inserido = true;
                    } else if (i == palavra.length() && i < p2.length()) { // Caso 4: Prefixo
                        criarNoIntermediarioPrefixo(atual, aux, p2, i, indiceLetra);
                        inserido = true;
                    } else if (i < palavra.length()) { // Caso 5: Inserção padrão após nó intermediário
                        criarNoAposNoIntermediario(atual, aux, palavra, i, indiceLetra);
                        inserido = true;
                    } else {
                        pos = i;
                        atual = aux;
                    }
                }
            }
        }
    }

    public boolean buscarPalavra(String palavra) {
        Pilha pilha = new Pilha();
        pilha.init();
        pilha.push(raiz);

        boolean encontrada = false;

        while (!pilha.isEmpty() && !encontrada) {
            No atual = pilha.pop();

            if (atual != null) {
                if (atual.getPalavra().equals(palavra)) {
                    encontrada = true;
                }

                // Busca nos filhos do nó atual
                No[] filhos = atual.getFilhos();
                for (int i = 0; i < filhos.length && !encontrada; i++) {
                    if (filhos[i] != null) {
                        pilha.push(filhos[i]);
                    }
                }
            }
        }

        return encontrada;
    }

    public void exibirTodasPalavras() {
        Pilha pilha = new Pilha();
        pilha.init();
        pilha.push(raiz);

        while (!pilha.isEmpty()) {
            No atual = pilha.pop();
            if (!atual.getPalavra().isEmpty()) {
                System.out.println(atual.getPalavra());
            }
            for (No filho : atual.getFilhos()) {
                if (filho != null) {
                    pilha.push(filho);
                }
            }
        }
    }

    public void exibirNosNivelANivel() {
        Fila fila = new Fila();
        fila.ini();
        fila.enqueue(raiz, 0);

        while (!fila.isEmpty()) {
            NoFila noFila = fila.dequeue();
            No atual = noFila.getInfo();
            int nivel = noFila.getNivel();

            System.out.println(
                "Nível " + nivel +
                ": " + construirStringLetras(atual.getLetras()) +
                " Palavra: " + atual.getPalavra() +
                " Índice: " + atual.getIndice()
            );

            for (No filho : atual.getFilhos()) {
                if (filho != null) {
                    fila.enqueue(filho, nivel + 1);
                }
            }
        }
    }

    private No encontrarProximoNoNaoVazio(No no) {
        boolean encontrouPalavra = false;

        while (no.getPalavra().isEmpty() && !encontrouPalavra) {
            encontrouPalavra = true;
            No proximoNoNaoVazio = null;

            for (No filho : no.getFilhos()) {
                if (filho != null) {
                    proximoNoNaoVazio = filho;
                    encontrouPalavra = false;
                }
            }

            if (proximoNoNaoVazio != null) {
                no = proximoNoNaoVazio;
            }
        }

        return no;
    }

    private void tratarRaizNula(String palavra) {
        raiz.setLetras(palavra.charAt(0), palavra.charAt(0) - 'a');
        raiz.setIndice(1);
        No novoNo = new No(palavra);
        raiz.setFilho(novoNo, palavra.charAt(0) - 'a');
    }

    private void inserirSemLigacao(No atual, String palavra, int pos, int indiceLetra) {
        No novoNo = new No(palavra);
        atual.setLetras(palavra.charAt(pos), indiceLetra);
        atual.setFilho(novoNo, indiceLetra);
    }

    private void criarNovoNoIntermediario(No atual, No aux, String palavra, String p2, int i, int indiceLetra) {
        No novoNoIntermediario = new No();
        novoNoIntermediario.setIndice(i + 1);

        novoNoIntermediario.setLetras(p2.charAt(i), p2.charAt(i) - 'a');
        novoNoIntermediario.setFilho(aux, p2.charAt(i) - 'a');

        No novoNo = new No(palavra);
        novoNoIntermediario.setLetras(palavra.charAt(i), palavra.charAt(i) - 'a');
        novoNoIntermediario.setFilho(novoNo, palavra.charAt(i) - 'a');

        atual.setFilho(novoNoIntermediario, indiceLetra);
    }

    private void criarNoIntermediarioPrefixo(No atual, No aux, String p2, int i, int indiceLetra) {
        No novoNoIntermediario = new No();
        novoNoIntermediario.setIndice(i);

        char letraAtual = p2.charAt(i - 1);
        novoNoIntermediario.setLetras(letraAtual, letraAtual - 'a');
        novoNoIntermediario.setFilho(aux, letraAtual - 'a');

        aux.setPalavra(p2.substring(0, i));

        atual.setFilho(novoNoIntermediario, indiceLetra);
    }

    private void criarNoAposNoIntermediario(No atual, No aux, String palavra, int i, int indiceLetra) {
        No novoNoIntermediario = new No();
        novoNoIntermediario.setIndice(i + 1);

        novoNoIntermediario.setLetras(palavra.charAt(i - 1), palavra.charAt(i - 1) - 'a');
        novoNoIntermediario.setFilho(aux, palavra.charAt(i - 1) - 'a');

        No novoNo = new No(palavra);
        novoNoIntermediario.setFilho(novoNo, palavra.charAt(i) - 'a');

        atual.setFilho(novoNoIntermediario, indiceLetra);
    }

    private String construirStringLetras(char[] letras) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (char letra : letras) {
            if (letra != '\u0000') {
                sb.append(letra).append(", ");
            }
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }
}
