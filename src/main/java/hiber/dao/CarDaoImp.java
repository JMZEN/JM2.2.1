package hiber.dao;

import hiber.model.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CarDaoImp implements CarDao {
    Car emptyCar = new Car();

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void updateCar(long id, Car car) {
        Session session = sessionFactory.getCurrentSession();

        Optional<Car> optionalCar =
                Optional.ofNullable(session.get(Car.class, id));
        Car foundForUpdateCar = optionalCar.orElse(emptyCar);
        foundForUpdateCar.setName(car.getName());
        foundForUpdateCar.setSeries(car.getSeries());

        session.update(foundForUpdateCar);
    }

    @Override
    public void deleteCarById(long id) {
        Session session = sessionFactory.getCurrentSession();

        Optional<Car> optionalCar =
                Optional.ofNullable(session.get(Car.class, id));
        Car foundForDeleteCar = optionalCar.orElse(emptyCar);
        foundForDeleteCar.getUser().setCar(emptyCar);

        session.delete(foundForDeleteCar);
    }
}
