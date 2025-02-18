package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

@FacesValidator("CpfValidator")
public class CpfValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		if (StringUtils.isNotEmpty((String) value)) {

			CPFValidator cpfValidator = new CPFValidator(false);

			try {
				cpfValidator.assertValid(value.toString());
			} catch (InvalidStateException e) {
				FacesMessage mensagem = new FacesMessage(enums.SumarioMensagem.ERRO + "CPF inv�lido");
				mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(mensagem);
			}
		}
	}

}
