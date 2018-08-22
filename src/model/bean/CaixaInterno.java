

package model.bean;



public class CaixaInterno {
    private int numero;
    private String dataReferencia ;
    private String historico;
    private double vlrCredito;
    private double vlrDebito;
    private double vlrSaldo;

    public CaixaInterno(int numero, String dataReferencia, String historico, double vlrCredito, double vlrDebito, double vlrSaldo) {
        this.numero = numero;
        this.dataReferencia = dataReferencia;
        this.historico = historico;
        this.vlrCredito = vlrCredito;
        this.vlrDebito = vlrDebito;
        this.vlrSaldo = vlrSaldo;
    }
    
    public CaixaInterno(String dataReferencia, String historico, double vlrCredito, double vlrDebito, double vlrSaldo) {
        this.dataReferencia = dataReferencia;
        this.historico = historico;
        this.vlrCredito = vlrCredito;
        this.vlrDebito = vlrDebito;
        this.vlrSaldo = vlrSaldo;
    }

    public CaixaInterno(String historico, double vlrCredito) {
        this.historico = historico;
        this.vlrCredito = vlrCredito;
    }

    public int getNumero() {
        return numero;
    }

    public String getDataReferencia() {
        return dataReferencia;
    }

    public String getHistorico() {
        return historico;
    }

    public double getVlrCredito() {
        return vlrCredito;
    }

    public double getVlrDebito() {
        return vlrDebito;
    }

    public double getVlrSaldo() {
        return vlrSaldo;
    }
    
    
    
}
