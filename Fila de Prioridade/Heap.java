public interface Heap {

    // Métodos Principais
    void enqueue(Object data);
    Object dequeue();
    Object front();


    // Métodos Auxiliares

    //int parent(int Index);      //Menos 1 dividido por 2
    //int leftSon(int parent);    //Vezes 2 + 1
    //int rightSon(int parent);   //Vezes 2 + 2
    //void heapifydown(int index);
    //void heapifyup(int index);
    boolean isEmpty();
    boolean isFull();
    void print();
}
