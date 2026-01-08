import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

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

    protected static void deleteData(int id){
    String url = "jdbc:sqlite:data.db";

    final String DELETE_SQL = "DELETE FROM users WHERE ID = ?";

    try(var conn = DriverManager.getConnection(url);
        var ps = conn.prepareStatement(DELETE_SQL)){

        ps.setInt(1, id);

        int rowsAffected = ps.executeUpdate();

        System.out.println("Rows deleted: " + rowsAffected);

    } catch (SQLException e){
        System.out.println(e.getMessage());
    }
}





    public static void main(String[] args) {

    // Initialize the database and ensure the table exists
    connect();

    Scanner sc = new Scanner(System.in);
    int option = 0;

    while (option != 5) {
        System.out.println("\n===== USER CRUD MENU =====");
        System.out.println("1 - INSERT A NEW USER");
        System.out.println("2 - DELETE A USER");
        System.out.println("3 - UPDATE USER");
        System.out.println("4 - SEE ALL USERS");
        System.out.println("5 - EXIT");
        System.out.print("Choose an option: ");

        option = sc.nextInt();
        sc.nextLine(); // consume newline

        switch (option) {

            case 1: {
                System.out.println("\n--- INSERT NEW USER ---");
                System.out.print("Enter name: ");
                String name = sc.nextLine();

                System.out.print("Enter email: ");
                String email = sc.nextLine();

                System.out.print("Enter phone number: ");
                String phone = sc.nextLine();

                insertData(name, email, phone);
                System.out.println("✓ User inserted successfully!");
                break;
            }

            case 2: {
                System.out.println("\n--- DELETE USER ---");
                selectData();
                System.out.println("");
                System.out.print("Enter user ID to delete: ");
                int id = sc.nextInt();
                sc.nextLine();

                deleteData(id);
                System.out.println("✓ User deleted successfully!");
                break;
            }

            case 3: {
                System.out.print("Enter user ID to update: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("New name: ");
                String name = sc.nextLine();

                System.out.print("New email: ");
                String email = sc.nextLine();

                System.out.print("New phone number: ");
                String phone = sc.nextLine();

                updateData(name, email, phone, id);
                System.out.println("✓ User updated successfully!");
                break;
            }

            case 4: {
                System.out.println("\n--- USER LIST ---");
                selectData();
                break;
            }

            case 5:
                System.out.println("\n✓ Goodbye! Exiting system...");
                break;

            default:
                System.out.println("\n✗ Invalid option! Please choose between 1 and 5.");
        }
    }

    sc.close();
}

}