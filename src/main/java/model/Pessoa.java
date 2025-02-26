package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pessoa", uniqueConstraints = { @UniqueConstraint(name = "uk_pessoa_email", columnNames = { "email" }),
		@UniqueConstraint(name = "uk_pessoa_cpf", columnNames = { "cpf" }),
		@UniqueConstraint(name = "uk_pessoa_cnpj", columnNames = { "cnpj" }) })
@Inheritance(strategy = InheritanceType.JOINED) // Herança JOINED
public class Pessoa implements IModel {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "nome", nullable = false, length = 150)
	private String nome;

	@Column(name = "email", nullable = false, length = 100)
	private String email;

	@Column(name = "telefone", length = 20)
	private String telefone;

	@Column(name = "endereco", length = 200)
	private String endereco;

	@Column(name = "cpf", length = 14)
	private String cpf;

	@Column(name = "cnpj", length = 18)
	private String cnpj;

	public Pessoa(String nome, String email, String telefone, String endereco, String cpf, String cnpj) {
		super();
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
		this.cpf = cpf;
		this.cnpj = cnpj;
	}

	public Pessoa() {
		super();
	}

	public Pessoa(Pessoa p) {
		super();
		this.id = p.id;
		this.nome = p.nome;
		this.email = p.email;
		this.telefone = p.telefone;
		this.endereco = p.endereco;
		this.cpf = p.cpf;
		this.cnpj = p.cnpj;
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
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}
}
