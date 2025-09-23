public class MaxBinaryTreeHeap implements Heap {
    private int tail;
    private Object[] data;

    public MaxBinaryTreeHeap() {
        this(7);
    }
    public MaxBinaryTreeHeap(int lenght) {
        tail = -1;
        data = new Object[lenght];
    }
    @Override
    public void enqueue(Object data) {
        if (isFull()) {
            System.err.println("Lista está cheia!");
        } else {
            tail++;
            this.data[tail] = data;
            heapifyUp(tail);
        }
    }

    @Override
    public Object dequeue() {
        Object temp = null;

        if (isEmpty()) {
            System.err.println("Lista está vazia!");
        } else {
            temp = data[0];
            data[0] = data[tail];
            tail--;
            heapifyDown(0);
        }
        return temp;
    }

    @Override
    public Object front() {
        Object temp = null;
        if (isEmpty()) {
            System.err.println("Fila está vazia!");
        } else {
            temp = data[0];
        }
        return temp;
    }

    @Override
    public boolean isEmpty() {
        return tail == -1;
    }

    @Override
    public boolean isFull() {
        return tail == data.length - 1;
    }

    private void heapifyUp(int index) {
        int temp = parent(index);
        while ((Integer)data[index] > (Integer)data[temp]) {
            swap(index, temp);
            index = temp;
            temp = parent(index);
        }
    }

    public Long priorityAdjust (int initialPriority) {
        long hour = System.currentTimeMillis();
        long newPriority = 99_999_999_999_999L - hour;
        return initialPriority * 1_000_000_000_000_000L + newPriority;
    }

    private void heapifyDown (int index) {
        boolean adjusted = false;
        while (!adjusted) {
            int left = leftSon(index);
            int right = rightSon(index);
            int maior = index;

            if ((Integer)data[left] > (Integer)data[maior] && left <= tail) {
                maior = left;
            }

            if ((Integer)data [right] > (Integer)data[maior] && right <= tail) {
                maior = right;
            }

            if (maior != index) {
                swap(maior, index);
            } else {
                adjusted = true;
            }
        }
    }

    private int parent(int child) {
        return (child - 1) / 2; 
    }

    private int leftSon(int parent) {
        return parent * 2 + 1;
    }

    private int rightSon(int parent) {
        return parent * 2 + 2;
    }

    private void swap(int index1, int index2) {
        Object temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;
    }
}
