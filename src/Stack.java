public class Stack {
    private Stack prox;
    private Node no;

    public void init(){
        this.prox = null;
        this.no = null;
    }
    public void push(Node node){
        Stack newStack = new Stack();
        newStack.no = node;
        newStack.prox = this.prox;
        this.prox = newStack;
    }

    public Node pop(){
        Node node = this.prox.no;
        this.prox = this.prox.prox;
        return node;
    }

    public boolean isEmpty(){
        return this.prox == null;
    }
}