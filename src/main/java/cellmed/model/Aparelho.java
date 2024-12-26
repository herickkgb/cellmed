package cellmed.model;

import javax.validation.constraints.NotNull;

import jakarta.persistence.*;

@Entity
@Table(name = "aparelho")
public class Aparelho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "marca", nullable = false)
    private MarcaCelular marca;

    @NotNull
    @Column(name = "numeroSerie", nullable = false)
    private String numeroSerie;

    @NotNull
    @Column(name = "status", nullable = false)
    private String status;

 
    @ManyToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = true)
    private Pessoa pessoa;

    public Aparelho() {}

    public Aparelho(String modelo, MarcaCelular marca, String numeroSerie, String status, Pessoa pessoa) {
        this.modelo = modelo;
        this.marca = marca;
        this.numeroSerie = numeroSerie;
        this.status = status;
        this.pessoa = pessoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public MarcaCelular getMarca() {
        return marca;
    }

    public void setMarca(MarcaCelular marca) {
        this.marca = marca;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
