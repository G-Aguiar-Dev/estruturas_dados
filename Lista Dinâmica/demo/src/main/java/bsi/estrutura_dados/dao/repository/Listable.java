package bsi.estrutura_dados.dao.repository;

public interface Listable<T> {
    
    // Métodos Principais
    
    void insert(T data, int index);         //Inserir
    void append(T data);                    //Anexar
    T select(int index);                    //Selecionar
    T[] selectAll();                        //Selecionar tudo
    void update(T data, int index);         //Atualizar
    T delete(int index);                    //Deletar
    void clear();                           //Limpar lista

    //Métodos Auxiliares

    int size();                            
    boolean isEmpty();
    boolean isFull();
    String print();
}
