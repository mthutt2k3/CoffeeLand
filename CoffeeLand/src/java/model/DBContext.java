package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category.Category;
import model.Category.CategoryDAO;
import model.Product.Product;
import model.Product.ProductDAO;

/**
 *
 * @author acer
 */
public class DBContext {

    protected Connection connection;

    public DBContext() {
        //@Students: You are allowed to edit user, pass, url variables to fit 
        //your system configuration
        //You can also add more methods for Database Interaction tasks. 
        //But we recommend you to do it in another class
        // For example : StudentDBContext extends DBContext , 
        //where StudentDBContext is located in dal package, 
        try {
            String user = "sa";
            String pass = "123";
            String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=SWP391_FinalData;encrypt=true;trustServerCertificate=true";
            //String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=test35;encrypt=true;trustServerCertificate=true";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DBContext db = new DBContext();
        System.out.println(db);
        ProductDAO dao = new ProductDAO();
        CategoryDAO cdao = new CategoryDAO();
        Product p = new Product();
        List<Product> lst = new ArrayList<>();
        List<Category> clst = new ArrayList<>();
        lst = dao.getListProduct();
        System.out.println(lst.size());
        System.out.println(clst.size());
    }
}
