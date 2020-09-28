package hiber.dao.cardao;

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
        if (optionalCar.isPresent()) {
            Car foundForUpdateCar = optionalCar.orElse(emptyCar);
            foundForUpdateCar.setName(car.getName());
            foundForUpdateCar.setSeries(car.getSeries());
            session.update(foundForUpdateCar);
        }
        System.out.println("Машина для обновления не найдена");
    }

    @Override
    public void deleteCarById(long id) {
        Session session = sessionFactory.getCurrentSession();

        Optional<Car> optionalCar =
                Optional.ofNullable(session.get(Car.class, id));
        if (optionalCar.isPresent()) {
            Car foundForDeleteCar = optionalCar.orElse(emptyCar);
            foundForDeleteCar.getUser().setCar(null);
            session.delete(foundForDeleteCar);
        }
        System.out.println("Машина для удаления не найдена");
    }

    @Override
    public void cleanCarTable() {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("DELETE FROM Car").executeUpdate();
    }

    @Override
    public void deleteCarTable() {
        Session session = sessionFactory.getCurrentSession();
        session.createSQLQuery("DROP TABLE IF EXISTS car").executeUpdate();
    }
}
