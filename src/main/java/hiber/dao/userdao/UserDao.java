package hiber.dao.userdao;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    List<User> listUsers();

    User getUserByCar(long carId, int carSeries);

    User getUserByUserId(long id);

    void updateUser(long id, User user);

    void deleteUserById(long id);

    void cleanUserTable();

    void deleteUserTable();
}