
package model.bean;


public class FormaPagamento {
    
    private String tipoPagamento;
    private String valor;

    public FormaPagamento(String tipoPagamento, String valor) {
        this.tipoPagamento = tipoPagamento;
        this.valor = valor;
    }
    
    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public String getValor() {
        return valor;
    }
    
    
    
}
