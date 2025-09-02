public interface Queueable{
    
    // Métodos Principais
    void enqueue(Object data);
    Object dequeue();
    Object front();

    // Métodos Auxiliares
    boolean isEmpty();
    boolean isFull();
    String print();
}