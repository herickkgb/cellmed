package cellmed;

import java.util.ArrayList;
import java.util.List;

import cellmed.model.Aparelho;
import cellmed.model.Endereco;
import cellmed.model.MarcaCelular;
import cellmed.model.Pessoa;

public class Main {
 public static void main(String[] args) {
     // Criando um endereço
     Endereco endereco = new Endereco(1, "Rua ABC", "123", "Centro", "São Paulo", "SP", "Brasil");
     Aparelho aparelho = new Aparelho("S20 ULTRA",MarcaCelular.APPLE , "125646", "novo", null);
     List<Aparelho> listAp = new ArrayList<>();
     listAp.add(aparelho);

     // Criando pessoas com diferentes tipos de documento
     Pessoa pessoa1 = new Pessoa(1, "Carlos Silva", "13382181750", endereco, listAp); // CPF inválido (para teste)
     Pessoa pessoa2 = new Pessoa(2, "Maria Oliveira", "17217988900104", endereco, listAp); // CNPJ válido (para teste)
     Pessoa pessoa3 = new Pessoa(3, "Juan Pérez", "P123456789", endereco,listAp); // Estrangeiro

     // Verificando o tipo de documento e validando
     System.out.println(pessoa1.getNome() + " - Tipo Documento: " + pessoa1.tipoDocumento() + " - " + pessoa1.validarCpfCnpj());
     System.out.println(pessoa2.getNome() + " - Tipo Documento: " + pessoa2.tipoDocumento() + " - " + pessoa2.validarCpfCnpj());
     System.out.println(pessoa3.getNome() + " - Tipo Documento: " + pessoa3.tipoDocumento() + " - " + pessoa3.validarCpfCnpj());
 }
}
