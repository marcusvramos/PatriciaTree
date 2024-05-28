public class PatriciaTree {
    private final No raiz;

    public PatriciaTree() {
        this.raiz = new No();
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
        raiz.setIndice(1); // Índice da primeira letra
        No novoNo = new No(palavra);
        raiz.setFilhos(novoNo, palavra.charAt(0) - 'a');
    }

    private void inserirSemLigacao(No atual, String palavra, int pos, int indiceLetra) {
        No novoNo = new No(palavra);
        atual.setLetras(palavra.charAt(pos), indiceLetra);
        atual.setFilhos(novoNo, indiceLetra);
    }

    private void criarNovoNoIntermediario(No atual, No aux, String palavra, String p2, int i, int indiceLetra) {
        No novoNoIntermediario = new No();
        novoNoIntermediario.setIndice(i + 1);

        novoNoIntermediario.setLetras(p2.charAt(i), p2.charAt(i) - 'a');
        novoNoIntermediario.setFilhos(aux, p2.charAt(i) - 'a');

        No novoNo = new No(palavra);
        novoNoIntermediario.setLetras(palavra.charAt(i), palavra.charAt(i) - 'a');
        novoNoIntermediario.setFilhos(novoNo, palavra.charAt(i) - 'a');

        atual.setFilhos(novoNoIntermediario, indiceLetra);
    }

    private void criarNoIntermediarioPrefixo(No atual, No aux, String p2, int i, int indiceLetra) {
        No novoNoIntermediario = new No();
        novoNoIntermediario.setIndice(i);

        char letraAtual = p2.charAt(i - 1);
        novoNoIntermediario.setLetras(letraAtual, letraAtual - 'a');
        novoNoIntermediario.setFilhos(aux, letraAtual - 'a');

        aux.setPalavra(p2.substring(0, i));

        atual.setFilhos(novoNoIntermediario, indiceLetra);
    }

    private void criarNoAposNoIntermediario(No atual, No aux, String palavra, int i, int indiceLetra) {
        No novoNoIntermediario = new No();
        novoNoIntermediario.setIndice(i + 1);

        novoNoIntermediario.setLetras(palavra.charAt(i - 1), palavra.charAt(i - 1) - 'a');
        novoNoIntermediario.setFilhos(aux, palavra.charAt(i - 1) - 'a');

        No novoNo = new No(palavra);
        novoNoIntermediario.setFilhos(novoNo, palavra.charAt(i) - 'a');

        atual.setFilhos(novoNoIntermediario, indiceLetra);
    }

    public void inserir(String palavra) {
        if (raiz.getIndice() == -1) { // Caso 1: Raiz nula
            tratarRaizNula(palavra);
            return;
        }

        No atual = raiz;
        No aux;
        int pos = 0;
        boolean inserido = false;

        while (!inserido) {
            int indiceLetra = palavra.charAt(pos) - 'a';
            aux = atual.getFilhos(indiceLetra);

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

    public boolean buscarPalavra(String palavra) {
        return buscarPalavraRecursivo(raiz, palavra);
    }

    private boolean buscarPalavraRecursivo(No atual, String palavra) {
        if (atual == null) {
            return false;
        }

        // Verifica se o nó atual contém a palavra procurada
        if (atual.getPalavra().equals(palavra)) {
            return true;
        }

        // Busca nos filhos do nó atual
        for (No filho : atual.getFilhos()) {
            if (buscarPalavraRecursivo(filho, palavra)) {
                return true;
            }
        }

        return false;
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
