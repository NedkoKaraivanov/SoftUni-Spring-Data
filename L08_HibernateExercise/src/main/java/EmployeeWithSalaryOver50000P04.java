import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeWithSalaryOver50000P04 {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        TypedQuery<String> query = entityManager.createQuery("SELECT e.firstName FROM Employee AS e WHERE e.salary > 50000", String.class);

        List<String> resultList = query.getResultList();

        for (String name : resultList) {
            System.out.println(name);
        }

        entityManager.getTransaction().commit();

        entityManager.close();

    }
}
