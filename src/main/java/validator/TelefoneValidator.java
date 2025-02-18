package validator;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator ("TelefoneValidator")
public class TelefoneValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		
		// TODO: Falta adaptar para o nono d�gito
		if (!Pattern.matches("[2-9][0-9]{7}|[2-9][0-9]{8}", (CharSequence) value)) {
			FacesMessage mensagem = new FacesMessage(enums.SumarioMensagem.ERRO + "N�mero de telefone inv�lido ");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(mensagem);
		}
	}
}
