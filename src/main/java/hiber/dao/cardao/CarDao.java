package hiber.dao.cardao;

import hiber.model.Car;
import hiber.model.User;

public interface CarDao {
    Car getCarId(long id);

    void updateCar(long id, Car car);

    void deleteCarById(long id);

    void cleanCarTable();

    void deleteCarTable();
}