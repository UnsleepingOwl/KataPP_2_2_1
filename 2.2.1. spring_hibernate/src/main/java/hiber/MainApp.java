package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car(Math.abs((int) (System.nanoTime())), "Machine infernale"),
                car2 = new Car(Math.abs((int) (System.nanoTime())), "Machine (non) infernale");

        // Перегруженный конструктор
//        User user1 = new User("Пафнутий", "Ложечкин", "palozh@bk.ru", car1),
//                user2 = new User("Даздраперма", "Кукушкина", "dazdraku@mail.ru", car2);


        // Обычный конструктор, добавление машин через сеттеры
        User user1 = new User("Пафнутий", "Ложечкин", "palozh@bk.ru"),
                user2 = new User("Даздраперма", "Кукушкина", "dazdraku@mail.ru");

        user1.setCar(car1);
        user2.setCar(car2);


        // Скармливание связки User/Car базе данных
        userService.add(user1);
        userService.add(user2);

        // Вывод списка User из базы данных
        System.out.println("------------------------------------------------------------");
        System.out.println("Список пользователей:");
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("------------------------------------------------------------");

        // Получение User из базы данных по связке model/car_series
        System.out.println("------------------------------------------------------------");
        System.out.println("Нахождение пользователя по связке модель/серийный номер:");
        System.out.println(userService.getUserByCarModelAndSeries(car1.getModel(), car1.getSeries()));
        System.out.println(userService.getUserByCarModelAndSeries(car2.getModel(), car2.getSeries()));
        System.out.println("------------------------------------------------------------");

        context.close();
    }
}
