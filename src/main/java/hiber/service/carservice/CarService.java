package hiber.service.carservice;

import hiber.model.Car;

public interface CarService {
    void updateCar(long id, Car car);

    void deleteCarById(long id);

    void cleanCarTable();

    void deleteCarTable();
}
