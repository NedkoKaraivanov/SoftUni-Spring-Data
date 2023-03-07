import entities.Address;
import entities.Employee;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Scanner;

public class AddressesWithEmployeeCountP07 {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = factory.createEntityManager();

        TypedQuery<Address> query = entityManager.createQuery("Select a From Address AS a Order by a.employees.size desc", Address.class);

        query.setMaxResults(10);

        List<Address> resultList = query.getResultList();

        for (Address address : resultList) {
            String text = address.getText();
            String townName;
            try {
                townName = address.getTown().getName();
            } catch (NullPointerException e) {
                townName = "";
            }

            int countEmployees = address.getEmployees().size();
            System.out.printf("%s, %s - %d employees%n", text, townName, countEmployees);
        }

    }
}
