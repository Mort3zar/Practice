import java.sql.*;

public class DataBaseWork {
    public static void getAllAgents(Connection connection) throws SQLException {
        String param;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM agents;");
        int count = resultSet.getMetaData().getColumnCount();
        System.out.println("СПИСОК АГЕНТОВ");
        while (resultSet.next()){
            param = "";
            for (int i = 1; i <= count; i++) {
                param += resultSet.getString(i);
                if (i != count){
                    param += " | ";
                }
            }
            System.out.println(param);
        }
        statement.close();
        System.out.println();
    }
    public static void getAllPlayers(Connection connection) throws SQLException{
        String param;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM players;");
        int count = resultSet.getMetaData().getColumnCount();
        System.out.println("СПИСОК ИГРОКОВ");
        while (resultSet.next()){
            param = "";
            for (int i = 1; i <= count; i++) {
                param += resultSet.getString(i);
                if (i != count){
                    param += " | ";
                }
            }
            System.out.println(param);
        }
        statement.close();
        System.out.println();
    }

    public static void getAllClubs(Connection connection) throws SQLException{
        String param;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM clubs;");
        int count = resultSet.getMetaData().getColumnCount();
        System.out.println("СПИСОК КЛУБОВ");
        while (resultSet.next()){
            param = "";
            for (int i = 1; i <= count; i++) {
                param += resultSet.getString(i);
                if (i != count){
                    param += " | ";
                }
            }
            System.out.println(param);
        }
        statement.close();
        System.out.println();
    }

    public static void getAllContracts(Connection connection) throws SQLException{
        String param;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM contracts;");
        int count = resultSet.getMetaData().getColumnCount();
        System.out.println("СПИСОК КОНТРАКТОВ");
        while (resultSet.next()){
            param = "";
            for (int i = 1; i <= count; i++) {
                param += resultSet.getString(i);
                if (i != count){
                    param += " | ";
                }
            }
            System.out.println(param);
        }
        statement.close();
        System.out.println();
    }

    public static void getPlayerByName(Connection connection, String name) throws SQLException {
        if (connection == null || connection.isClosed()) return;
        if (name == null || name.isBlank()) return;
        String param;
        name = "%" + name + "%";
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM players WHERE full_name LIKE ?;"
        );
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        int count = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()){
            param = "";
            for (int i = 1; i <= count; i++) {
                param += resultSet.getString(i);
                if (i != count){
                    param += " | ";
                }
            }
            System.out.println(param);
        }
        statement.close();
        System.out.println();
    }

    public static void vacantAgent(Connection connection, int budget, double rating, String position) throws SQLException {
        if (connection == null || connection.isClosed()) return;
        String param;
        position = "%" + position + "%";
        PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT\n" +
                "\tA.FULL_NAME AS AGENT_NAME,\n" +
                "    A.RATING,\n" +
                "    P.FULL_NAME AS PLAYER_NAME,\n" +
                "    P.POSITION,\n" +
                "    C.NAME AS CURRENT_CLUB,\n" +
                "    CT.WEEKLY_SALARY\n" +
                "FROM AGENTS A\n" +
                "JOIN PLAYERS P ON A.ID = P.AGENT_ID\n" +
                "JOIN CONTRACTS CT ON P.ID = CT.PLAYER_ID\n" +
                "JOIN CLUBS C ON CT.CLUB_ID = C.ID\n" +
                "WHERE C.BUDGET >= ?\n" +
                "  AND A.RATING >= ?\n" +
                "  AND P.POSITION LIKE ?\n" +
                "ORDER BY A.RATING DESC;");
        statement.setInt(1, budget);
        statement.setDouble(2, rating);
        statement.setString(3, position);
        ResultSet resultSet = statement.executeQuery();
        int count = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()){
            param = "";
            for (int i = 1; i <= count; i++) {
                param += resultSet.getString(i);
                if (i != count){
                    param += " | ";
                }
            }
            System.out.println(param);
        }
        statement.close();
        System.out.println();
    }

    public static void getHighestPayedPlayer(Connection connection, String name) throws SQLException {
        if (connection == null || connection.isClosed()) return;
        if (name == null || name.isBlank()) return;
        String param;
        name = "%" + name + "%";
        PreparedStatement statement = connection.prepareStatement("SELECT\n" +
                "\tP.FULL_NAME,\n" +
                "\tP.POSITION,\n" +
                "\tP.NATIONALITY,\n" +
                "\tCT.WEEKLY_SALARY,\n" +
                "\tCT.START_DATE,\t\n" +
                "\tCT.END_DATE \n" +
                "FROM AGENTS A \n" +
                "JOIN PLAYERS P ON A.ID = P.AGENT_ID\n" +
                "JOIN CONTRACTS CT ON P.ID = CT.PLAYER_ID\n" +
                "WHERE A.FULL_NAME LIKE  ? \n" +
                "ORDER BY CT.WEEKLY_SALARY DESC \n" +
                "LIMIT 5;");
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        int count = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()){
            param = "";
            for (int i = 1; i <= count ; i++) {
                param += resultSet.getString(i);
                if (i != count){
                    param += " | ";
                }
            }
            System.out.println(param);
        }
        statement.close();
        System.out.println();
    }
}


