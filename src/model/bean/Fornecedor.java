
package model.bean;

public class Fornecedor {
    private String razaoSocial;
    private String tipo;
    private String nomeInstituicao; 
    private String cep;
    private String uf;
    private String cidade; 
    private String bairro; 
    private String rua; 
    private String numero;
    private String complemento;
    private String email; 
    private String fixo;
    private String celular;
    private int codigo;
    private String cnpj;

    public Fornecedor(int codigo,String razaoSocial, String nomeInstituicao, String cep,
            String uf, String cidade, String bairro, String rua, String numero, String complemento,
            String email, String fixo, String celular, String cnpj, String tipo) {
        this.razaoSocial = razaoSocial;
        this.nomeInstituicao = nomeInstituicao;
        this.cep = cep;
        this.uf = uf;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.email = email;
        this.fixo = fixo;
        this.celular = celular;
        this.codigo = codigo;
        this.cnpj = cnpj;
        this.tipo = tipo;
    }
    
    public Fornecedor(int codigo, String nomeInstituicao) {
        this.codigo = codigo;
        this.nomeInstituicao = nomeInstituicao;
    } 

    public String getTipo() {
        return tipo;
    }

    public String getCnpj() {
        return cnpj;
    }
    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getNomeInstituicao() {
        return nomeInstituicao;
    }

    public String getCep() {
        return cep;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getUf() {
        return uf;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }


    public String getEmail() {
        return email;
    }

    public String getFixo() {
        return fixo;
    }

    public String getCelular() {
        return celular;
    }


}
