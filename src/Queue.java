public class Queue {
    private NodeQueue ini;

    public Queue() {}

    public void init()
    {
        ini=null;
    }

    public void enqueue(Node info, int nivel)
    {
        NodeQueue novo = new NodeQueue(info,nivel);
        if(ini==null)
            ini=novo;
        else
        {
            NodeQueue aux = ini;
            while(aux.getProx()!=null)
                aux = aux.getProx();
            aux.setProx(novo);
        }
    }

    public NodeQueue dequeue()
    {
        if(ini!=null)
        {
            NodeQueue aux = ini;
            ini = ini.getProx();
            return aux;
        }
        return null;
    }

    public boolean isEmpty()
    {
        return ini==null;
    }
}