import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.Scanner;

public class IncreaseSalaryP10 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery("Select e FROM Employee AS e" +
                " Where e.department.name IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services')", Employee.class)
                .getResultList()
                .forEach(e -> {
                    e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12)));
                    System.out.printf("%s %s ($%s)%n", e.getFirstName(), e.getLastName(), e.getSalary().toString());
                });

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
