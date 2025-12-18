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
                + "nome TEXT NOT NULL, "
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

    protected static void insertData(){

        String url = "jdbc:sqlite:data.db";

    }

    public static void main(String[] args) {

        connect();
    }
}