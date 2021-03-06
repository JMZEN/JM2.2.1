package hiber.service.carservice;

import hiber.dao.cardao.CarDao;
import hiber.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarServiceImp implements CarService {
    @Autowired
    private CarDao carDao;

    @Override
    public Car getCarId(long id) {
        return carDao.getCarId(id);
    }

    @Override
    public void updateCar(long id, Car car) {
        carDao.updateCar(id, car);
    }

    @Override
    public void deleteCarById(long id) {
        carDao.deleteCarById(id);
    }

    @Override
    public void cleanCarTable() {
        carDao.cleanCarTable();
    }

    @Override
    public void deleteCarTable() {
        carDao.deleteCarTable();
    }
}
