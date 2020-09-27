package hiber.dao.userdao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao {
    User emptyUser = new User();

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory
                .getCurrentSession()
                .createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User getUserByCar(long carId, int carSeries) {
        String hql = "SELECT u FROM User as u " +
                "LEFT JOIN FETCH u.car c " +
                "WHERE c.id=:id and c.series=:series ";

        Query<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
        query.setParameter("id", carId);
        query.setParameter("series", carSeries);

        Optional<User> optionalUser = Optional.ofNullable(query.uniqueResult());
        return optionalUser.orElse(emptyUser);
    }

    @Override
    public User getUserByUserId(long id) {
        Session session = sessionFactory.getCurrentSession();
        Optional<User> optionalUser = Optional.ofNullable(session.get(User.class, id));
        return optionalUser.orElse(emptyUser);
    }

    @Override
    public void updateUser(long id, User user) {
        Session session = sessionFactory.getCurrentSession();

        Optional<User> optionalUser =
                Optional.ofNullable(session.get(User.class, id));
        User foundForUpdateUser = optionalUser.orElse(emptyUser);
        foundForUpdateUser.setEmail(user.getEmail());
        foundForUpdateUser.setFirstName(user.getFirstName());
        foundForUpdateUser.setLastName(user.getLastName());

        session.update(foundForUpdateUser);
    }

    @Override
    public void deleteUserById(long id) {
        Session session = sessionFactory.getCurrentSession();

        Optional<User> optionalUser =
                Optional.ofNullable(session.get(User.class, id));
        User foundForDeleteUser = optionalUser.orElse(emptyUser);

        session.delete(foundForDeleteUser);
    }

    @Override
    public void cleanUserTable() {
//        Session session = sessionFactory.getCurrentSession();
        sessionFactory.getCurrentSession().createQuery("DELETE FROM User").executeUpdate();
    }

    public void deleteUserTable() {
//        Session session = sessionFactory.getCurrentSession();
        sessionFactory.getCurrentSession().createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
    }
}
