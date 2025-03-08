package generics;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class GenericBean implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Adiciona uma mensagem ao contexto do JSF.
     *
     * @param severidade Tipo de severidade da mensagem (INFO, WARN, ERROR)
     * @param mensagem   Texto da mensagem a ser exibida
     */
    protected void adicionarMensagem(FacesMessage.Severity severidade, String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severidade, mensagem, null));
    }

    /**
     * Método genérico para lidar com exceções e adicionar mensagens de erro ao FacesContext.
     *
     * @param mensagemErro Mensagem personalizada de erro
     * @param e            Exceção capturada
     */
    protected void tratarExcecao(String mensagemErro, Exception e) {
        adicionarMensagem(FacesMessage.SEVERITY_ERROR, mensagemErro + ": " + e.getMessage());
    }
}
