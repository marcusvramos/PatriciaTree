public class Fila {
    private NoFila ini;

    public Fila() {}

    public void ini()
    {
        ini=null;
    }

    public void enqueue(No info, int nivel)
    {
        NoFila novo = new NoFila(info,nivel);
        if(ini==null)
            ini=novo;
        else
        {
            NoFila aux = ini;
            while(aux.getProx()!=null)
                aux = aux.getProx();
            aux.setProx(novo);
        }
    }

    public NoFila dequeue()
    {
        if(ini!=null)
        {
            NoFila aux = ini;
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