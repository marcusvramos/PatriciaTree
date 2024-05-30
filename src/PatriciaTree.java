public class PatriciaTree {
    private Node root;

    public PatriciaTree() {
        this.root = null;
    }

    public void insert(String word) {
        Node newNode = new Node(word);

        if (root == null) {
            root = new Node();
            handleNullRootCase(newNode);
        } else {
            int pos = root.searchLetter(word.charAt(0));
            if (pos == -1) {
                root.insertLetter(word.charAt(0), newNode);
            } else {
                insertIntoTree(newNode, word, pos);
            }
        }
    }

    private void insertIntoTree(Node newNode, String word, int pos) {
        NodeContext context = new NodeContext(root, root.getLink(pos), 1);
        Node intermediaryNode = new Node();

        boolean inserted = false;
        int letterPos = pos;

        while (context.currentNode.getLink(0) != null && context.currentNode.getIndex() <= word.length() && !inserted) {
            if (context.currentNode.getIndex() == context.i) {
                inserted = handleMatchingIndex(context, newNode, word);
                if (!inserted) {
                    letterPos = context.currentNode.searchLetter(word.charAt(context.i - 1));
                }
            } else {
                String childToCompare = searchChildToBeCompare(context.currentNode, word, context.currentNode.getIndex() - 1);
                int diff = findDifferentCharacter(childToCompare, word);
                if (diff < context.currentNode.getIndex() - 1) {
                    insertIntermediaryNode(context.previousNode, context.currentNode, intermediaryNode, childToCompare, word, diff, letterPos, newNode);
                    inserted = true;
                } else if (diff >= context.currentNode.getIndex() - 1) {
                    letterPos = context.currentNode.searchLetter(word.charAt(context.currentNode.getIndex() - 1));
                    if (letterPos == -1) {
                        context.currentNode.insertLetter(word.charAt(context.currentNode.getIndex() - 1), newNode);
                        inserted = true;
                    } else {
                        if (context.currentNode.getLink(0) == null) {
                            splitWord(context.currentNode, newNode, letterPos);
                            inserted = true;
                        } else {
                            context.previousNode = context.currentNode;
                            context.currentNode = context.currentNode.getLink(letterPos);
                        }
                    }
                }
            }
        }
        finalizeInsertion(newNode, word, context.previousNode, context.currentNode, intermediaryNode, inserted);
    }

    private boolean handleMatchingIndex(NodeContext context, Node newNode, String word) {
        int letterPos = context.currentNode.searchLetter(word.charAt(context.i - 1));
        if (letterPos == -1) {
            context.currentNode.insertLetter(word.charAt(context.i - 1), newNode);
            return true;
        } else {
            context.previousNode = context.currentNode;
            context.currentNode = context.currentNode.getLink(letterPos);
            context.i++;
            return false;
        }
    }

    private void insertIntermediaryNode(
        Node previousNode,
        Node currentNode,
        Node intermediaryNode,
        String childToCompare,
        String word,
        int difference,
        int letterPos,
        Node newNode
    ) {
        intermediaryNode.setIndex(difference + 1);
        intermediaryNode.insertLetter(childToCompare.charAt(difference), currentNode);
        intermediaryNode.insertLetter(word.charAt(difference), newNode);
        previousNode.setLink(intermediaryNode, letterPos);
    }

    private void finalizeInsertion(
        Node newNode,
        String word,
        Node previousNode,
        Node currentNode,
        Node intermediaryNode,
        boolean inserted
    ) {
        if (currentNode.getLink(0) == null && !inserted) {
            splitWord(previousNode, newNode, previousNode.searchLetter(word.charAt(previousNode.getIndex() - 1)));
        } else if (currentNode.getIndex() > word.length() && !inserted) {
            intermediaryNode.insertLetter(word.charAt(word.length() - 1), currentNode);
            intermediaryNode.setIndex(word.length());

            currentNode.setWord(word);

            int pos = previousNode.searchLetter(word.charAt(previousNode.getIndex() - 1));
            previousNode.setLink(intermediaryNode, pos);
            intermediaryNode.setLink(currentNode, intermediaryNode.searchLetter(word.charAt(word.length() - 1)));
        }
    }

    private String searchChildToBeCompare(Node root, String word, int j) {
        if (j < word.length()) {
            char origin = word.charAt(j);
            while (root.getLink(0) != null && root.searchLetter(origin) >= 0) {
                root = root.getLink(root.searchLetter(origin));
                origin = word.charAt(j);
                j++;
            }
        }
        while (root.getLink(0) != null) {
            root = root.getLink(0);
        }
        return root.getWord();
    }

    private void splitWord(Node root, Node newNode, int pos) {
        Node currentNode = root.getLink(pos);
        int differentPos = findDifferentCharacter(newNode.getWord(), currentNode.getWord());
        Node intermediaryNode = new Node();

        if (differentPos == newNode.getWord().length()) {
            intermediaryNode.setWord(newNode.getWord());
            intermediaryNode.insertLetter(currentNode.getWord().charAt(differentPos), currentNode);
        } else if (differentPos == currentNode.getWord().length()) {
            intermediaryNode.setWord(currentNode.getWord());
            intermediaryNode.insertLetter(newNode.getWord().charAt(differentPos), newNode);
        } else {
            intermediaryNode.insertLetter(newNode.getWord().charAt(differentPos), newNode);
            intermediaryNode.insertLetter(currentNode.getWord().charAt(differentPos), currentNode);
        }
        intermediaryNode.setIndex(differentPos + 1);
        root.setLink(intermediaryNode, pos);
    }

    private int findDifferentCharacter(String firstWord, String secondWord) {
        int k = 0;
        while (
            k < firstWord.length() &&
            k < secondWord.length() &&
            firstWord.charAt(k) == secondWord.charAt(k)
        )
            k++;

        return k;
    }

    private void handleNullRootCase(Node newNode) {
        root.setLink(newNode, 0);
        root.setIndex(1);
        root.setLetter(newNode.getWord().charAt(0), 0);
        root.setLetterCount(1);
    }

    public boolean searchWord(String word) {
        Stack stack = new Stack();
        stack.init();
        stack.push(root);

        boolean found = false;

        while (!stack.isEmpty() && !found) {
            Node currentNode = stack.pop();

            if (currentNode != null) {
                if (currentNode.getWord().equals(word)) {
                    found = true;
                } else {
                    for (int i = 0; i < currentNode.getLetterCount(); i++) {
                        stack.push(currentNode.getLink(i));
                    }
                }
            }
        }
        return found;
    }

    public void displayAllWords() {
        Stack stack = new Stack();
        stack.init();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            if (!currentNode.getWord().isEmpty()) {
                System.out.println(currentNode.getWord());
            }
            for (Node child : currentNode.getLinks()) {
                if (child != null) {
                    stack.push(child);
                }
            }
        }
    }

    public void displayNodesLevelByLevel() {
        Queue queue = new Queue();
        queue.init();
        queue.enqueue(root, 0);

        while (!queue.isEmpty()) {
            NodeQueue nodeQueue = queue.dequeue();
            Node currentNode = nodeQueue.getInfo();
            int level = nodeQueue.getLevel();

            System.out.println(
                "Nível " + level +
                ": " + buildLetterString(currentNode.getLetters()) +
                " Palavra: " + currentNode.getWord() +
                " Índice: " + currentNode.getIndex()
            );

            for (Node child : currentNode.getLinks()) {
                if (child != null) {
                    queue.enqueue(child, level + 1);
                }
            }
        }
    }

    private String buildLetterString(char[] letters) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (char letter : letters) {
            if (letter != '\u0000') {
                sb.append(letter).append(", ");
            }
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }
}
