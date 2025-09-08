package org.example;
import org.example.domain.User;
import org.example.mapper.UserMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;
import java.util.Scanner;


public class App {

    private static UserMapper userMapper;

    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        userMapper = context.getBean(UserMapper.class);

        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("\n=== User Management System ===");
            System.out.println("1.Add User");
            System.out.println("2.Get All Users");
            System.out.println("3.Update User");
            System.out.println("4.Delete User");
            System.out.println("5.Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();     // 사용자 입력 받는 변수
            scanner.nextLine();

            switch(choice) {
                case 1:
                    System.out.print("Enter User Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter User email: ");
                    String email = scanner.nextLine();
                    insertUser(name, email);
                    break;
                case 2:
                    System.out.println("All Users List: " + getAllUsers());
                    break;
                case 3:
                    System.out.print("Enter id to update: ");
                    Long update_id = scanner.nextLong();
                    Long up
                    updateUser(update_id);
                    break;
                case 4:
                    System.out.print("Enter id to delete: ");
                    Long delete_id = scanner.nextLong();
                    deleteUser(delete_id);
                    break;
                case 5:
                    return;
            }
        }
    }

    //Insert User
    private static void insertUser(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userMapper.addUser(user);
        System.out.println("new user: " + user);
    }

    //Get All Users
    private static List<User> getAllUsers() {
        List<User> users = userMapper.getAllUsers();
        return users;
    }

    //Update User
    private static User updateUser(Long id) {
        User user = userMapper.getUserById(id);
        userMapper.updateUser(user);
        return user;
    }

    //Delete User
    private static void deleteUser(Long id) {
        User deletedUser = userMapper.getUserById(id);
        userMapper.deleteUser(deletedUser.getId());
        System.out.println("deleted user: " + deletedUser);
        System.out.println("remaining users: " + getAllUsers());
    }
}
