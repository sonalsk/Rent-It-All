package items;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ItemDBC itemDBC;
    
    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
 
        itemDBC = new ItemDBC(jdbcURL, jdbcUsername, jdbcPassword); 
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
 
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertItem(request, response);
                break;
            case "/delete":
                deleteItem(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateItem(request, response);
                break;
            default:
                listItem(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void listItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Item> listItem = ItemDBC.listAllItems();
        request.setAttribute("listItem", listItem);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ItemList.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("ItemForm.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("ID"));
        Item existingItem = ItemDBC.getItem(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ItemForm.jsp");
        request.setAttribute("item", existingItem);
        dispatcher.forward(request, response);
    }
    
    private void insertItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("Name");
        String category = request.getParameter("category");
        int price = Integer.parseInt(request.getParameter("PricePerDay"));
        int duration = Integer.parseInt(request.getParameter("Duration"));
        int deposit = Integer.parseInt(request.getParameter("SecurityDepo"));
 
        Item newItem = new Item(name, category, price, duration, deposit);
        ItemDBC.insertItem(newItem);
        response.sendRedirect("list");
    }
    
    private void updateItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("ID"));
        String name = request.getParameter("Name");
        String category = request.getParameter("Category");
        int price = Integer.parseInt(request.getParameter("PricePerDay"));
        int duration = Integer.parseInt(request.getParameter("Duration"));
        int deposit = Integer.parseInt(request.getParameter("SecurityDepo"));
        
        Item item = new Item(id, name, category, price, duration, deposit);
        ItemDBC.updateItem(item);
        response.sendRedirect("list");
    }
    
    private void deleteItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("ID"));
 
        Item item = new Item(id);
        ItemDBC.deleteItem(item);
        response.sendRedirect("list");
    }    
}
