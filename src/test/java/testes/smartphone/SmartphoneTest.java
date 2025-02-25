package testes.smartphone;

import model.Smartphone;
import model.Dono;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import enums.SistemaOperacional;
import testes.BaseTest;


public class SmartphoneTest extends BaseTest {

    @Test
    void testInsercao() {
        Dono dono = new Dono();
        dono.setNome("João Silva");
        salvar(dono);

        Smartphone smartphone = new Smartphone(
            "Samsung", "Galaxy S23", SistemaOperacional.ANDROID,
            "Snapdragon 8 Gen 2", 8, 256, 6.1, "108MP", 5000, 2023, dono
        );

        salvar(smartphone);

        Smartphone encontrado = em.find(Smartphone.class, smartphone.getId());
        assertNotNull(encontrado);
        assertEquals("Samsung", encontrado.getMarca());
    }

    @Test
    void testConsulta() {
        Dono dono = new Dono();
        dono.setNome("Maria Oliveira");
        salvar(dono);

        Smartphone smartphone = new Smartphone(
            "Apple", "iPhone 14", SistemaOperacional.IOS,
            "A15 Bionic", 6, 128, 6.1, "12MP", 3500, 2022, dono
        );

        salvar(smartphone);

        Smartphone encontrado = em.createQuery(
            "SELECT s FROM Smartphone s WHERE s.modelo = :modelo", Smartphone.class)
            .setParameter("modelo", "iPhone 14")
            .getSingleResult();

        assertNotNull(encontrado);
        assertEquals("Apple", encontrado.getMarca());
    }

    @Test
    void testEdicao() {
        Dono dono = new Dono();
        dono.setNome("Carlos Souza");
        salvar(dono);

        Smartphone smartphone = new Smartphone(
            "Xiaomi", "Redmi Note 12", SistemaOperacional.ANDROID,
            "Snapdragon 695", 6, 128, 6.67, "50MP", 5000, 2023, dono
        );

        salvar(smartphone);

        Smartphone editado = em.find(Smartphone.class, smartphone.getId());
        editado.setModelo("Redmi Note 12 Pro");
        em.merge(editado);

        Smartphone atualizado = em.find(Smartphone.class, smartphone.getId());
        assertEquals("Redmi Note 12 Pro", atualizado.getModelo());
    }

    @Test
    void testDelecao() {
        Dono dono = new Dono();
        dono.setNome("Fernando Lima");
        salvar(dono);

        Smartphone smartphone = new Smartphone(
            "Motorola", "Moto G100", SistemaOperacional.ANDROID,
            "Snapdragon 870", 8, 128, 6.7, "64MP", 5000, 2021, dono
        );

        salvar(smartphone);

        Smartphone deletar = em.find(Smartphone.class, smartphone.getId());
        em.remove(deletar);
        em.flush();

        Smartphone verificado = em.find(Smartphone.class, smartphone.getId());
        assertNull(verificado);
    }
}
