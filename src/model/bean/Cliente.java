
package model.bean;


public abstract class Cliente {
    private String nome;
    private String dataNascFund;
    private boolean permitecredito;
    private String cep;
    private String uf;
    private String rua;
    private String numeroCasa;
    private String complemento;
    private String bairro;
    private String cidade;
    private String email;
    private double renda;
    private double limiteCompra;
    private String telefone;

    public  Cliente(String nome, String dataNascFund, boolean permitecredito, 
            String cepCliente, String ufCliente, String ruaCliente, String numeroCasa,
            String complementoCliente, String bairroCliente, String cidadeCliente, String emailCliente,
            double renda, double limiteCompra, String telefoneCliente) {
        this.nome = nome;
        this.dataNascFund= dataNascFund;
        this.permitecredito = permitecredito;
        this.cep = cepCliente;
        this.uf = ufCliente;
        this.rua = ruaCliente;
        this.numeroCasa = numeroCasa;
        this.complemento = complementoCliente;
        this.bairro = bairroCliente;
        this.cidade = cidadeCliente;
        this.email = emailCliente;
        this.renda = renda;
        this.limiteCompra = limiteCompra;
        this.telefone = telefoneCliente;
    }


    public String getNome() {
        return nome;
    }

    public String getDataNascFund() {
        return dataNascFund;
    }

    public boolean isPermitecredito() {
        return permitecredito;
    }

    public String getCep() {
        return cep;
    }

    public String getUf() {
        return uf;
    }

    public String getRua() {
        return rua;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEmail() {
        return email;
    }

    public double getRenda() {
        return renda;
    }

    public double getLimiteCompra() {
        return limiteCompra;
    }

    public String getTelefone() {
        return telefone;
    }
    
    
}
