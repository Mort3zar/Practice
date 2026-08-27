import java.sql.*;
import java.time.LocalDate;

public class DataBaseWork {
    public void getAllAgents(Connection connection) throws SQLException {
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
    public void getAllPlayers(Connection connection) throws SQLException{
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

    public void getAllClubs(Connection connection) throws SQLException{
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

    public void getAllContracts(Connection connection) throws SQLException{
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

    public void getFinanceInfoAboutClub(Connection connection, String name) throws SQLException {
        if (connection == null || connection.isClosed()) return;
        if (name == null || name.isBlank()) return;
        String param;
        name = "%" + name + "%";
        PreparedStatement statement = connection.prepareStatement(
                "SELECT\n" +
                        "\tC.NAME,\n" +
                        "\tC.CITY,\n" +
                        "\tC.BUDGET,\n" +
                        "\tCOUNT(CT.ID),\n" +
                        "\tSUM(CT.WEEKLY_SALARY)\n" +
                        "FROM CLUBS C\n" +
                        "JOIN CONTRACTS CT ON C.ID = CT.CLUB_ID\n" +
                        "WHERE C.NAME LIKE ?\n" +
                        "GROUP BY C.ID, C.NAME, C.CITY, C.BUDGET;"
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

    public void vacantAgent(Connection connection, int budget, double rating, String position) throws SQLException {
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

    public void getHighestPayedPlayer(Connection connection, String name) throws SQLException {
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

    public void addPlayer(Connection connection, String name, String position, String nationality) throws SQLException{
        if (connection == null || connection.isClosed()) return;
        if (name == null || name.isBlank()) return;
        PreparedStatement statement = connection.prepareStatement("INSERT into players(full_name, position, nationality) VALUES (?, ?, ?);");
        statement.setString(1, name);
        statement.setString(2, position);
        statement.setString(3, nationality);
        statement.executeUpdate();
        statement.close();
        getAllPlayers(connection);
    }

    public void addAgent(Connection connection, String name, double rating) throws SQLException{
        if (connection == null || connection.isClosed()) return;
        if (name == null || name.isBlank()) return;
        PreparedStatement statement = connection.prepareStatement("INSERT into agents(full_name, rating) VALUES (?, ?);");
        statement.setString(1, name);
        statement.setDouble(2, rating);
        statement.executeUpdate();
        statement.close();
        getAllAgents(connection);
    }

    public void addClub(Connection connection, String name, String city, int budget) throws SQLException{
        if (connection == null || connection.isClosed()) return;
        if (name == null || name.isBlank()) return;
        PreparedStatement statement = connection.prepareStatement("INSERT into clubs(name, city, budget) VALUES (?, ?, ?);");
        statement.setString(1, name);
        statement.setString(2, city);
        statement.setInt(3, budget);
        statement.executeUpdate();
        statement.close();
        getAllClubs(connection);
    }

    public void addContract(Connection connection, int clubId, int playerId, int salary, LocalDate startDate, LocalDate endDate) throws SQLException{
        if (connection == null || connection.isClosed()) return;
        if (clubId <=0) return;
        PreparedStatement statement = connection.prepareStatement("INSERT into contracts(club_id, player_id, weekly_salary, start_date, end_date) VALUES (?, ?, ?, ?, ?);");
        statement.setInt(1, clubId);
        statement.setInt(2, playerId);
        statement.setInt(3, salary);
        statement.setDate(4, Date.valueOf(startDate));
        statement.setDate(5, Date.valueOf(endDate));
        statement.executeUpdate();
        statement.close();
        getAllContracts(connection);
    }
    public void correctPlayerPosition(Connection connection, String name, String position)throws SQLException{
        if (connection == null || connection.isClosed()) return;
        if (name == null || name.isBlank()) return;
        PreparedStatement statement = connection.prepareStatement("UPDATE players SET position=? WHERE full_name=?;");
        statement.setString(1, position);
        statement.setString(2, name);

        int count = statement.executeUpdate();
        System.out.println("UPDATEd " + count + " players");

        statement.close();
        getAllPlayers(connection);
    }


}


