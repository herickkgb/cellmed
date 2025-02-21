package bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
        pessoaBO.salvar(pessoa);
        pessoa = new Pessoa();
        carregarPessoas();
    }

    public void atualizar() {
        pessoaBO.atualizar(pessoa);
        carregarPessoas();
    }

    public void excluir(Long id) {
        pessoaBO.excluir(id);
        carregarPessoas();
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
