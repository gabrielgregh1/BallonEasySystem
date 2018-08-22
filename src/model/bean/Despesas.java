
package model.bean;


public class Despesas {
    
    private int codigo;
    private String tipo;
    private String observacao;
    private String data;
    private double valor;

    public Despesas(String tipo, String observacao, String data, double valor) {
        this.tipo = tipo;
        this.observacao = observacao;
        this.data = data;
        this.valor = valor;
    }

    public Despesas(String tipo, String observacao, double valor) {
        this.tipo = tipo;
        this.observacao = observacao;
        this.valor = valor;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getObservacao() {
        return observacao;
    }

    public String getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }
    
    
    
}
