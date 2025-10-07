public interface Stackable<T> {
    
    // Métodos Principais
    T pop();
    void push(T buffer);
    void update(T newData);
    T peek();

    // Métodos Auxiliares
    boolean isEmpty();
    boolean isFull();
    String print();
}
