import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/sys";
    private static final String USER = "root"; // например, root
    private static final String PASSWORD = "qW3$rM6#pL9Z";

    public static void main(String[] args) {
        System.out.println("Пытаюсь подключиться к MySQL...");

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("✅ Успешное подключение к MySQL!");
            System.out.println("Версия сервера: " + connection.getMetaData().getDatabaseProductVersion());
        } catch (SQLException e) {
            System.err.println("❌ Ошибка подключения:");
            e.printStackTrace();

            // Расшифровка распространенных ошибок
            if (e.getErrorCode() == 1045) {
                System.err.println("\nПодсказка: Неверный логин/пароль");
            } else if (e.getErrorCode() == 1049) {
                System.err.println("\nПодсказка: База данных не существует");
            } else if (e.getMessage().contains("Communications link failure")) {
                System.err.println("\nПодсказка: MySQL сервер не запущен или недоступен");
            }
        }
    }
}