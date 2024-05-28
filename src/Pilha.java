public class Pilha {
    private Pilha prox;
    private No no;

    public void init(){
        this.prox = null;
        this.no = null;
    }
    public void push(No node){
        Pilha newStack = new Pilha();
        newStack.no = node;
        newStack.prox = this.prox;
        this.prox = newStack;
    }

    public No pop(){
        No node = this.prox.no;
        this.prox = this.prox.prox;
        return node;
    }

    public No top(){
        return this.prox.no;
    }

    public boolean isEmpty(){
        return this.prox == null;
    }
}