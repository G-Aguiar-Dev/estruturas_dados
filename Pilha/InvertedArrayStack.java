public class InvertedArrayStack implements Stackable{
    
    private int pointerTop;
    private Object[] data;

    public InvertedArrayStack (){
        this(10);
    }

    public InvertedArrayStack (int lenght){
        data = new Object[lenght];
        pointerTop = lenght;
    }

    @Override
    public boolean isEmpty(){
        return (pointerTop == data.length);
    }

    @Override
    public boolean isFull(){
        return (pointerTop == 0);
    }

    @Override
    public String print(){
        String buffer = "";
        for (int i = data.length; i < pointerTop; i--) {
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
            pointerTop++;
        }
        return buffer;
    }

    @Override
    public void push(Object buffer){
        if (isFull()){
            System.out.println("Pilha cheia!");
        } else {
        pointerTop--;
        data[pointerTop] = buffer;
        }
    }
}
