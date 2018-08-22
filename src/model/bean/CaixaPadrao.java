
package model.bean;


public class CaixaPadrao {
    
    private int numero;
    private String dataReferencia ;
    private String historico;
    private double vlrCredito;
    private double vlrDebito;
    private double vlrSaldo;

    public CaixaPadrao(int numero, String dataReferencia, String historico, double vlrCredito, double vlrDebito, double vlrSaldo) {
        this.numero = numero;
        this.dataReferencia = dataReferencia;
        this.historico = historico;
        this.vlrCredito = vlrCredito;
        this.vlrDebito = vlrDebito;
        this.vlrSaldo = vlrSaldo;
    }


    public CaixaPadrao(String historico, double vlrCredito, double vlrDebito) {
        this.historico = historico;
        this.vlrCredito = vlrCredito;
        this.vlrDebito = vlrDebito;
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
