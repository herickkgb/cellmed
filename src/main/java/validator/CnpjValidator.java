package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.InvalidStateException;

import enums.SumarioMensagem;


/**
 * Validator para verificar CNPJ com formata��o. 
 * 
 * @author Rodrigo Oliveira Tavares
 *
 */
@FacesValidator("CnpjValidator")
public class CnpjValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		if (StringUtils.isNotEmpty((String) value)) {

			// O VALIDATOR N�O TRATA ESSE CASO
			if (value.toString().equals("00000000000000")) {
				FacesMessage mensagem = new FacesMessage(SumarioMensagem.ERRO + "CNPJ inv�lido");
				mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(mensagem);
			} else {
				CNPJValidator cnpjValidator = new CNPJValidator(false);
				
				try {
					cnpjValidator.assertValid(value.toString());
				} catch (InvalidStateException e) {
					FacesMessage mensagem = new FacesMessage(SumarioMensagem.ERRO + "CNPJ inv�lido");
					mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(mensagem);
				}
			}
		}
	}

}
