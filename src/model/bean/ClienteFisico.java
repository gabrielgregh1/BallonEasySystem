/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author gabri
 */
public class ClienteFisico extends Cliente{
    private String cpf; 
    private String sexo;
    private String rg;
    private String orgaoExpeditor;
    private String mae;
    private String pai;


    public ClienteFisico(String cpf, String sexo, String rg, String orgaoExpeditor, String mae, String pai,String nome, String dataNascFund, boolean permitecredito, 
            String cepCliente, String ufCliente, String ruaCliente, String numeroCasa,
            String complementoCliente, String bairroCliente, String cidadeCliente, String emailCliente,
            double renda, double limiteCompra, String telefoneCliente) {
        
        super( nome,  dataNascFund,  permitecredito, 
             cepCliente,  ufCliente,  ruaCliente,  numeroCasa,
             complementoCliente,  bairroCliente,  cidadeCliente,  emailCliente,
             renda,  limiteCompra,  telefoneCliente);
        this.cpf = cpf; 
        this.sexo = sexo;
        this.rg = rg;
        this.orgaoExpeditor = orgaoExpeditor;
        this.mae = mae;
        this.pai = pai;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public String getRg() {
        return rg;
    }

    public String getOrgaoExpeditor() {
        return orgaoExpeditor;
    }

    public String getMae() {
        return mae;
    }

    public String getPai() {
        return pai;
    }

}
