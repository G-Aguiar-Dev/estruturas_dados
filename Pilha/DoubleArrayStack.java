public class DoubleArrayStack implements Stackable {
    
    private int pointerTop1, pointerTop2;
    private Object[] data;

    public DoubleArrayStack(){
        this(10);
    }

    public DoubleArrayStack(int lenght){
        data = new Object[lenght];
        pointerTop1 = -1;
        pointerTop2 = data.length;
    }

//Pilha Normal

    @Override
    public boolean isEmpty(){
        return (pointerTop1 == -1);
    }

    @Override
    public boolean isFull(){
        return (pointerTop1 + 1 == pointerTop2);
    }

    @Override
    public String print(){
        String buffer = "";
        for (int i = 0; i < pointerTop1; i++) {
            buffer += data[i];
        }
        return buffer;
    }

    @Override
    public Object peek(){
        if (isEmpty()){
            System.out.println("Pilha vazia");
        }
        return data[pointerTop1];
    }

    @Override
    public Object pop(){
        Object buffer = null;
        if (isEmpty()){
            System.out.println("Pilha vazia");
        } else {
            buffer = data[pointerTop1];
            pointerTop1--;
        }
        return buffer;
    }

    @Override
    public void push(Object buffer){
        if (isFull()){
            System.out.println("Pilha cheia!");
        } else {
        pointerTop1++;
        data[pointerTop1] = buffer;
        }
    }

// Pilha Invertida

    public boolean isEmpty2(){
        return (pointerTop2 == data.length);
    }

    public boolean isFull2(){
        return (isFull());
    }

    public String print2(){
        String buffer = "";
        for (int i = data.length; i < pointerTop2; i--) {
            buffer += data[i];
        }
        return buffer;
    }

    public Object peek2(){
        if (isEmpty()){
            System.out.println("Pilha vazia");
        }
        return data[pointerTop2];
    }

    public Object pop2(){
        Object buffer = null;
        if (isEmpty()){
            System.out.println("Pilha vazia");
        } else {
            buffer = data[pointerTop2];
            pointerTop2++;
        }
        return buffer;
    }

    public void push2(Object buffer){
        if (isFull()){
            System.out.println("Pilha cheia!");
        } else {
        pointerTop2--;
        data[pointerTop2] = buffer;
        }
    }
}
