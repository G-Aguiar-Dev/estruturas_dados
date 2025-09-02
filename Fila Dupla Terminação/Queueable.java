public interface Queueable{
    
    // Métodos Principais
    void BeginEnqueue(Object data);
    void EndEnqueue(Object data);
    Object BeginDequeue();
    Object EndDequeue();
    Object front();
    Object rear();

    // Métodos Auxiliares
    boolean isEmpty();
    boolean isFull();
    String printFrontToRear();
    String printRearToFront();
}