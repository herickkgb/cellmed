package enums;

public enum SistemaOperacional {
	ANDROID("Android", 1, "Sistema operacional open-source baseado em Linux, utilizado em dispositivos móveis.", "11"),
	IOS("iOS", 2, "Sistema operacional exclusivo da Apple, utilizado em dispositivos como iPhones, iPads e iPods.", "15"),
	WINDOWS_PHONE("Windows Phone", 3, "Sistema operacional para smartphones desenvolvido pela Microsoft, descontinuado.", "8.1"),
	HARMONY_OS("HarmonyOS", 4, "Sistema operacional desenvolvido pela Huawei, projetado para IoT e dispositivos móveis.", "2.0");

	private String nome;
	private int id;
	private String descricao;
	private String ultimaVersao;

	SistemaOperacional(String nome, int id, String descricao, String ultimaVersao) {
		this.nome = nome;
		this.id = id;
		this.descricao = descricao;
		this.ultimaVersao = ultimaVersao;
	}

	public String getNome() {
		return nome;
	}

	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getUltimaVersao() {
		return ultimaVersao;
	}

	public boolean isIgual(SistemaOperacional outro) {
		return this.id == outro.getId();
	}

	public void showDetails() {
		System.out.println("Sistema Operacional: " + nome);
		System.out.println("ID: " + id);
		System.out.println("Descrição: " + descricao);
		System.out.println("Última versão: " + ultimaVersao);
	}

	public static SistemaOperacional getById(int id) {
		for (SistemaOperacional so : SistemaOperacional.values()) {
			if (so.getId() == id) {
				return so;
			}
		}
		return null;
	}

	public static void listarSistemasOperacionais() {
		for (SistemaOperacional so : SistemaOperacional.values()) {
			System.out.println(so.getNome());
		}
	}

	@Override
	public String toString() {
		return nome;
	}
}
