package items;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;

//data base management class for items table
public class ItemDBC {
	private static String jdbcURL;
	private static String jdbcUsername;
    private static String jdbcPassword;
    private static Connection jdbcConnection;
     
    public ItemDBC(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
     
    protected static void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     
    protected static void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
    
    public static boolean insertItem(Item item) throws SQLException {
        String sql = "INSERT INTO ITEMS (ID, Name, Category, PricePerDay, Duration, SecurityDepo) VALUES (?, ?, ?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, item.getID());
        statement.setString(2, item.getName());
        statement.setString(3, item.getCategory());
        statement.setInt(4, item.getPrice());
        statement.setInt(5, item.getDuration());
        statement.setInt(6, item.getDeposit());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    
    public static List<Item> listAllItems() throws SQLException {
        List<Item> listItem = new ArrayList<>();
         
        String sql = "SELECT * FROM ITEMS";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("Name");
            String category = resultSet.getString("Category");
            int price = resultSet.getInt("PricePerDay");
            int duration = resultSet.getInt("Duration");
            int deposit = resultSet.getInt("SecurityDepo");
             
            Item item = new Item(id, name, category, price, duration, deposit);
            listItem.add(item);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listItem;
    }
    
    public static boolean deleteItem(Item item) throws SQLException {
        String sql = "DELETE FROM ITEMS where ID = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, item.getID());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
    
    public static boolean updateItem(Item item) throws SQLException {
        String sql = "UPDATE ITEMS SET Name = ?, Category = ?, PricePerDay = ?, Duration = ?, SecurityDepo = ?";
        sql += " WHERE ID = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, item.getName());
        statement.setString(2, item.getCategory());
        statement.setInt(3, item.getPrice());
        statement.setInt(4, item.getDuration());
        statement.setInt(5,  item.getDeposit());
        statement.setInt(6,  item.getID());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
   
    public static Item getItem(int id) throws SQLException {
        Item item = null;
        String sql = "SELECT * FROM ITEMS WHERE ID = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String name = resultSet.getString("Name");
            String category = resultSet.getString("Category");
            int price = resultSet.getInt("PricePerDay");
            int duration = resultSet.getInt("Duration");
            int deposit = resultSet.getInt("SecurityDepo");
             
            item = new Item(id, name, category, price, duration, deposit);
        }
         
        resultSet.close();
        statement.close();
         
        return item;
    }

}
