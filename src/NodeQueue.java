public class NodeQueue {
    private Node info;
    private int nivel;
    private NodeQueue prox;

    public NodeQueue(Node info, int level) {
        this.info = info;
        this.nivel = level;
        this.prox = null;
    }

    public Node getInfo() {
        return info;
    }

    public int getLevel() {
        return nivel;
    }

    public NodeQueue getProx() {
        return prox;
    }

    public void setProx(NodeQueue prox) {
        this.prox = prox;
    }
}