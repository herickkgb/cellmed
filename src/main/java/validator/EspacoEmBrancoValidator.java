package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

@FacesValidator("EspacoEmBrancoValidator")
public class EspacoEmBrancoValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		if ( (value.toString().trim().equals("")) || (StringUtils.isEmpty((String) value)) ){
			FacesMessage mensagem = new FacesMessage(enums.SumarioMensagem.ERRO + "" + component.getAttributes().get("label") + ": Campo obrigat�rio n�o foi informado.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(mensagem);
		}
	}

}
