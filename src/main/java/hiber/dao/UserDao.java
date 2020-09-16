package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    List<User> listUsers();

    User getUserByCar(int carId, int carSeries);

    User getUserByUserId(int id);

    void updateUser(int id);

    void updateCar(int id);

    void deleteUserById(int id);

    void deleteCarById(int id);

    void cleanTables();
}
