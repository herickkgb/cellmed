package bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import bo.PessoaBO;
import model.Pessoa;

@ManagedBean
@ViewScoped
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
        try {
            pessoaBO.salvar(pessoa);
            pessoa = new Pessoa();
            carregarPessoas();
            adicionarMensagem(FacesMessage.SEVERITY_INFO, "Pessoa salva com sucesso!");
        } catch (Exception e) {
            adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao salvar a pessoa: " + e.getMessage());
        }
    }

    public void editar(Pessoa p) {
        this.pessoa = p;
    }

    public void atualizar() {
        if (pessoa == null) {
            adicionarMensagem(FacesMessage.SEVERITY_WARN, "Nenhuma pessoa selecionada!");
            return;
        }
        try {
            pessoaBO.atualizar(pessoa);
            carregarPessoas();
            adicionarMensagem(FacesMessage.SEVERITY_INFO, "Pessoa atualizada com sucesso!");
        } catch (Exception e) {
            adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar a pessoa: " + e.getMessage());
        }
    }

    public void excluir(Pessoa pessoa) {
        if (pessoa == null) {
            adicionarMensagem(FacesMessage.SEVERITY_WARN, "Nenhuma pessoa selecionada!");
            return;
        }
        try {
            pessoaBO.excluir(pessoa);
            carregarPessoas();
            adicionarMensagem(FacesMessage.SEVERITY_INFO, "Pessoa excluída com sucesso!");
        } catch (Exception e) {
            adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao excluir a pessoa: " + e.getMessage());
        }
    }

    private void adicionarMensagem(FacesMessage.Severity severidade, String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severidade, mensagem, null));
    }

    private void carregarPessoas() {
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
