package vo;

import java.util.Objects;

public class PessoaVO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private String cpf;
    private String cnpj;

    // Construtores
    public PessoaVO() {}

    public PessoaVO(Long id, String nome, String email, String telefone, String endereco, String cpf, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cpf = cpf;
        this.cnpj = cnpj;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    // hashCode e equals, baseados no id
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PessoaVO other = (PessoaVO) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "PessoaVO [id=" + id + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", endereco=" + endereco + "]";
    }
}
