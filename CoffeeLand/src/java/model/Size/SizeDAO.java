/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Size;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.MyDAO;
import model.Product.Product;

/**
 *
 * @author acer
 */
public class SizeDAO extends MyDAO{
    public List<Size> getListSize() {
        List<Size> t = new ArrayList<>();
           xSql = "select * from Size";
           try {
             ps = con.prepareStatement(xSql);
             rs = ps.executeQuery();
             while(rs.next()) {
                int xSizeId = Integer.parseInt(rs.getString("sizeId"));
                String xSizeName = rs.getString("sizeName");
               Size  x = new Size(xSizeId, xSizeName);
               t.add(x);
             }
             rs.close();
             ps.close();
            }
            catch(SQLException e) {
            }
        return(t);
    }
}
