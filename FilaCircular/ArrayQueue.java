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
    public void enqueue(Object data) {
        if (isFull()) {
            System.err.println("Fila cheia!");
        } else {
            tail = next(tail);
            numberElements++;
            this.data[tail] = data;
        }
    }

    @Override
    public Object dequeue() {
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
    public Object front() {
        Object buffer = null;
        if (isEmpty()) {
            System.err.println("Fila vazia!");
        } else {
            buffer = data[head];
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
    public String print(){
        String buffer = "";
        int temp = head;

        for (int i = 0; i < numberElements; i++) {
            buffer += data[temp];
            temp = next(temp);    
        }
        return "[" + buffer + "]";
    }

    private int next (int head) {
        return ((head + 1) % data.length);
    }
}
