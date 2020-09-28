import ir.maktabsharif.entities.cms.User;
import ir.maktabsharif.entities.employee.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory userEntityManagerFactory =
                Persistence.createEntityManagerFactory("Employee");
        EntityManager userEntityManager = userEntityManagerFactory.createEntityManager();
        Employee employee = new Employee();
        employee.setName("Ali Najafi");
        employee.setPhoneNumber("09145813194");
        userEntityManager.getTransaction().begin();
        userEntityManager.persist(employee);
        userEntityManager.getTransaction().commit();
        userEntityManagerFactory.close();
        userEntityManagerFactory =
                Persistence.createEntityManagerFactory("ContentManagementSystem");
        userEntityManager = userEntityManagerFactory.createEntityManager();
        User user = new User();
        user.setUsername("AliNajafi");
        userEntityManager.getTransaction().begin();
        userEntityManager.persist(user);
        userEntityManager.getTransaction().commit();
        userEntityManagerFactory.close();
    }
}
