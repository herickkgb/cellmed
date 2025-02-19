package main;

import java.util.ArrayList;
import java.util.List;

import enums.SistemaOperacional;
import model.Dono;
import model.Smartphone;

public class Main {
	public static void main(String[] args) {
		List<Smartphone> smartphones = new ArrayList<>();

		Dono dono = new Dono(1L, smartphones);

		Smartphone smartphone1 = new Smartphone(1L, "Apple", "iPhone 13", SistemaOperacional.IOS, "A15 Bionic", 6, 128,
				6.1, "12 MP", 3240, 2021, dono);
		Smartphone smartphone2 = new Smartphone(2L, "Samsung", "Galaxy S21", SistemaOperacional.ANDROID, "Exynos 2100",
				8, 128, 6.2, "64 MP", 4000, 2021, dono);

		dono.addSmartphone(smartphone1);
		dono.addSmartphone(smartphone2);

		for (Smartphone smartphone : dono.getSmartphones()) {
			smartphone.showDetails();
			System.out.println("----------------------------");
		}
	}
}
