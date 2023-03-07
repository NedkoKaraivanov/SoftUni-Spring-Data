import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class FindLatest10ProjectsP09 {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = factory.createEntityManager();

        TypedQuery<Project> projectsQuery = entityManager.createQuery("Select p from Project as p Order by p.startDate", Project.class);

        projectsQuery.setMaxResults(10);

        List<Project> resultList = projectsQuery.getResultList();

        resultList.stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> System.out.printf("Project name: %s%n" +
                        "    Project Description: %s%n" +
                        "    Project start date: %s%n" +
                        "    Project End Date: %s%n"
                        , p.getName(), p.getDescription(), p.getStartDate().toString(), p.getEndDate().toString()));


    }
}
