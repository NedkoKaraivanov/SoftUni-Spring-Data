import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Scanner;

public class RemoveTownsP13 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        String townInput = scanner.nextLine();

        Query deleteQuery = entityManager.createQuery("Delete From Town as t " +
                " Where t.name = :input");

        int count = deleteQuery.setParameter("input", townInput).executeUpdate();

        entityManager.getTransaction().commit();

        System.out.printf("%d address in %s deleted", count, townInput);

    }
}
