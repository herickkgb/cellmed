package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import enums.SumarioMensagem;


@FacesValidator("DddValidator")
public class DddValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		final int DDD_MIN = 11;
		final int DDD_MAX = 99;

		int ddd;

		FacesMessage mensagem = new FacesMessage(SumarioMensagem.ERRO + "N�mero de DDD inv�lido");
		mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);

		try {
			ddd = Integer.parseInt(value.toString());
		} catch (NumberFormatException e) {
			throw new ValidatorException(mensagem);
		}

		if (ddd < DDD_MIN || ddd > DDD_MAX) {
			throw new ValidatorException(mensagem);
		}
	}

}
