import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeesMaximumSalariesP12 {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = factory.createEntityManager();

        TypedQuery<Employee> query = entityManager.createQuery("Select e From Employee AS e" +
                " Where (Select max(em.salary) as max From Employee AS em Where e.department.name = em.department.name)" +
                " Not Between 30000 and 70000" +
                " Group by e.department.name " +
                " Order by e.salary", Employee.class);

        List<Employee> resultList = query.getResultList();

        resultList.forEach(e -> System.out.printf("%s %.2f%n", e.getDepartment().getName(), e.getSalary()));
    }
}
