package hiber.dao.cardao;

import hiber.model.Car;

public interface CarDao {
    void updateCar(long id, Car car);

    void deleteCarById(long id);

    void cleanCarTable();

    void deleteCarTable();
}