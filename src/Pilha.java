public class Stack {
    private Stack next;
    private No node;

    public void init(){
        this.next = null;
        this.node = null;
    }
    public void push(No node){
        Stack newStack = new Stack();
        newStack.node = node;
        newStack.next = this.next;
        this.next = newStack;
    }

    public No pop(){
        No node = this.next.node;
        this.next = this.next.next;
        return node;
    }

    public boolean isEmpty(){
        return this.next == null;
    }

    public No peek(){
        return this.next.node;
    }

    public void print(){
        Stack current = this.next;
        while(current != null){
            System.out.println(current.node.getPalavra());
            current = current.next;
        }
    }
}