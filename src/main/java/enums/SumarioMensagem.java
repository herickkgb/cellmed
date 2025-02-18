package enums;

public enum SumarioMensagem {
	
    ERRO("Erro: "),
    SUCESSO("Sucesso: "),
    EXCECAO("Exceção: "),
    ATENCAO("Atenção: "),
    CAMPO_OBRIGATORIO_NAO_PREENCHIDO("Campo obrigatório não informado. "),
    SEM_MENSAGEM("");

    private SumarioMensagem(final String text) {
        this.text = text;
    }

    private final String text;

    @Override
    public String toString() {
        return text;
    }
}