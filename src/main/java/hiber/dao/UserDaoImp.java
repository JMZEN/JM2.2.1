package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository

public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
//    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory
                .getCurrentSession()
                .createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User getUserByCar(int carId, int carSeries) {
        User emptyUser = new User();
        String hql = "SELECT DISTINCT u FROM User as u " +
                "LEFT JOIN FETCH u.car c " +
                "WHERE c.id=:id and c.series=:series ";

        Query<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
        query.setParameter("id", carId);
        query.setParameter("series", carSeries);

        Optional<User> optionalUser = Optional.ofNullable(query.uniqueResult());
        return optionalUser.orElse(emptyUser);
    }

}
