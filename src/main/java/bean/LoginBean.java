package bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.UserDAO;
import model.Pessoa;
import model.User;

@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public String telaInicial() {
        return "/PesquisaPessoa.xhtml?faces-redirect=true";
    }

	private String email;
	private String password;

	private UserDAO userDAO;
	
	@PostConstruct
	public void init() {
		userDAO = new UserDAO();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		Pessoa pessoa = userDAO.findByEmail(email);
		User user =  userDAO.findById(pessoa.getId());
		
		if(user!= null && pessoa != null && pessoa.getId() != null) {
			user.setPessoa(pessoa);
		}		
		
		if (user != null && user.checkPassword(password)) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedUser", user);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login efetuado com sucesso!", ""));
			
			return "/PesquisaPessoa.xhtml?faces-redirect=true";
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email ou senha inválidos", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "";
		}
	}
}