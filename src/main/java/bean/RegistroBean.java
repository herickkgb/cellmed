package bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dao.PessoaDAO;
import dao.UserDAO;
import model.Pessoa;
import model.User;

@ManagedBean
@ViewScoped
public class RegistroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	private String email;
	private String telefone;
	private String endereco;
	private String cpf;
	private String cnpj;
	private String senha;

	private UserDAO usuarioDAO;
	private PessoaDAO pessoaDAO;

	@PostConstruct
	public void init() {
		usuarioDAO = new UserDAO();
		pessoaDAO = new PessoaDAO();
	}

	public String registrar() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cellmed");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try {
			transaction.begin();

			Pessoa pessoa = new Pessoa(nome, email, telefone, endereco, cpf, cnpj);
			em.persist(pessoa);

			User usuario = new User(pessoa, senha);
			usuario.setId(pessoa.getId());
			em.persist(usuario);

			transaction.commit();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro efetuado com sucesso!", ""));

			return "";
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}

			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erro ao registrar: Email, CPF ou CNPJ já em uso", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		} finally {
			em.close();
		}
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UserDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UserDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public PessoaDAO getPessoaDAO() {
		return pessoaDAO;
	}

	public void setPessoaDAO(PessoaDAO pessoaDAO) {
		this.pessoaDAO = pessoaDAO;
	}
}
