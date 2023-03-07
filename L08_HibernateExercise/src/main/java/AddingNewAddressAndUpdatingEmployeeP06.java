import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class AddingNewAddressAndUpdatingEmployeeP06 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = factory.createEntityManager();

        String lastName = scanner.nextLine();

        entityManager.getTransaction().begin();

        Address newAddress = new Address();
        newAddress.setText("Vitoshka 15");

        entityManager.persist(newAddress);

        entityManager.createQuery("Update Employee e Set e.address = :newAddress Where e.lastName = :ln")
                .setParameter("newAddress", newAddress)
                .setParameter("ln", lastName)
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();


    }
}
