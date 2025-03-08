package bo;

import java.util.List;
import java.util.Objects;

import dao.PessoaDAO;
import generics.GenericBO;
import model.Pessoa;

public class PessoaBO extends GenericBO {
    
    private static final long serialVersionUID = 1L;
    private final PessoaDAO dao;

    public PessoaBO() {
        this.dao = new PessoaDAO();
    }

    public void salvar(Pessoa pessoa) {
        Objects.requireNonNull(pessoa, "A pessoa não pode ser nula.");
        if (pessoa.getNome() == null || pessoa.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da pessoa não pode ser vazio.");
        }
        dao.salvar(pessoa);
    }

    public void atualizar(Pessoa pessoa) {
        Objects.requireNonNull(pessoa, "A pessoa não pode ser nula.");
        Objects.requireNonNull(pessoa.getId(), "ID da pessoa não pode ser nulo para atualização.");
        dao.atualizar(pessoa);
    }

    public void excluir(Long id) {
        dao.excluir(Objects.requireNonNull(id, "ID não pode ser nulo para exclusão."));
    }

    public Pessoa buscarPorId(Long id) {
        return dao.buscarPorId(Objects.requireNonNull(id, "ID não pode ser nulo para busca."));
    }

    public List<Pessoa> listarTodos() {
        return dao.listarTodos();
    }

    public void excluir(Pessoa pessoa) {
        Objects.requireNonNull(pessoa, "A pessoa não pode ser nula.");
        Objects.requireNonNull(pessoa.getId(), "ID não pode ser nulo para exclusão.");
        dao.delete(pessoa);
    }
}
