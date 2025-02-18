package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import enums.SumarioMensagem;

@FacesValidator("StringValidator")
public class StringValidator implements Validator {
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {		

		if( checarTipoPermitido((String) value) == false ) {			
			FacesMessage mensagem = new FacesMessage(SumarioMensagem.ERRO + "Nome n�o pode conter n�meros.");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(mensagem);						
		}
		
	}
	
	public boolean checarTipoPermitido(final CharSequence cs) {
		
		if (cs == null) {
		 return false;
		}
		
		final int sz = cs.length();
		
		for (int i = 0; i < sz; i++) {
			if (Character.isLetter(cs.charAt(i)) == false && cs.charAt(i) != ' ' && cs.charAt(i) != '.') {
				return false;
			}
		}		
		return true;				
	}

	
}
