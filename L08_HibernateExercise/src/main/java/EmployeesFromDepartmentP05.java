import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class EmployeesFromDepartmentP05 {

    private static final String PRINT_EMPLOYEE_FORMAT = "%s %s from %s - $%.2f%n";
    public static void main(String[] args) {

        final EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");

        final EntityManager entityManager = factory.createEntityManager();

        String departmentName = "Research and Development";

        Query employees = entityManager.createQuery("Select e From Employee As e " +
                "Where e.department.name = :dp " +
                "Order by e.salary asc, e.id asc", Employee.class);

        employees.setParameter("dp", departmentName);

        List<Employee> resultList = employees.getResultList();

        resultList.forEach(employee -> System.out.printf("%s %s from %s - $%.2f%n", employee.getFirstName(), employee.getLastName(),
                    employee.getDepartment().getName(), employee.getSalary()));


    }
}
