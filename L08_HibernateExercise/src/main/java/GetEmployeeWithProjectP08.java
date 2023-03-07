import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;
import java.util.Set;

public class GetEmployeeWithProjectP08 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = factory.createEntityManager();

        int employeeId = Integer.parseInt(scanner.nextLine());

        Employee singleResult = entityManager.createQuery("Select e From Employee AS e Where e.id = :id", Employee.class)
                .setParameter("id", employeeId)
                .getSingleResult();

        System.out.printf("%s %s - %s%n", singleResult.getFirstName(), singleResult.getLastName(), singleResult.getJobTitle());
        Set<Project> projects = singleResult.getProjects();
        projects.stream()
                .sorted((f,s) -> f.getName().compareTo(s.getName()))
                .forEach(p -> System.out.printf("    %s%n", p.getName()));
    }
}
