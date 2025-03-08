package generics;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenericBO implements Serializable {

    private static final long serialVersionUID = 1L;

    // Método para verificar se uma string é nula ou vazia
    public boolean estaNuloOuVazio(String str) {
        return str == null || str.trim().isEmpty();
    }

    // Método para validar um e-mail usando expressão regular
    public boolean emailValido(String email) {
        if (estaNuloOuVazio(email)) {
            return false;
        }
        String regexEmail = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern padrao = Pattern.compile(regexEmail);
        Matcher matcher = padrao.matcher(email);
        return matcher.matches();
    }

    // Método para verificar se um número é positivo
    public boolean numeroPositivo(int numero) {
        return numero > 0;
    }

    // Método para verificar se um objeto é nulo
    public boolean objetoNulo(Object obj) {
        return obj == null;
    }

    // Método para garantir que uma lista não seja nula ou vazia
    public boolean listaNulaOuVazia(List<Object> lista) {
        return lista == null || lista.isEmpty();
    }

    // Método para formatar uma string para maiúsculas
    public String formatarParaMaiusculas(String entrada) {
        if (estaNuloOuVazio(entrada)) {
            return "";
        }
        return entrada.trim().toUpperCase();
    }

    // Método para formatar uma string para minúsculas
    public String formatarParaMinusculas(String entrada) {
        if (estaNuloOuVazio(entrada)) {
            return "";
        }
        return entrada.trim().toLowerCase();
    }

    // Método para remover espaços extras em uma string
    public String removerEspacosExtras(String entrada) {
        if (estaNuloOuVazio(entrada)) {
            return "";
        }
        return entrada.trim().replaceAll("\\s+", " ");
    }

    // Método para gerar um hash simples de uma string
    public int gerarHashString(String entrada) {
        if (estaNuloOuVazio(entrada)) {
            return 0;
        }
        return entrada.hashCode();
    }

    // Método para comparar se duas listas são iguais
    public boolean listasIguais(List<Object> lista1, List<Object> lista2) {
        if (lista1 == lista2) return true;
        if (lista1 == null || lista2 == null) return false;
        return lista1.size() == lista2.size() && lista1.containsAll(lista2);
    }

    // Método para garantir que uma lista não seja nula ou vazia
    public void validarListaNaoVazia(List<Object> lista, String mensagemErro) {
        if (listaNulaOuVazia(lista)) {
            throw new IllegalArgumentException(mensagemErro);
        }
    }

    // Método para garantir que um número esteja dentro de um intervalo
    public void validarNumeroNoIntervalo(int numero, int minimo, int maximo, String mensagemErro) {
        if (numero < minimo || numero > maximo) {
            throw new IllegalArgumentException(mensagemErro);
        }
    }

    // Método para garantir que uma string tenha um comprimento mínimo
    public void validarComprimentoMinimo(String str, int comprimentoMinimo, String mensagemErro) {
        if (str == null || str.length() < comprimentoMinimo) {
            throw new IllegalArgumentException(mensagemErro);
        }
    }
}
