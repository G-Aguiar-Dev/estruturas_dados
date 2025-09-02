public class ArrayQueue implements Queueable {
    private Object[] data;
    private int head, tail, numberElements;

    public ArrayQueue() {
        this(10);
    }

    public ArrayQueue(int lenght) {
        data = new Object[lenght];
        head = 0;
        tail = -1;
        numberElements = 0;
    }

    // Métodos Principais
    @Override
    public void BeginEnqueue(Object data) {
        if (isFull()) {
            System.err.println("Fila cheia!");
        } else {
            head = prior(head);
            numberElements++;
            this.data[head] = data;
        }
    }

    @Override
    public void EndEnqueue(Object data) {
        if (isFull()) {
            System.err.println("Fila cheia!");
        } else {
            tail = next(tail);
            numberElements++;
            this.data[tail] = data;
        }
    }

    @Override
    public Object BeginDequeue() {
        Object buffer = null;
        if (isEmpty()) {
            System.err.println("Fila vazia!");
        } else {
            buffer = data[head];
            head = next(head);
            numberElements--;
        }
        return buffer;
    }

    @Override
    public Object EndDequeue() {
        Object buffer = null;
        if (isEmpty()) {
            System.err.println("Fila vazia!");
        } else {
            buffer = data[tail];
            tail = prior(tail);
            numberElements--;
        }
        return buffer;
    }

    @Override
    public Object front() {
        Object buffer = null;
        if (isEmpty()) {
            System.err.println("Fila vazia!");
        } else {
            buffer = data[head];
        }
        return buffer;
    }

    @Override
    public Object rear() {
        Object buffer = null;
        if (isEmpty()) {
            System.err.println("Fila vazia!");
        } else {
            buffer = data[tail];
        }
        return buffer;
    }

    // Métodos Auxiliares
    @Override
    public boolean isEmpty() {
        return (numberElements == 0);
    }

    @Override
    public boolean isFull() {
        return (numberElements == data.length);
    }

    @Override
    public String printFrontToRear(){
        String buffer = "";
        int temp = head;

        for (int i = 0; i < numberElements; i++) {
            buffer += data[temp];
            temp = next(temp);    
        }
        return "[" + buffer + "]";
    }

    @Override
    public String printRearToFront(){
        String buffer = "";
        int temp = tail;

        for (int i = 0; i < numberElements; i++) {
            buffer += data[temp];
            temp = prior(temp);    
        }
        return "[" + buffer + "]";
    }

    private int next (int temp) {
        return ((temp + 1) % data.length);
    }

    private int prior (int temp) {
        return (((temp - 1) + data.length) % data.length);
    }
}
