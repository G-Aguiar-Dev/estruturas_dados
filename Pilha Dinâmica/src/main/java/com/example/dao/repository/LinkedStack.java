package com.example.dao.repository;

import java.util.NoSuchElementException;

/**
 * Implementação de uma pilha dinâmica
 * @param <T> tipo de dado que será armazenado na pilha
 * @author Guilherme Aguiar Silva
 * @version 1.0
 * @since 06/10/2025
 */

public class LinkedStack<T> implements Stackable<T>{
    /** Aponta para o topo da pilha */
    private DoubleNode<T> topPointer;
    /** Número de elementos na pilha */
    private int numberElements;
    /** Número máximo de elementos pode possuir */
    private int maxElements;

    /**
     * Construtor vazio, cria uma pilha com até 10 slots
     */
    public LinkedStack() {
        this(10);
    }

    /**
     * Construtor
     * @param maxElements, contém o número máximo de elementos que pode conter a pilha
     */
    public LinkedStack(int maxElements) {
        topPointer = null;
        numberElements = 0;
        this.maxElements = maxElements;
    }

    /**
     * Remove e retorna o elemento do topo da pilha.
	 *
	 * @return o elemento removido do topo
	 * @throws NoSuchElementException se a pilha estiver vazia
     */
    @Override
    public T pop() {
        T buffer = null;
        if (isEmpty()) {
            throw new NoSuchElementException("Pilha está vazia"); 
        } else {
            buffer = topPointer.getData();
            topPointer = topPointer.getPrevious();
            if (!isEmpty()) {
			topPointer.setNext(null);
		    }
            numberElements--;
            }
        return buffer;
    }

    /**
     * Empilha o elemento na pilha
     * 
     * @param data, dado a ser empilhado
     */
    @Override
    public void push(T data) {
		if (isFull()) {
			throw new NoSuchElementException("Pilha Cheia!");
		}
		DoubleNode<T> newNode = new DoubleNode<T>();
		newNode.setData(data);
        topPointer.setNext(newNode);
		newNode.setPrevious(topPointer);
		topPointer = newNode;
		numberElements++;	
    }   
    
    /**
     * Atualiza o elemento do topo
     * 
     * @param newData
     */
    @Override
    public void update(T newData) {
        pop();
        push(newData);
    }

    /**
     * Retorna o elemento do topo da pilha sem removê-lo.
	 *
	 * @return o elemento do topo
	 * @throws NoSuchElementException se a pilha estiver vazia
	 */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Pilha está vazia"); 
        } else {
            return topPointer.getData();
        }
    }

    /**
     * Verifica se a pilha está vazia
     * 
     * @return retorna true se a pilha estiver vazia
     */
    @Override
    public boolean isEmpty() {
        return numberElements == 0;
    };

    /**
     * Verifica se a pilha está cheia
     * 
     * @return retorna true se a pilha estiver cheia
     */
    @Override
    public boolean isFull() {
        return numberElements == maxElements;
    };

    /**
     * Imprime os elementos da pilha
     * 
     * @return String com os dados entre colchetes separados por vírgula
     */
    @Override
    public String print() {
        String result = "";
        DoubleNode<T> buffer = topPointer;
        for (int i = 0; i < numberElements; i++) {
            result += buffer.getData();
            buffer = buffer.getPrevious();
            if (i != numberElements - 1) {
                result += ",";
            }
        }
        return "[" + result + "]";
    }
}
