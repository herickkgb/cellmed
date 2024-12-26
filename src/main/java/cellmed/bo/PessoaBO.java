package cellmed.bo;

import cellmed.dao.PessoaDAO;
import cellmed.model.Pessoa;
import java.util.List;

public class PessoaBO {

    private PessoaDAO pessoaDAO;

    public PessoaBO() {
        this.pessoaDAO = new PessoaDAO();
    }

    public void salvar(Pessoa pessoa) {
        pessoaDAO.salvar(pessoa);
    }

    public void deletar(Pessoa pessoa) {
        pessoaDAO.deletar(pessoa);
    }

    public List<Pessoa> listar() {
        return pessoaDAO.listar();
    }

    public Pessoa buscarPorId(int id) {
        return pessoaDAO.buscarPorId(id);
    }
}
