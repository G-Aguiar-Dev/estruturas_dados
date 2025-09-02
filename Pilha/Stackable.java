public interface Stackable {
    
    // Métodos Principais
    Object pop();
    void push(Object buffer);
    Object peek();

    // Métodos Auxiliares
    boolean isEmpty();
    boolean isFull();
    String print();
}
