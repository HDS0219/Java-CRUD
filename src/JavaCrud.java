import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JavaCrud {
    
    protected static void connect() {

        String url = "jdbc:sqlite:data.db";
        File dbFile = new File("data.db");
        boolean existed = dbFile.exists();


        var sql = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL, "
                + "email TEXT NOT NULL UNIQUE, "
                + "phone_num TEXT"
                + ");";


        try (var conn = DriverManager.getConnection(url);
            var stmt = conn.createStatement()) {

            stmt.execute(sql);

            System.out.println("Connection to SQlite established");
            if (existed) {
                System.out.println("Database already existed.");
            } else {
                System.out.println("Database created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    protected static void insertData(String name, String email, String phone_num){

        String url = "jdbc:sqlite:data.db";

        final String INSERT_SQL = "INSERT INTO users(name, email, phone_num) VALUES(?,?,?)";

        try (var conn = DriverManager.getConnection(url);
         var ps = conn.prepareStatement(INSERT_SQL)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone_num);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    protected static void updateData(String name, String email, String phone_num, Integer id){

        String url = "jdbc:sqlite:data.db";

        final String UPDATE_SQL = "UPDATE users SET name = ?, email = ?, phone_num = ? WHERE id = ?";


        try (var conn = DriverManager.getConnection(url);
         var ps = conn.prepareStatement(UPDATE_SQL)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone_num);
            ps.setInt(4, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    protected static void selectData() {

    String url = "jdbc:sqlite:data.db";
    final String SELECT_SQL =
            "SELECT id, name, email, phone_num FROM users";

    try (var conn = DriverManager.getConnection(url);
         var ps = conn.prepareStatement(SELECT_SQL);
         var rs = ps.executeQuery()) {

        while (rs.next()) {
            System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getString("email") + " | " +
                    rs.getString("phone_num")
            );
        }

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}



    public static void main(String[] args) {

        // Initialize the database and ensure the table exists
        connect();

        // Used only for manual testing
        /*
        insertData(
                "Test",
                "Test@email.com",
                "555-555-555"
        );

         */

        // Updates an existing record (example usage)
        /*
        updateData(
                "Teste1",
                "teste2@gmail.com",
                "555-555-555-2",
                1
        );
         */

        selectData();

    }
}