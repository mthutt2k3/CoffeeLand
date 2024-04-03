/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Voucher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.MyDAO;

/**
 *
 * @author acer
 */
public class VoucherDAO extends MyDAO{
    
    public int CountVoucher(){
        xSql = """
               select Count(*) as x from Voucher""";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                int xId = rs.getInt("x");
                return xId;}
        }catch(SQLException e){
        }
        return 0;
    }

    public List<Voucher> getListVoucher(int page, int PPP) {
        List<Voucher> t = new ArrayList<>();
        int start = PPP * (page - 1) + 1;
        int end = PPP * page;
        xSql = """
               select voucherId, voucherName, voucherCode, voucherImage,description, discount, startedDate, expirationDate, format(condition,'#,###') as condition, userName from Voucher
               join Users on Users.userId = Voucher.userId
               ORDER BY voucherId desc
               OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;""";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, start - 1); // Số dòng bắt đầu
            ps.setInt(2, PPP); // Số dòng kết thúc
            rs = ps.executeQuery();
            while (rs.next()) {
                int xvoucherId = rs.getInt("voucherId");
                String xvoucherName = rs.getString("voucherName");
                String xvoucherCode = rs.getString("voucherCode");
                String xvoucherImage = rs.getString("voucherImage");
                String xdiscount = rs.getString("discount");
                String xstartedDate = rs.getString("startedDate");
                String xexpirationDate = rs.getString("expirationDate");
                String xuser = rs.getString("userName");
                String xcondition = rs.getString("condition");
                String xdescriprion = rs.getString("description");
                Voucher x = new Voucher(xvoucherId, xvoucherName, xvoucherCode, xvoucherImage, xdiscount, xdescriprion, xstartedDate, xexpirationDate, xuser, xcondition);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
        }
        return (t);
    }
    public List<Voucher> getListVoucher() {
        List<Voucher> t = new ArrayList<>();
        xSql = """
               select voucherId, voucherName, voucherCode, voucherImage,description, discount, startedDate, expirationDate, format(condition,'#,###') as condition, userName from Voucher
               join Users on Users.userId = Voucher.userId
               ORDER BY voucherId desc
               """;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xvoucherId = rs.getInt("voucherId");
                String xvoucherName = rs.getString("voucherName");
                String xvoucherCode = rs.getString("voucherCode");
                String xvoucherImage = rs.getString("voucherImage");
                String xdiscount = rs.getString("discount");
                String xstartedDate = rs.getString("startedDate");
                String xexpirationDate = rs.getString("expirationDate");
                String xuser = rs.getString("userName");
                String xcondition = rs.getString("condition");
                String xdescriprion = rs.getString("description");
                Voucher x = new Voucher(xvoucherId, xvoucherName, xvoucherCode, xvoucherImage, xdiscount, xdescriprion, xstartedDate, xexpirationDate, xuser, xcondition);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
        }
        return (t);
    }

    public static void main(String[] args) {
        VoucherDAO dao = new VoucherDAO();
        List<Voucher> x= dao.searchVoucher("");
        System.out.println(x);
    }
    public List<Voucher> orderBy(String xColName, String xSortType) {
        List<Voucher> t = new ArrayList<>();
        xSql = "select voucherId, voucherName, voucherCode, voucherImage,description, discount, startedDate, expirationDate, format(condition,'#,###') as condition, userName from Voucher\n" +
"               join Users on Users.userId = Voucher.userId\n" +
"               order by ["+ xColName+"] "+xSortType;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xvoucherId = rs.getInt("voucherId");
                String xvoucherName = rs.getString("voucherName");
                String xvoucherCode = rs.getString("voucherCode");
                String xvoucherImage = rs.getString("voucherImage");
                String xdiscount = rs.getString("discount");
                String xstartedDate = rs.getString("startedDate");
                String xexpirationDate = rs.getString("expirationDate");
                String xuser = rs.getString("userName");
                String xcondition = rs.getString("condition");
                String xdescriprion = rs.getString("description");
                Voucher x = new Voucher(xvoucherId, xvoucherName, xvoucherCode, xvoucherImage, xdiscount, xdescriprion, xstartedDate, xexpirationDate, xuser, xcondition);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
        }
        return (t);
    }

    public boolean isCodeExist(String xvoucherCode) {
        xSql = "select * from Voucher \n"
                + " where voucherCode='" + xvoucherCode + "'";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return false;
    }

    public void insert(Voucher x) {
        xSql = "INSERT INTO Voucher (userId, voucherName,"
                + "description, voucherCode, voucherImage, "
                + "discount, startedDate, expirationDate, condition) VALUES\n" +
"(?,?,?, ?,?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getUser());
            ps.setString(2, x.getVoucherName());
            ps.setString(3, x.getDescription());
            ps.setString(4, x.getVoucherCode());
            ps.setString(5, x.getVoucherImage());
            ps.setString(6, x.getDiscount());
            ps.setString(7, x.getStartedDate());
            ps.setString(8, x.getExpirationDate());
            ps.setString(9, x.getCondition());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
        }
    }

    public Voucher getVoucher(int xId) {
        Voucher x = null;
        xSql = "select voucherId, voucherName, voucherCode, voucherImage,description, discount, startedDate, expirationDate, condition, userName from Voucher\n" +
"join Users on Users.userId = Voucher.userId\n"
                + " where voucherId=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int xvoucherId = rs.getInt("voucherId");
                String xvoucherName = rs.getString("voucherName");
                String xvoucherCode = rs.getString("voucherCode");
                String xvoucherImage = rs.getString("voucherImage");
                String xdiscount = rs.getString("discount");
                String xstartedDate = rs.getString("startedDate");
                String xexpirationDate = rs.getString("expirationDate");
                String xuser = rs.getString("userName");
                String xcondition = rs.getString("condition");
                String xdescriprion = rs.getString("description");
                x = new Voucher(xvoucherId, xvoucherName, xvoucherCode, xvoucherImage, xdiscount, xdescriprion, xstartedDate, xexpirationDate, xuser, xcondition);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (x);
    }

    public void update(int voucherId, Voucher x) {
        xSql = "UPDATE Voucher SET voucherName=?, voucherCode=?, voucherImage=?, discount=?, startedDate=?, expirationDate=?, userId=?, description=?, condition=? WHERE voucherId=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getVoucherName());
            ps.setString(2, x.getVoucherCode());
            ps.setString(3, x.getVoucherImage());
            ps.setString(4, x.getDiscount());
            ps.setString(5, x.getStartedDate());
            ps.setString(6, x.getExpirationDate());
            ps.setString(7, x.getUser());
            ps.setString(8, x.description);
            ps.setString(9, x.condition);
            ps.setInt(10, voucherId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
        }
    }

    public List<Voucher> searchVoucher(String xInput) {
        List<Voucher> t = new ArrayList<>();
        xSql = "select voucherId, voucherName, voucherCode, voucherImage,description, discount, startedDate, expirationDate, format(condition,'#,###') as condition, userName from Voucher\n" +
"               join Users on Users.userId = Voucher.userId\n" +
"               WHERE voucherName like N'%"+ xInput +"%' or voucherCode like N'%"+ xInput +"%' or description like N'%"+ xInput +"%'\n";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xvoucherId = rs.getInt("voucherId");
                String xvoucherName = rs.getString("voucherName");
                String xvoucherCode = rs.getString("voucherCode");
                String xvoucherImage = rs.getString("voucherImage");
                String xdiscount = rs.getString("discount");
                String xstartedDate = rs.getString("startedDate");
                String xexpirationDate = rs.getString("expirationDate");
                String xuser = rs.getString("userName");
                String xcondition = rs.getString("condition");
                String xdescriprion = rs.getString("description");
                Voucher x = new Voucher(xvoucherId, xvoucherName, xvoucherCode, xvoucherImage, xdiscount, xdescriprion, xstartedDate, xexpirationDate, xuser, xcondition);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
        }
        return (t);
    }
    
    
}

    

