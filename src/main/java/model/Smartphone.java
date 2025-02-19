package model;

import enums.SistemaOperacional;

public class Smartphone {
	private Long id;
	private String brand;
	private String model;
	private SistemaOperacional operatingSystem;
	private String processor;
	private int ram;
	private int storage;
	private double screenSize;
	private String camera;
	private int batteryCapacity;
	private int releaseYear;
	private Dono dono;

	public Smartphone() {
		super();
	}

	public Smartphone(Long id, String brand, String model, SistemaOperacional operatingSystem, String processor,
			int ram, int storage, double screenSize, String camera, int batteryCapacity, int releaseYear, Dono dono) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.operatingSystem = operatingSystem;
		this.processor = processor;
		this.ram = ram;
		this.storage = storage;
		this.screenSize = screenSize;
		this.camera = camera;
		this.batteryCapacity = batteryCapacity;
		this.releaseYear = releaseYear;
		this.dono = dono;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public SistemaOperacional getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(SistemaOperacional operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public int getStorage() {
		return storage;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}

	public double getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(double screenSize) {
		this.screenSize = screenSize;
	}

	public String getCamera() {
		return camera;
	}

	public void setCamera(String camera) {
		this.camera = camera;
	}

	public int getBatteryCapacity() {
		return batteryCapacity;
	}

	public void setBatteryCapacity(int batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Dono getDono() {
		return dono;
	}

	public void setDono(Dono dono) {
		this.dono = dono;
	}

	public void showDetails() {
		System.out.println("Marca: " + brand);
		System.out.println("Modelo: " + model);
		System.out.println("Sistema Operacional: " + operatingSystem.getNome());
		System.out.println("Descrição: " + operatingSystem.getDescricao());
		System.out.println("Última versão: " + operatingSystem.getUltimaVersao());
		System.out.println("Processador: " + processor);
		System.out.println("Memória RAM: " + ram + "GB");
		System.out.println("Armazenamento: " + storage + "GB");
		System.out.println("Tamanho da Tela: " + screenSize + " polegadas");
		System.out.println("Câmera: " + camera);
		System.out.println("Bateria: " + batteryCapacity + "mAh");
		System.out.println("Ano de Lançamento: " + releaseYear);
		System.out.println("Dono: " + dono); // Exibindo o dono do smartphone
	}
}
