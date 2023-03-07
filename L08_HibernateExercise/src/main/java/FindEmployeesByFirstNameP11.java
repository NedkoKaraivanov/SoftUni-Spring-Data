import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class FindEmployeesByFirstNameP11 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = factory.createEntityManager();

        String pattern = scanner.nextLine();

        TypedQuery<Employee> query = entityManager.createQuery("Select e From Employee AS e", Employee.class);

        List<Employee> resultListAll = query.getResultList();
        for (Employee employee : resultListAll) {
            if ((employee.getFirstName()).substring(0, 2).toLowerCase().equals(pattern.toLowerCase())) {
                System.out.printf("%s %s - %s - ($%s)%n", employee.getFirstName(),
                employee.getLastName(),
                employee.getJobTitle(),
                employee.getSalary().toString());
            }
        }

    }
}
