import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class ContainsEmployeeP03 {

    public static void main(String[] args) {

       final Scanner scanner = new Scanner(System.in);

       final EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");

       final EntityManager entityManager = factory.createEntityManager();

        final String[] names = scanner.nextLine().split(" ");

        final String firstName = names[0];
        final String lastName = names[1];

        final Long countMatches = entityManager.createQuery(
                "SELECT count(e) FROM Employee e WHERE e.firstName = :fn AND e.lastName = :ln", Long.class)
                .setParameter("fn", firstName).setParameter("ln", lastName)
                .getSingleResult();


        if (countMatches == 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }

    }
}
