public class ArrayQueue implements Queueable {
    private Object[] data;
    private int head, tail;

    public ArrayQueue() {
        this(10);
    }

    public ArrayQueue(int lenght) {
        data = new Object[lenght];
        head = 0;
        tail = -1;
    }

    // Métodos Principais
    @Override
    public void enqueue(Object data) {
        if (isFull()) {
            System.err.println("Fila cheia!");
        } else {
            tail++;
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
            head++;
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
        return (head - 1 == tail);
    }

    @Override
    public boolean isFull() {
        return (tail == data.length - 1);
    }

    @Override
    public String print(){
        String buffer = "";
        for (int i = head; i < tail; i++) {
            buffer += data[i];
        }
        return "[" + buffer + "]";
    }
}