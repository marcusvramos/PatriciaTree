public class Aplicacao {

    public static void main(String[] args) {
        PatriciaTree trie = new PatriciaTree();
        trie.inserir("galo");
        trie.inserir("gel");
        trie.inserir("sola");
        trie.inserir("solo");
        trie.inserir("sol");

        System.out.println("Palavras inseridas: ");
        trie.exibirTodasPalavras();

        System.out.println("\nBuscando palavras: ");
        System.out.println("Buscando 'galo': " + trie.buscarPalavra("galo"));
        System.out.println("Buscando 'gel': " + trie.buscarPalavra("gel"));
        System.out.println("Buscando 'sola': " + trie.buscarPalavra("sola"));
        System.out.println("Buscando 'solo': " + trie.buscarPalavra("solo"));
        System.out.println("Buscando 'sol': " + trie.buscarPalavra("sol"));
        System.out.println("Buscando 'palavrainexistente': " + trie.buscarPalavra("palavrainexistente"));

        System.out.println("\nExibindo nível a nível: ");
        trie.exibirNosNivelANivel();
    }
}
