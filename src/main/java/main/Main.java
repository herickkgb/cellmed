package main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Dono;
import model.Pessoa;
import model.Smartphone;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Substitua "MeuPU" pelo nome da sua unidade de persistência configurada no persistence.xml
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("cellmed");

        EntityManager em = emf.createEntityManager();
        
        try {
            // Consultando todas as pessoas
            List<Pessoa> pessoas = em.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
            System.out.println("=== Pessoas ===");
            for (Pessoa p : pessoas) {
                System.out.println("ID: " + p.getId() + " - Nome: " + p.getNome() + " - Email: " + p.getEmail());
            }
            
            // Consultando todos os donos e seus smartphones
            List<Dono> donos = em.createQuery("SELECT d FROM Dono d", Dono.class).getResultList();
            System.out.println("\n=== Donos e seus Smartphones ===");
            for (Dono d : donos) {
                System.out.println("Dono: " + d.getNome());
                if (d.getSmartphones() != null) {
                    d.getSmartphones().forEach(s -> {
                        System.out.println("\tSmartphone: " + s.getMarca() + " " + s.getModelo());
                    });
                } else {
                    System.out.println("\tNenhum smartphone cadastrado.");
                }
            }
            
            // Consultando todos os smartphones diretamente
            List<Smartphone> smartphones = em.createQuery("SELECT s FROM Smartphone s", Smartphone.class).getResultList();
            System.out.println("\n=== Smartphones ===");
            for (Smartphone s : smartphones) {
                System.out.println("ID: " + s.getId() + " - " + s.getMarca() + " " + s.getModelo() +
                        " - Dono: " + (s.getDono() != null ? s.getDono().getNome() : "Sem dono"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
