package cellmed.model;

import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "tb_pessoa")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nome", nullable = false)
	@NotNull
	private String nome;

	@Column(name = "documento", nullable = false)
	@NotNull
	private String documento;

	@ManyToOne
	@JoinColumn(name = "endereco_id", referencedColumnName = "id", nullable = false)
	private Endereco endereco;

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Aparelho> aparelhos;

	public Pessoa() {
	}

	public Pessoa(int id, String nome, String documento, Endereco endereco, List<Aparelho> aparelhos) {
		this.id = id;
		this.nome = nome;
		this.documento = documento;
		this.endereco = endereco;
		this.aparelhos = aparelhos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Aparelho> getAparelhos() {
		return aparelhos;
	}

	public void setAparelhos(List<Aparelho> aparelhos) {
		this.aparelhos = aparelhos;
	}

	// Método para determinar o tipo de documento (CPF, CNPJ ou Estrangeiro)
	public TipoDocumento tipoDocumento() {
		if (documento != null) {
			if (documento.length() == 11) {
				return TipoDocumento.CPF;
			} else if (documento.length() == 14) {
				return TipoDocumento.CNPJ;
			}
		}
		return TipoDocumento.ESTRANGEIRO;
	}

	// Método para validar o CPF ou CNPJ
	public String validarCpfCnpj() {
		TipoDocumento tipo = tipoDocumento();
		if (tipo == TipoDocumento.CPF) {
			return Util.validarCpf(documento) ? "CPF válido" : "CPF inválido";
		} else if (tipo == TipoDocumento.CNPJ) {
			return Util.validarCnpj(documento) ? "CNPJ válido" : "CNPJ inválido";
		} else {
			return "Documento estrangeiro (sem validação)";
		}
	}
}
