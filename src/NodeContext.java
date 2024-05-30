class NodeContext {
    Node previousNode;
    Node currentNode;
    int i;

    NodeContext(Node previousNode, Node currentNode, int i) {
        this.previousNode = previousNode;
        this.currentNode = currentNode;
        this.i = i;
    }
}
