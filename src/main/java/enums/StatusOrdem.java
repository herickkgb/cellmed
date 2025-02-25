package enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum StatusOrdem {
    SOLICITADO(1, "Ordem de serviço registrada no sistema"),
    COLETA_AGENDADA(2, "Coleta do aparelho agendada com o cliente"),
    EM_COLETA(3, "Técnico a caminho para recolher o aparelho"),
    EM_TRANSITO_OFICINA(4, "Aparelho em trânsito para a oficina"),
    RECEBIDO_OFICINA(5, "Aparelho recebido na oficina"),
    TRIAGEM_INICIAL(6, "Avaliação inicial de danos e funcionalidades"),
    ANALISE_TECNICA(7, "Diagnóstico técnico em andamento"),
    AGUARDANDO_APROVACAO(8, "Aguardando aprovação do cliente para reparo"),
    AGUARDANDO_PECA(9, "Aguardando peça de reposição no estoque"),
    EM_REPARO(10, "Reparo físico/software em execução"),
    TESTES_POS_REPARO(11, "Testes de qualidade pós-intervenção"),
    REPARO_IMPOSSIVEL(12, "Reparo inviável - necessária substituição"),
    LIMPEZA_FINAL(13, "Higienização e preparação para entrega"),
    DEVOLUCAO_AGENDADA(14, "Retorno ao cliente agendado"),
    EM_TRANSITO_CLIENTE(15, "Aparelho em trânsito para o cliente"),
    SAIU_ENTREGA(16, "Aparelho saiu para entrega ao cliente"),
    ENTREGA_FALHOU(17, "Falha na entrega - nova tentativa agendada"),
    ENTREGUE(18, "Dispositivo devolvido com sucesso"),
    RECEBIDO_CLIENTE(19, "Cliente confirmou recebimento e funcionalidade"),
    CANCELADO(20, "Processo cancelado a pedido do cliente"),
    REABERTO(21, "Nova intervenção requerida no mesmo aparelho"),
    EM_QUARENTENA(22, "Aparelho em análise de segurança/contaminação"),
    FINALIZADO(23, "Finalizado");

    private final int id;
    private final String descricao;
    
    private static final Map<StatusOrdem, List<StatusOrdem>> TRANSICOES = Map.ofEntries(
        Map.entry(SOLICITADO, List.of(COLETA_AGENDADA, CANCELADO)),
        Map.entry(COLETA_AGENDADA, List.of(EM_COLETA, CANCELADO)),
        Map.entry(EM_COLETA, List.of(EM_TRANSITO_OFICINA)),
        Map.entry(EM_TRANSITO_OFICINA, List.of(RECEBIDO_OFICINA)),
        Map.entry(RECEBIDO_OFICINA, List.of(TRIAGEM_INICIAL)),
        Map.entry(TRIAGEM_INICIAL, List.of(ANALISE_TECNICA, CANCELADO)),
        Map.entry(ANALISE_TECNICA, List.of(AGUARDANDO_APROVACAO, REPARO_IMPOSSIVEL, EM_QUARENTENA)),
        Map.entry(AGUARDANDO_APROVACAO, List.of(AGUARDANDO_PECA, CANCELADO)),
        Map.entry(AGUARDANDO_PECA, List.of(EM_REPARO)),
        Map.entry(EM_REPARO, List.of(TESTES_POS_REPARO)),
        Map.entry(TESTES_POS_REPARO, List.of(LIMPEZA_FINAL, REABERTO)),
        Map.entry(LIMPEZA_FINAL, List.of(DEVOLUCAO_AGENDADA)),
        Map.entry(DEVOLUCAO_AGENDADA, List.of(EM_TRANSITO_CLIENTE)),
        Map.entry(EM_TRANSITO_CLIENTE, List.of(SAIU_ENTREGA)),
        Map.entry(SAIU_ENTREGA, List.of(ENTREGUE, ENTREGA_FALHOU)),
        Map.entry(ENTREGA_FALHOU, List.of(DEVOLUCAO_AGENDADA)),
        Map.entry(REABERTO, List.of(ANALISE_TECNICA)),
        Map.entry(EM_QUARENTENA, List.of(ANALISE_TECNICA, CANCELADO)),
        Map.entry(FINALIZADO, List.of(FINALIZADO, CANCELADO))
    );

    /**
     * Construtor do enum
     * @param id Identificador numérico do status
     * @param descricao Descrição detalhada do status
     */
    StatusOrdem(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    /**
     * Retorna o ID numérico do status
     * @return ID do status
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna a descrição detalhada do status
     * @return Descrição do status
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Busca um status pelo seu ID
     * @param id ID do status a ser buscado
     * @return Status correspondente ao ID
     * @throws IllegalArgumentException Se o ID não existir
     */
    public static StatusOrdem buscarPorId(int id) {
        return Arrays.stream(values())
            .filter(status -> status.id == id)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
    }

    /**
     * Retorna todos os status ordenados por ID
     * @return Lista de todos os status
     */
    public static List<StatusOrdem> listarTodos() {
        return Arrays.stream(values())
            .sorted((s1, s2) -> Integer.compare(s1.id, s2.id))
            .collect(Collectors.toList());
    }

    /**
     * Verifica se o status é final (não permite mais transições)
     * @return true se for um status final
     */
    public boolean isFinal() {
        return List.of(FINALIZADO,ENTREGUE, CANCELADO, RECEBIDO_CLIENTE, REPARO_IMPOSSIVEL).contains(this);
    }

    /**
     * Retorna os próximos status possíveis a partir do atual
     * @return Lista de status possíveis
     */
    public List<StatusOrdem> proximosStatus() {
        return TRANSICOES.getOrDefault(this, Collections.emptyList());
    }

    /**
     * Converte uma string para o enum correspondente
     * @param nome Nome do status (case insensitive)
     * @return Status correspondente
     * @throws IllegalArgumentException Se o nome não existir
     */
    public static StatusOrdem deString(String nome) {
        return Arrays.stream(values())
            .filter(status -> status.name().equalsIgnoreCase(nome))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Status inválido: " + nome));
    }

    /**
     * Retorna representação completa do status
     * @return String formatada com ID, nome e descrição
     */
    @Override
    public String toString() {
        return id + " - " + name() + ": " + descricao;
    }
}