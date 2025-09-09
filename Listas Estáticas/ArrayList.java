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
