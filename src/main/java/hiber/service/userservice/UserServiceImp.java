package hiber.service.userservice;

import hiber.dao.userdao.UserDao;
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
    public User getUserByCar(long carId, int carSeries) {
        return userDao.getUserByCar(carId, carSeries);
    }

    @Override
    public User getUserByUserId(long id) {
        return userDao.getUserByUserId(id);
    }

    @Override
    public void updateUser(long id, User user) {
        userDao.updateUser(id, user);
    }

    @Override
    public void deleteUserById(long id) {
        userDao.deleteUserById(id);
    }


    @Override
    public void cleanUserTable() {
        userDao.cleanUserTable();
    }

    @Override
    public void deleteUserTable() {
        userDao.deleteUserTable();
    }

}
