package bsi.estrutura_dados.dao.repository;

public class UnderflowException extends RuntimeException {
    public UnderflowException(String mensagem) {
        super(mensagem);
    }
}
