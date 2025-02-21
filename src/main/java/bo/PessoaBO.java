package bo;
import java.io.Serializable;
import java.util.List;

import dao.PessoaDAO;
import model.Pessoa;

public class PessoaBO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private PessoaDAO dao;

    public PessoaBO() {
        this.dao = new PessoaDAO();
    }

    public void salvar(Pessoa pessoa) {
        if (pessoa.getNome() == null || pessoa.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome da pessoa não pode ser vazio.");
        }
        dao.salvar(pessoa);
    }

    public void atualizar(Pessoa pessoa) {
        if (pessoa.getId() == null) {
            throw new IllegalArgumentException("ID da pessoa não pode ser nulo para atualização.");
        }
        dao.atualizar(pessoa);
    }

    public void excluir(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo para exclusão.");
        }
        dao.excluir(id);
    }

    public Pessoa buscarPorId(Long id) {
        return dao.buscarPorId(id);
    }

    public List<Pessoa> listarTodos() {
        return dao.listarTodos();
    }
}
