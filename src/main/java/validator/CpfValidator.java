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
import java.util.Arrays;
import java.util.List;

/**
 * Validador de CPF, garantindo que seja válido e formatado corretamente.
 */
@FacesValidator("CpfValidator")
public class CpfValidator implements Validator {

    private static final List<String> CPF_INVALIDOS_CONHECIDOS = Arrays.asList(
        "00000000000", "11111111111", "22222222222", "33333333333", "44444444444", 
        "55555555555", "66666666666", "77777777777", "88888888888", "99999999999"
    );

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null || StringUtils.isBlank(value.toString())) {
            return; // CPF vazio é considerado válido (assumimos que não é obrigatório)
        }

        String cpf = removerFormatacao(value.toString());

        if (!StringUtils.isNumeric(cpf)) {
            throw criarExcecao("O CPF deve conter apenas números.");
        }

        if (cpf.length() != 11) {
            throw criarExcecao("O CPF deve conter exatamente 11 dígitos.");
        }

        if (CPF_INVALIDOS_CONHECIDOS.contains(cpf)) {
            throw criarExcecao("CPF inválido.");
        }

        validarCPF(cpf);
    }

    private void validarCPF(String cpf) {
        CPFValidator cpfValidator = new CPFValidator(false);
        try {
            cpfValidator.assertValid(cpf);
        } catch (InvalidStateException e) {
            throw criarExcecao("CPF inválido.");
        }
    }

    private String removerFormatacao(String cpf) {
        return cpf.replaceAll("[^0-9]", "");
    }

    private ValidatorException criarExcecao(String mensagem) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, enums.SumarioMensagem.ERRO + mensagem, null);
        return new ValidatorException(msg);
    }
}
