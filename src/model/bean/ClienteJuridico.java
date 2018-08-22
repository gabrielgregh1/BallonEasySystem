

package model.bean;


public class ClienteJuridico extends Cliente{
    
    private String cnpj;

    public ClienteJuridico(String cnpj,String nome, String dataNascFund, boolean permitecredito, 
            String cepCliente, String ufCliente, String ruaCliente, String numeroCasa,
            String complementoCliente, String bairroCliente, String cidadeCliente, String emailCliente,
            double renda, double limiteCompra, String telefoneCliente) {
        super(nome, dataNascFund, permitecredito, 
             cepCliente, ufCliente, ruaCliente, numeroCasa,
             complementoCliente, bairroCliente, cidadeCliente, emailCliente,
             renda, limiteCompra, telefoneCliente);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    
}
