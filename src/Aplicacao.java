public class Aplicacao {

    public static void main(String[] args) {
        PatriciaTree trie = new PatriciaTree();
        trie.insert("galo");
        trie.insert("gel");
        trie.insert("sola");
        trie.insert("solo");
        trie.insert("sol");
        trie.insert("solado");
        trie.insert("soldador");
        trie.insert("astronauta");
        trie.insert("aviador");
        trie.insert("banana");
        trie.insert("batata");
        trie.insert("barco");

        System.out.println("Palavras inseridas: ");
        trie.displayAllWords();

        System.out.println("\nBuscando palavras: ");
        System.out.println("Buscando 'galo': " + trie.searchWord("galo"));
        System.out.println("Buscando 'gel': " + trie.searchWord("gel"));
        System.out.println("Buscando 'sola': " + trie.searchWord("sola"));
        System.out.println("Buscando 'solo': " + trie.searchWord("solo"));
        System.out.println("Buscando 'sol': " + trie.searchWord("sol"));
        System.out.println("Buscando 'solado': " + trie.searchWord("solado"));
        System.out.println("Buscando 'soldador': " + trie.searchWord("soldador"));
        System.out.println("Buscando 'astronauta': " + trie.searchWord("astronauta"));
        System.out.println("Buscando 'aviador': " + trie.searchWord("aviador"));
        System.out.println("Buscando 'banana': " + trie.searchWord("banana"));
        System.out.println("Buscando 'batata': " + trie.searchWord("batata"));
        System.out.println("Buscando 'barco': " + trie.searchWord("barco"));
        System.out.println("Buscando 'palavrainexistente': " + trie.searchWord("palavrainexistente"));

        System.out.println("\nExibindo nível a nível: ");
        trie.displayNodesLevelByLevel();
    }
}
