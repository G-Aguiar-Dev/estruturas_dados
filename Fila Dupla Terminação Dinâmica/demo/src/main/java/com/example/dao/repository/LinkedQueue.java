import java.util.NoSuchElementException;

import com.example.dao.repository.DoubleNode;

/**
 * Implementação de uma fila de dupla terminação genérica.
 * 
 * @param <T> Tipo de elementos armazenado na fila
 * @author Guilherme Aguiar Silva
 * @version 1.0
 */


public class LinkedQueue<T> implements Queueable<T> {
    /** Quantidade de elementos na fila */
    private int numberElements = 0;
    /** Tamanho máximo da fila */
    private int capacity;
    /** Primeiro nó da fila */
    private DoubleNode<T> head;
    /** Último nó da fila */
    private DoubleNode<T> tail;

    /**
     * Construtor padrão para criação de uma fila com capacidade para 10 elementos
     */
    public LinkedQueue() {
        this(10);
    }

    /**
     * Construtor que cria uma fila com capacidade definida na chamada do construtor
     * @param capacity Limite da fila
     */
    public LinkedQueue(int capacity) {
        numberElements = 0;
        this.capacity = capacity;
        head = null;
        tail = null;
    }

    /**
     * Método para colocar elementos no início da fila
     * @param data elemento a ser adicionado na fila
     */
    @Override
    public void BeginEnqueue(T data) {
        if (isFull()) {
            throw new NoSuchElementException("Fila Cheia!");
        }
        
        DoubleNode<T> newNode = new DoubleNode();
        newNode.setData(data);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = head.getPrevious();
        }
        numberElements++;
    }

    /**
     * Método para colocar elementos no final da fila
     * @param data elemento a ser adicionado na fila
     */
    @Override
    public void EndEnqueue(T data) {
        if (isFull()) {
            throw new NoSuchElementException("Fila Cheia!");
        }

        DoubleNode<T> newNode = new DoubleNode();
        newNode.setData(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = tail.getNext();
        }
        numberElements++;
    }

    /**
     * Método para remoção do elemento no início da fila
     * @return buffer: dado removido
     */
    @Override
    public T BeginDequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Fila vazia!");
        }
        T buffer = head.getData();
        head = head.getNext();
        if (!isEmpty()) {
            head.setPrevious(null);
        } else {
            tail = null;
        }
        numberElements--;
        return buffer;
    }

    /**
     * Método para remoção do elemento no final da fila
     * @return buffer: dado removido
     */
    @Override
    public T EndDequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Fila vazia!");
        }
        T buffer = tail.getData();
        tail = tail.getPrevious();
        if (!isEmpty()){
            tail.setNext(null);
        } else {
            head = null;
        }
        numberElements--;
        return buffer;
    }

    /**
     * Método para retornar o elemento no início da fila
     * @return head.getData(): elemento presente no nó head
     */
    @Override
    public T front() {
        if (isEmpty()) {
            throw new NoSuchElementException("Fila vazia!");
        }
        return head.getData();
    }
    
    /**
     * Método para retornar o elemento no final da fila
     * @return tail.getData(): elemento presente no nó tail
     */
    @Override
    public T rear() {
        if (isEmpty()) {
            throw new NoSuchElementException("Fila vazia!");
        }
        return tail.getData();
    }

    /**
     * Método para mostrar elementos da fila partindo do head para o tail
     * @return buffer: elementos da lista
     */
    @Override
    public String printFrontToRear() {
        String buffer = "";
        DoubleNode aux = head;
        for (int i = 0; i < numberElements; i++) {
            buffer += aux.getData();
            aux = aux.getNext();
            if (i != numberElements - 1) {
                buffer += ", ";
            }
        }
        return buffer;
    }

    /**
     * Método para mostrar elementos da fila partindo do tail para o head
     * @return buffer: elementos da lista
     */
    @Override
    public String printRearToFront() {
        String buffer = "";
        DoubleNode aux = tail;
        for (int i = 0; i < numberElements; i++) {
            buffer += aux.getData();
            aux = aux.getPrevious();
            if (i != numberElements - 1) {
                buffer += ", ";
            }
        }
        return buffer;
    }

    /**
     * Retorna o estado da fila de acordo ao fato de estar cheia ou não
     * @return true se a fila estiver cheia, caso contrário retorna false true se a fila estiver cheia, caso contrário retorna false
     */
    @Override
    public boolean isFull() {
        return numberElements == capacity;
    }

    /**
     * Retorna o estado da fila de acordo ao fato de estar vazia ou não
     * @return true se a fila estiver vazia, caso contrário retorna false
     */
    @Override
    public boolean isEmpty() {
        return numberElements == 0;
    }
}
