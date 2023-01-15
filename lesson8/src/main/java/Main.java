import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import dao.DAOException;
import domain.Magazine;
import domain.Subscribe;
import domain.User;
import service.MagazineService;
import service.SubscribeService;
import service.UserService;
import service.impl.MagazineServiceImpl;
import service.impl.SubscribeServiceImpl;
import service.impl.UserServiceImpl;

public class Main {

    public static void main(String[] args) throws DAOException {
        Logger log = Logger.getLogger(Main.class);
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        log.trace("Starting application...");

        List<User> userList = new ArrayList<>();
        userList.add(new User("Иван", "Петренко", "petrenko@gmail.com", "123456", "USER"));
        userList.add(new User("Василий", "Иванов", "vas_ivanov@gmail.com", "123456", "USER"));

        UserService userService = UserServiceImpl.getUserService();
        userList.forEach(user -> {
            try {
                System.out.println(userService.insert(user));
            } catch (DAOException e) {
                log.error("Error occurred!", e);
                e.printStackTrace();
            }
        });

        System.out.println(userService.readByID(2));
        System.out.println(userService.readByEmail("petrenko@gmail.com"));
        userService.updateByID(new User(2, "Джон", "Питерс", "petrenko@gmail.com", "123456", "АDMIN"));
        userService.updateByEmail(new User("Вася", "Иванов", "vas_ivanov@gmail.com", "123456", "USER"));
        userService.delete(1);
        userService.readAll().forEach(System.out::println);

        MagazineService magazineService = MagazineServiceImpl.getMagazineService();
        System.out.println(
                magazineService.insert(new Magazine("Playboy", "Алина Альвинская покоряет шоу-бизнес и продает экзотические острова!",
                        LocalDate.parse("2019-04-01"), 6005)));
        magazineService.readAll().forEach(System.out::println);

        SubscribeService subscribeService = SubscribeServiceImpl.getSubscribeService();
        System.out.println(subscribeService.insert(new Subscribe(2, 1, true, LocalDate.parse("2019-04-26"), 12)));
        subscribeService.readAll().forEach(System.out::println);
    }
}

