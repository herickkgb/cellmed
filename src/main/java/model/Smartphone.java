package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import enums.SistemaOperacional;

@Entity
@Table(name = "smartphone")
public class Smartphone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String marca;

	@Column(nullable = false)
	private String modelo;
	private SistemaOperacional sistemaOperacional;
	private String processador;
	private int memoriaRam;
	private int armazenamento;
	private double tamanhoTela;
	private String camera;
	private int capacidadeBateria;
	private int anoLancamento;

	@ManyToOne
	@JoinColumn(name = "dono_id")
	private Dono dono;

	public Smartphone() {
	}

	public Smartphone(String marca, String modelo, SistemaOperacional sistemaOperacional, String processador,
			int memoriaRam, int armazenamento, double tamanhoTela, String camera, int capacidadeBateria,
			int anoLancamento, Dono dono) {
		this.marca = marca;
		this.modelo = modelo;
		this.sistemaOperacional = sistemaOperacional;
		this.processador = processador;
		this.memoriaRam = memoriaRam;
		this.armazenamento = armazenamento;
		this.tamanhoTela = tamanhoTela;
		this.camera = camera;
		this.capacidadeBateria = capacidadeBateria;
		this.anoLancamento = anoLancamento;
		this.dono = dono;
	}

	// Getters e Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public SistemaOperacional getSistemaOperacional() {
		return sistemaOperacional;
	}

	public void setSistemaOperacional(SistemaOperacional sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}

	public String getProcessador() {
		return processador;
	}

	public void setProcessador(String processador) {
		this.processador = processador;
	}

	public int getMemoriaRam() {
		return memoriaRam;
	}

	public void setMemoriaRam(int memoriaRam) {
		this.memoriaRam = memoriaRam;
	}

	public int getArmazenamento() {
		return armazenamento;
	}

	public void setArmazenamento(int armazenamento) {
		this.armazenamento = armazenamento;
	}

	public double getTamanhoTela() {
		return tamanhoTela;
	}

	public void setTamanhoTela(double tamanhoTela) {
		this.tamanhoTela = tamanhoTela;
	}

	public String getCamera() {
		return camera;
	}

	public void setCamera(String camera) {
		this.camera = camera;
	}

	public int getCapacidadeBateria() {
		return capacidadeBateria;
	}

	public void setCapacidadeBateria(int capacidadeBateria) {
		this.capacidadeBateria = capacidadeBateria;
	}

	public int getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(int anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public Dono getDono() {
		return dono;
	}

	public void setDono(Dono dono) {
		this.dono = dono;
	}

	public void exibirDetalhes() {
		System.out.println("Marca: " + marca);
		System.out.println("Modelo: " + modelo);
		System.out.println("Sistema Operacional: " + sistemaOperacional.getNome());
		System.out.println("Descrição: " + sistemaOperacional.getDescricao());
		System.out.println("Última versão: " + sistemaOperacional.getUltimaVersao());
		System.out.println("Processador: " + processador);
		System.out.println("Memória RAM: " + memoriaRam + "GB");
		System.out.println("Armazenamento: " + armazenamento + "GB");
		System.out.println("Tamanho da Tela: " + tamanhoTela + " polegadas");
		System.out.println("Câmera: " + camera);
		System.out.println("Bateria: " + capacidadeBateria + "mAh");
		System.out.println("Ano de Lançamento: " + anoLancamento);
		System.out.println("Dono: " + dono); // Exibindo o dono do smartphone
	}
}
