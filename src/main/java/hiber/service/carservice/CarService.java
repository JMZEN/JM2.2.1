package hiber.service.carservice;

import hiber.model.Car;

public interface CarService {
    Car getCarId(long id);

    void updateCar(long id, Car car);

    void deleteCarById(long id);

    void cleanCarTable();

    void deleteCarTable();
}
