public class ArrayList implements Listable{
    private Object[] data;
    private int head;
    private int tail;
    private int numberElements;

    public ArrayList() {
        this(10);
    }

    public ArrayList(int lenght) {
        data = new Object[lenght];
        head = 0;
        tail = -1;
        numberElements = 0;
    }

    @Override
    public void insert(Object data, int LogicIndex) {
        if (isFull()) {
            System.out.println("Lista está cheia!");
        }
        if (LogicIndex < 0 || LogicIndex > numberElements) {
            System.err.println("Index inválido!");
        }

        // Avançando elementos para gerar espaço vazio
        /*
        int aux = tail;
        for (int i = 0; i < numberElements - LogicIndex; i++) {
            this.data[next(aux)] = this.data[next(aux)];
            aux = prior(aux);
        }
        tail = next(tail);
        */

        // Retrocedendo elementos para gerar espaço vazio
        int aux = head;
        for (int i = 0; i < LogicIndex; i++) {
            this.data[prior(aux)] = this.data[prior(aux)];
            aux = next(aux);
        }
        head = prior(head);

        this.data[aux] = data;
        numberElements++;
    }

    @Override
    public Object delete (int LogicIndex) {
        if (isEmpty()) {
            System.err.println("Lista está vazia!");
        }

        if (LogicIndex < 0 || LogicIndex >= numberElements) {
            System.err.println("Index inválido!");
        }

        int PhysicalIndex = map(LogicIndex);
        int aux = PhysicalIndex;
        Object buffer = this.data[PhysicalIndex]; 
        
        //Delete baseado em Tail
/*
        for (int i = 0; i < numberElements - LogicIndex - 1; i++) {
            data[aux] = data[next(aux)];
            aux = next(aux);
        }
        
        tail = prior(tail);
*/
        //Delete baseado em Head
        for (int i = 0; i < LogicIndex; i++) {
            data[aux] = data[prior(aux)];
            aux = prior(aux);
        }
        
        head = next(head);

        numberElements--;
        return buffer;
    }

    @Override
    public Object select(int LogicIndex) {
        Object temp = null;
        if (isEmpty()) {
            System.err.println("Lista está vazia!");
        } else {
            if (LogicIndex < 0 || LogicIndex >= numberElements) {
                System.err.println("Index inválido!");
            } else {
                int PhysicIndex = map(LogicIndex);
                temp = data[PhysicIndex];
                }
            }
        return temp;
    }

    @Override
    public Object[] selectAll() {
        Object[] buffer = new Object[numberElements];
        int aux = head;
        for(int i = 0; i < numberElements; i++) {
            buffer[i] = data[aux];
            aux = next(aux);
        }
        return buffer;
    }

    @Override
    public void update(Object data, int LogicIndex) {
        if (isEmpty()) {
            System.err.println("Lista está vazia!");
        } else {
            if (LogicIndex < 0 || LogicIndex >= numberElements) {
                System.err.println("Index inválido!");
            } else {
                int PhysicIndex = map(LogicIndex);
                this.data[PhysicIndex] = data;
            }
        }
    }

    @Override
    public void append(Object data) {
        if(isFull()) {
            System.err.println("Lista está cheia!");
        } else {
        next(tail);
        this.data[tail] = data;
        numberElements++;
        }
    }

    @Override
    public void clear() {
        head = 0;
        tail = -1;
        numberElements = 0;
    }

    @Override
    public boolean isEmpty() {
        return numberElements == 0;
    }

    @Override
    public boolean isFull() {
        return numberElements == data.length;
    }

    @Override
    public String print() {
        String buffer = "";
        int aux = head;
        for(int i = 0; i < numberElements; i++) {
            buffer += data[aux];
            aux = next(aux);
            if (i != numberElements - 1) {
                buffer += ", ";
            }
        }
        return "[" + buffer + "]";
    }

    private int next(int index) {
        return (index + 1) % data.length;
    }

    private int prior(int index) {
        return (data.length + index - 1) % data.length;
    }

    private int map(int index) {
        return (head + index) % data.length;
    }
}
