import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;

public class ItemDBC {
	private String jdbcURL;
	private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public ItemDBC(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
     
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
    
    public boolean insertItem(Item item) throws SQLException {
        String sql = "INSERT INTO ITEMS (ID, Name, Category, PricePerDay, Duration) VALUES (?, ?, ?, ?, ?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, item.getID());
        statement.setString(2, item.getName());
        statement.setString(3, item.getCategory());
        statement.setInt(4, item.getPrice());
        statement.setInt(5, item.getDuration());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    
    public List<Item> listAllItems() throws SQLException {
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
             
            Item item = new Item(id, name, category, price, duration);
            listItem.add(item);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listItem;
    }
    
    public boolean deleteItem(Item item) throws SQLException {
        String sql = "DELETE FROM ITEMS where ID = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, item.getID());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
    
    public boolean updateItem(Item item) throws SQLException {
        String sql = "UPDATE ITEMS SET Name = ?, Category = ?, PricePerDay = ?, Duration = ?";
        sql += " WHERE ID = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, item.getName());
        statement.setString(2, item.getCategory());
        statement.setInt(3, item.getPrice());
        statement.setInt(4, item.getDuration());
        statement.setInt(5,  item.getID());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
   
    public Item getItem(int id) throws SQLException {
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
             
            item = new Item(id, name, category, price, duration);
        }
         
        resultSet.close();
        statement.close();
         
        return item;
    }

}
