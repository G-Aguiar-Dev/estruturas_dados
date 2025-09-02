public class ArrayStack implements Stackable{
    
    private int pointerTop;
    private Object[] data;

    public ArrayStack (){
        this(10);
    }

    public ArrayStack (int lenght){
        data = new Object[lenght];
        pointerTop = -1;
    }

    @Override
    public boolean isEmpty(){
        return (pointerTop == -1);
    }

    @Override
    public boolean isFull(){
        return (pointerTop == (data.length - 1));
    }

    @Override
    public String print(){
        String buffer = "";
        for (int i = 0; i < pointerTop; i++) {
            buffer += data[i];
        }
        return buffer;
    }

    @Override
    public Object peek(){
        if (isEmpty()){
            System.out.println("Pilha vazia");
        }
        return data[pointerTop];
    }

    @Override
    public Object pop(){
        Object buffer = null;
        if (isEmpty()){
            System.out.println("Pilha vazia");
        } else {
            buffer = data[pointerTop];
            pointerTop--;
        }
        return buffer;
    }

    @Override
    public void push(Object buffer){
        if (isFull()){
            System.out.println("Pilha cheia!");
        } else {
        pointerTop++;
        data[pointerTop] = buffer;
        }
    }
}
