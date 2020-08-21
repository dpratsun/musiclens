import com.musiclens.model.Role;
import com.musiclens.model.User;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MusicLens {

    private static final EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("musiclens");


    public static void main(String[] args) {
        var entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            var query = entityManager.createQuery("DELETE FROM Role ");
            query.executeUpdate();
            var role = new Role(0, "Admin");
            var user = new User("Dmitry", "Pratsun", "pratsundv@gmail.com");
            user.setRole(role);
            user.setId(0);
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
