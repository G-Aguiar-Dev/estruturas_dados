public interface Listable {
    
    // Métodos Principais
    
    void insert(Object data, int LogicIndex);   //Inserir
    void append(Object data);                   //Anexar

    Object select(int LogicIndex);              //Selecionar
    Object[] selectAll();                       //Selecionar tudo

    void update(Object data, int LogicIndex);   //Atualizar

    Object delete(int LogicIndex);              //Deletar
    void clear();                               //Limpar lista

    //Métodos Auxiliares

    boolean isEmpty();
    boolean isFull();
    String print();
}
