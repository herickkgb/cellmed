package bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import bo.PessoaBO;
import model.Pessoa;

@ManagedBean
@SessionScoped
public class PessoaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private PessoaBO pessoaBO;
	private Pessoa pessoa;
	private List<Pessoa> pessoas;

	@PostConstruct
	public void init() {
		pessoaBO = new PessoaBO();
		pessoa = new Pessoa();
		carregarPessoas();
	}

	public void salvar() {
		pessoaBO.salvar(pessoa);
		pessoa = new Pessoa();
		carregarPessoas();
	}

	public void atualizar() {
		try {
			if (pessoa != null) {
				pessoaBO.atualizar(pessoa);
				carregarPessoas();
				
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Pessoa Atualizada com sucesso!!", null));
			} else {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Nenhuma pessoa selecionada!", null));
			}
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao Atualizar a pessoa: " + e.getMessage(), null));
		}
	}

	public void excluir(Pessoa pessoa) {
		try {
			if (pessoa != null) {
				pessoaBO.excluir(pessoa);
				carregarPessoas();
				
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Pessoa excluída com sucesso!!", null));
			} else {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Nenhuma pessoa selecionada!", null));
			}
		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir a pessoa: " + e.getMessage(), null));
		}
	}

	public void carregarPessoas() {
		pessoas = pessoaBO.listarTodos();
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

}
