import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class JDBCRunner {
    private static Scanner scanner = new Scanner(System.in);
    private static DataBaseWork dataBaseWork = new DataBaseWork();

    public static void main(String[] args) {
        boolean flag = true;
        if (!DataBaseConnection.checkDB() || !DataBaseConnection.checkDriver()) {
            System.out.println("Ошибка подключения");
            return;
        }
        try (Connection connection = DataBaseConnection.getConnection()) {
            while (flag) {
                printMenu();
                String number = scanner.nextLine();
                switch (number) {
                    case "1": dataBaseWork.getAllAgents(connection);break;
                    case "2": dataBaseWork.getAllPlayers(connection);break;
                    case "3": dataBaseWork.getAllClubs(connection);break;
                    case "4": dataBaseWork.getAllContracts(connection);break;
                    case "5": getFinanceInfoAboutClub(connection);break;
                    case "6": vacantAgent(connection);break;
                    case "7": getHighestPayedPlayer(connection);break;
                    case "8": addPlayer(connection);break;
                    case "9": addAgent(connection);break;
                    case "10": addClub(connection);break;
                    case "11": addContract(connection);break;
                    case "12": correctPlayerPosition(connection);break;
                    case "13": flag = false;break;
                    default:
                        System.out.println("неверный формат данных");}

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
        }
    }

    public static void getFinanceInfoAboutClub(Connection connection) throws SQLException {
        System.out.println("Введите название клуба: ");
        String clubName = scanner.nextLine();
        dataBaseWork.getFinanceInfoAboutClub(connection, clubName);
    }

    public static void vacantAgent(Connection connection) throws SQLException {
        System.out.println("Введите бюджет клуба");
        int budget = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите рейтинг агента");
        double rating = Double.parseDouble(scanner.nextLine());

        System.out.println("Введите позицию игрока");
        String position = scanner.nextLine();

        dataBaseWork.vacantAgent(connection, budget, rating, position);
    }

    public static void getHighestPayedPlayer(Connection connection)throws SQLException{
        System.out.println("Введите имя агента");
        String highAgentName = scanner.nextLine();

        dataBaseWork.getHighestPayedPlayer(connection, highAgentName);
    }

    public static void addPlayer(Connection connection)throws SQLException{
        System.out.println("Введите имя игрока: ");
        String name = scanner.nextLine();

        System.out.println("Введите позицию игрока: ");
        String addPosition = scanner.nextLine();

        System.out.println("Введите национальность игрока: ");
        String addNationality = scanner.nextLine();

        dataBaseWork.addPlayer(connection, name, addPosition, addNationality);
    }

    public static void addAgent(Connection connection)throws SQLException{
        System.out.println("Введите имя агента: ");
        String addAgentName = scanner.nextLine();

        System.out.println("Введите рейтинг агента: ");
        double addRating = Double.parseDouble(scanner.nextLine());

        dataBaseWork.addAgent(connection, addAgentName, addRating);
    }
    public static void addClub(Connection connection)throws SQLException{
        System.out.println("Введите название клуба");
        String addClubName = scanner.nextLine();

        System.out.println("Введите город");
        String addCity = scanner.nextLine();

        System.out.println("Введите бюджет клуба");
        int addBudget = Integer.parseInt(scanner.nextLine());

        dataBaseWork.addClub(connection, addClubName, addCity, addBudget);
    }

    public static void addContract(Connection connection)throws SQLException{
        System.out.println("Введите id клуба который хочет подписать контракт ");
        int addIdClub = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите id игрока, которого хочет подписать клуб ");
        int addIdPlayer = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите зарплату, которую клуб готов выплачивать игроку ");
        int addSalary = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите дату начала контракта");
        LocalDate addStartDate = LocalDate.parse(scanner.nextLine());

        System.out.println("Введите дату окончания контракта");
        LocalDate addEndDate = LocalDate.parse(scanner.nextLine());

        dataBaseWork.addContract(connection, addIdClub, addIdPlayer, addSalary, addStartDate, addEndDate);
    }
    public static void correctPlayerPosition(Connection connection)throws SQLException{
        System.out.println("Введите игрока, позицию которого вы хотите поменять");
        String correctPlayer = scanner.nextLine();
        System.out.println("Введите новую позицию игрока");
        String newPosition = scanner.nextLine();
        dataBaseWork.correctPlayerPosition(connection, correctPlayer, newPosition);
    }
    private static void printMenu() {
        System.out.println("1. Список агентов");
        System.out.println("2. Список игроков");
        System.out.println("3. Список клубов");
        System.out.println("4. Список контрактов");
        System.out.println("5. Финансовая информация о клубе");
        System.out.println("6. Поиск игроков по критериям");
        System.out.println("7. Топ-3 игроков агента");
        System.out.println("8. Добавить игрока");
        System.out.println("9. Добавить агента");
        System.out.println("10. Добавить клуб");
        System.out.println("11. Добавить контракт");
        System.out.println("12. Изменить позицию игрока");
        System.out.println("13. Выход");

    }


}