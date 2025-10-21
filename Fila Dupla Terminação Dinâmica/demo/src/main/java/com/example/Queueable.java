public interface Queueable<T>{
    
    // Métodos Principais
    void BeginEnqueue(T data);
    void EndEnqueue(T data);
    T BeginDequeue();
    T EndDequeue();
    T front();
    T rear();

    // Métodos Auxiliares
    boolean isEmpty();
    boolean isFull();
    String printFrontToRear();
    String printRearToFront();
}