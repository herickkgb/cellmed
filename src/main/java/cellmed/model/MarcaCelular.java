package cellmed.model;

public enum MarcaCelular {
    SAMSUNG(1),
    APPLE(2),
    XIAOMI(3),
    OPPO(4),
    VIVO(5),
    MOTOROLA(6),
    REALME(7),
    HONOR(8),
    HUAWEI(9),
    LENOVO(10),
    LG(11),
    ASUS(12),
    NOKIA(13),
    SONY(14),
    ZTE(15),
    TECNO(16),
    INFINIX(17),
    MEIZU(18),
    ONEPLUS(19),
    GOOGLE(20);

    private final int indice;

    MarcaCelular(int indice) {
        this.indice = indice;
    }

    public int getIndice() {
        return indice;
    }

    @Override
    public String toString() {
        return name() + " (Índice: " + indice + ")";
    }
}

