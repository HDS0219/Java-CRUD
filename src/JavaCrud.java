import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JavaCrud {
    public static void connect(){

        String url = "jdbc:sqlite:data.db";
        File dbFile = new File("data.db");
        boolean existed = dbFile.exists();

        try (var conn = DriverManager.getConnection(url)){
            System.out.println("Connection to SQlite established"); // Debug message
            if (existed) {
                System.out.println("Database already existed.");
            } else {
                System.out.println("Database created.");
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        //connect();


    }
}
