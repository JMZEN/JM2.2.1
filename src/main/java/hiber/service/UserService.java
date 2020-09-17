package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();

    User getUserByCar(long carId, int carSeries);

    User getUserByUserId(long id);

    void updateUser(long id);

    void updateCar(long id);

    void deleteUserById(long id);

    void deleteCarById(long id);

    void cleanTables();
}
