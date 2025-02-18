package validator;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import enums.SumarioMensagem;

@FacesValidator("AnoValidator")
public class AnoValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		if(value == null || value != null && value.equals("")) 
			return;
		
		Date dataAtual = new Date();
		Integer anoAtual = new Integer(new SimpleDateFormat("yyyy").format(dataAtual));
		Integer ano = new Integer(value.toString());
		
		if (ano.compareTo(anoAtual) > 0) {
			FacesMessage mensagem = new FacesMessage(SumarioMensagem.ERRO + "Ano inv�lido!");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(mensagem);
		}
	}

}
