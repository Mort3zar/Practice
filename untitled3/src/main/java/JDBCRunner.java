import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCRunner {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        try (Connection connection = DataBaseConnection.getConnection()){
            while (true){
                String number = scanner.nextLine();
                switch (number){
                    case "1":
                        DataBaseWork.getAllAgents(connection);break;
                    case "2":
                        DataBaseWork.getAllPlayers(connection);break;
                    case "3":
                        DataBaseWork.getAllClubs(connection);break;
                    case "4":
                        DataBaseWork.getAllContracts(connection);break;
                    case "5":
                        System.out.println("Введите имя игрока: ");
                        String playerName = scanner.nextLine();
                        DataBaseWork.getPlayerByName(connection, playerName);break;
                    case "6":
                        System.out.println("Введите бюджет клуба");
                        int budget = Integer.parseInt(scanner.nextLine());
                        System.out.println("Введите рейтинг агента");
                        double rating = Double.parseDouble(scanner.nextLine());
                        System.out.println("Введите позицию игрока");
                        String position = scanner.nextLine();
                        DataBaseWork.vacantAgent(connection, budget, rating, position);break;
                    case "7":
                        System.out.println("Введите имя игрока");
                        String highPlayerName = scanner.nextLine();
                        DataBaseWork.getHighestPayedPlayer(connection, highPlayerName);break;

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}