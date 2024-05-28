public class NoFila {
    private No info;
    private int nivel;
    private NoFila prox;

    public NoFila(No info, int level) {
        this.info = info;
        this.nivel = level;
        this.prox = null;
    }

    public No getInfo() {
        return info;
    }

    public void setInfo(No info) {
        this.info = info;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int level) {
        this.nivel = level;
    }

    public NoFila getProx() {
        return prox;
    }

    public void setProx(NoFila prox) {
        this.prox = prox;
    }
}