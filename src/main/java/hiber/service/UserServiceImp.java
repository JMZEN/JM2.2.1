package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public User getUserByCar(int carId, int carSeries) {
        return userDao.getUserByCar(carId, carSeries);
    }

    @Override
    public User getUserByUserId(int id) {
        return userDao.getUserByUserId(id);
    }

    @Override
    public void updateUser(int id) {
        userDao.updateUser(id);
    }

    @Override
    public void updateCar(int id) {
        userDao.updateCar(id);
    }

    @Override
    public void deleteUserById(int id) {
        userDao.deleteUserById(id);
    }

    @Override
    public void deleteCarById(int id) {
        userDao.deleteCarById(id);
    }

    @Override
    public void cleanTables() {
        userDao.cleanTables();
    }

}
