package validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import enums.SumarioMensagem;


@FacesValidator("DataIgualOuAnteriorValidator")
public class DataIgualOuAnteriorValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		if(value == null || value != null && value.equals("")) 
			return;
		
		Date dataAtual = new Date();
		Date dataPreenchida = (Date) value;
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			dataAtual = formatter.parse(formatter.format(dataAtual));
			dataPreenchida = formatter.parse(formatter.format(dataPreenchida));
			if (util.DateUtil.comparaDatas(dataPreenchida, dataAtual) == 1) {
				FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, enums.SumarioMensagem.ERRO.toString(),"A data informada deve ser menor ou igual a data de hoje!");
				throw new ValidatorException(mensagem);
			}
		} catch (ParseException e) {
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, SumarioMensagem.ERRO.toString(),"O formato da data � inv�lido!");
			throw new ValidatorException(mensagem);
		}
	}

}
