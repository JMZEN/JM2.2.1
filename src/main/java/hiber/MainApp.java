package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MainApp {
    static final AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

    static UserService userService = context.getBean(UserService.class);

    public static void main(String[] args) {
        Car car1 = new Car("BMW", 111);
        Car car2 = new Car("Lada", 222);
        Car car3 = new Car("XXX", 333);
        Car car4 = new Car("YYY", 444);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        user1.setCar(car1);
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        user2.setCar(car2);
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        user3.setCar(car3);
        User user4 = new User("User4", "Lastname1", "user1@mail.ru");
        user4.setCar(car4);

        // (Create) Добавление 4 User + Car
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        // (Read) Вывод Users
        printUsersList();

        // (Read) Поиск по Car
        System.out.println(userService.getUserByCar(1, 111));

        // (Update) Обновление User / Car
        userService.updateUser(1, new User("Max", "Maximov", "max@ya.ru"));
        userService.updateCar(4, new Car("Audi", 777));

        // (Delete) Удаление пользователя по Id(удаляется также машина)
        // (Delete) Удаление машины(пользоваель не удаляется)
        userService.deleteUserById(2);
        userService.deleteCarById(3);

//        userService.cleanTables();
        context.close();
    }

    private static void printUsersList() {
        Car emptyCar = new Car();
        List<User> users = userService.listUsers();
        for (User user : users) {
            Optional<Car> carOptional = Optional.ofNullable(user.getCar());
            Car car = carOptional.orElse(emptyCar);
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + car.toString());
            System.out.println();
        }
    }
}
