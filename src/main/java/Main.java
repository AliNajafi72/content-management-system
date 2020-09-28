import ir.maktabsharif.entities.Employee;
import ir.maktabsharif.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory userEntityManagerFactory =
                Persistence.createEntityManagerFactory("ContentManagementSystem");
        EntityManager userEntityManager = userEntityManagerFactory.createEntityManager();
        Employee employee = new Employee();
        employee.setName("Ali Najafi");
        employee.setPhoneNumber("09145813194");
        userEntityManager.persist(employee);
        userEntityManagerFactory.close();
    }
}
