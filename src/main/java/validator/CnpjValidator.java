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
import java.util.Arrays;
import java.util.List;

/**
 * Validador de CNPJ, garantindo que seja válido e formatado corretamente.
 */
@FacesValidator("CnpjValidator")
public class CnpjValidator implements Validator {

    private static final List<String> CNPJ_INVALIDOS_CONHECIDOS = Arrays.asList(
        "00000000000000", "11111111111111", "22222222222222", "33333333333333",
        "44444444444444", "55555555555555", "66666666666666", "77777777777777",
        "88888888888888", "99999999999999"
    );

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null || StringUtils.isBlank(value.toString())) {
            return; // CNPJ vazio é considerado válido (assumimos que não é obrigatório)
        }

        String cnpj = removerFormatacao(value.toString());

        if (!StringUtils.isNumeric(cnpj)) {
            throw criarExcecao("O CNPJ deve conter apenas números.");
        }

        if (cnpj.length() != 14) {
            throw criarExcecao("O CNPJ deve conter exatamente 14 dígitos.");
        }

        if (CNPJ_INVALIDOS_CONHECIDOS.contains(cnpj)) {
            throw criarExcecao("CNPJ inválido.");
        }

        validarCNPJ(cnpj);
    }

    private void validarCNPJ(String cnpj) {
        CNPJValidator cnpjValidator = new CNPJValidator(false);
        try {
            cnpjValidator.assertValid(cnpj);
        } catch (InvalidStateException e) {
            throw criarExcecao("CNPJ inválido.");
        }
    }

    private String removerFormatacao(String cnpj) {
        return cnpj.replaceAll("[^0-9]", "");
    }

    private ValidatorException criarExcecao(String mensagem) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, enums.SumarioMensagem.ERRO + mensagem, null);
        return new ValidatorException(msg);
    }
}
