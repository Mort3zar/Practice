    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;

    public class DataBaseConnection {
        private static final String DRIVER = "org.postgresql.Driver";

        private static final String PROTOCOL = "jdbc:postgresql://";
        private static final String URL_LOCALE = "localhost/";
        private static final String URL_REMOTE = "localhost:5432/";
        private static final String DATABASE_NAME = "postgres";
        private static final String DATABASE_URL = PROTOCOL + URL_LOCALE + DATABASE_NAME;

        private static final String USER_NAME = "postgres";
        private static final String DATABASE_PASS = "mysecretpassword";

        public static void main(String[] args) {
            if (checkDriver() && checkDB()) {
                System.out.println("Успешное подключение к базе данных | " + DATABASE_URL + "\n");
            }
        }
            public static boolean checkDriver() {
                try {
                    Class.forName(DRIVER);
                    return true;
                } catch (ClassNotFoundException e) {
                    System.out.println("нет драйвера");
                    throw new RuntimeException(e);
                }
            }

            public static boolean checkDB() {
                try (Connection connection = getConnection()) {
                    return connection != null && !connection.isClosed();
                } catch (SQLException e) {
                    System.out.println("Нет подключения к базе данных");
                    throw new RuntimeException(e);
                }
            }
            public static Connection getConnection() throws SQLException {
                return DriverManager.getConnection(DATABASE_URL, USER_NAME, DATABASE_PASS);
            }
    }
