package cellmed.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import cellmed.bo.PessoaBO;
import cellmed.model.Pessoa;
import jakarta.faces.view.ViewScoped;

@ManagedBean(value = "pessoaBean")
@ViewScoped
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	private PessoaBO pessoaBO;

	@PostConstruct
	public void init() {
		pessoaBO = new PessoaBO();
		carregarPessoas();
	}

	public void carregarPessoas() {
		pessoas = pessoaBO.listar();
	}

	public Pessoa salvar(Pessoa pessoa) {
		pessoaBO.salvar(pessoa);
		pessoa = new Pessoa();
		carregarPessoas();
		return pessoa;
	}

	public void deletar(Pessoa pessoa) {
		pessoaBO.deletar(pessoa);
		carregarPessoas();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
}
