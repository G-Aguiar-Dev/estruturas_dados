package bsi.estrutura_dados.dao.repository;

public class LinkedList<T> implements Listable<T>{
    private DoubleNode<T> head;
    private DoubleNode<T> tail;
    private int capacity;
    private int numberElements;

    public LinkedList() {
        this(10);
    }

    public LinkedList(int capacity) {
        head = null;
        tail = null;
        this.capacity = capacity;
        numberElements = 0;
    }

    @Override
    public void insert(T data, int index) {
        if (isFull()) {
            throw new OverflowException("Lista cheia!");
        }
        if (index < 0 || index >= numberElements){
            throw new IndexOutOfBoundsException("Índice inválido: " + index);
        }
        DoubleNode<T> previous = null;
        DoubleNode<T> next = head;
        DoubleNode<T> newNode = new DoubleNode<>();
        newNode.setData(data);
        for (int i = 0; i < index; i++) {
            previous = next;
            next = next.getNext();
        }
        if (previous != null) {
            newNode.setPrevious(previous);
            previous.setNext(newNode);
        } else {
            head = newNode;
        }
        if (next != null) {
            newNode.setNext(next);
            next.setPrevious(newNode);
        } else {
            tail = newNode;
        }
        numberElements++;
    }

    @Override
    public void append(T data) {
        if (isFull()) {
            throw new OverflowException("Lista Cheia!"); 
        }

        DoubleNode<T> newNode = new DoubleNode<>();
        newNode.setData(data);

        if (!isEmpty()) {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
        } else {
            head = newNode;
            tail = newNode;
        }
        numberElements++;
    }

    @Override
    public T select(int index) {
        if (isEmpty()) {
            throw new UnderflowException("Lista Vazia!");
        }
		if (index < 0 || index >= numberElements) {
			throw new IndexOutOfBoundsException("Índice inválido: " + index);
		}

        return getNodeAt(index).getData();
	}

    @Override
    public T[] selectAll() {
        Object[] buffer = new Object[size()];
        DoubleNode<T> aux = head;
        for (int i = 0; i < numberElements; i++) {
            buffer[i] = aux.getData();
            aux = aux.getNext();
        }
        return (T[]) buffer;
    }

    @Override
    public void update(T data, int index) {
        if (isEmpty()) {
            throw new UnderflowException("Lista Vazia!");
        }
		
        if (index < 0 || index >= numberElements) {
			throw new IndexOutOfBoundsException("Índice inválido: " + index);
		}

		getNodeAt(index).setData(data);
    }

    @Override
    public T delete(int index) {
        if (isEmpty()) {
            throw new UnderflowException("Lista vazia!");
        }

        if (index < 0 || index > capacity) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }

        DoubleNode<T> aux = getNodeAt(index);

        if (numberElements == 1) {
            head = null;
            tail = null;
        } else if (index == 0) {
            aux.getNext().setPrevious(null);
            head = aux.getNext();
            } else if (index == numberElements - 1) {
                aux.getPrevious().setNext(null);
                tail = aux.getPrevious();
            } else {
                aux.getPrevious().setNext(aux.getNext());
                aux.getNext().setPrevious(aux.getPrevious());
            }

        numberElements--;
        return aux.getData();
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        numberElements = 0;
    }

    @Override
    public int size() {
        return numberElements;
    }

    @Override
    public boolean isEmpty() {
        return numberElements == 0;
    }

    @Override
    public boolean isFull() {
        return numberElements == capacity;
    }

    @Override
    public String print() {
        String buffer = "";
        DoubleNode<T> aux = head;
        for (int i = 0; i < numberElements; i++) {
            buffer += aux.getData();
            if (i != numberElements - 1) {
                buffer += ","; 
            }
            aux = aux.getNext();
        }
        return "[" + buffer + "]";
    }

    private DoubleNode<T> getNodeAt(int index) {
		DoubleNode<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current;
    }
}
