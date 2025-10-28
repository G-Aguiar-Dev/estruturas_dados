package bsi.estrutura_dados.dao.repository;

public class OverflowException extends RuntimeException{
    public OverflowException(String mensagem) {
        super(mensagem);
    }
}
