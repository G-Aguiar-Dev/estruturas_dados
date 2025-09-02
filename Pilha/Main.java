import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int escolha = -1;
        ArrayStack data = new ArrayStack(2);
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Menu:");
        System.out.println("1- Adicionar elemento a pilha");
        System.out.println("2- Verificar elemento do topo da pilha");
        System.out.println("3- Remover elemento do topo da pilha");
        System.out.println("0- Sair do menu");
        escolha = scanner.nextInt();

        while (escolha != 0){
            switch (escolha) {
                case 1:
                    System.out.println("Digite o elemento:");
                    Object elemento = scanner.nextLine();
                    scanner.nextLine();
                    data.push(elemento);
                    break;
                case 2:
                    data.peek();
                    break;
                case 3:
                    elemento = data.pop();
                    System.out.println("Elemento retirado:");
                    System.out.println(elemento);
                    break;
                default:
                    break;
            }
        }
    }
}
