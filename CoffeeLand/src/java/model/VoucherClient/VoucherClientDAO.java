/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.VoucherClient;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.MyDAO;
import model.Voucher.Voucher;

/**
 *
 * @author Admin
 */
public class VoucherClientDAO extends MyDAO{
    
    public static void main(String[] args) {
        VoucherClientDAO x= new VoucherClientDAO();
        System.out.println(x.getVoucherByCode("voucher123"));
    }
    
    public VoucherClient getVoucherByCode(String xId) {
        VoucherClient x = null;
        xSql = """
               select voucherId, voucherName, voucherCode, voucherImage, discount, startedDate, expirationDate,condition, userName from Voucher join Users on Voucher.userId = Users.userId
                where voucherCode=?  AND GETDATE() BETWEEN startedDate AND expirationDate;""";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, xId);
            rs = ps.executeQuery();
            if (rs.next()) {
                String xvoucherName = rs.getString("voucherName");
                int voucherId = rs.getInt("voucherId");
                String xvoucherImage = rs.getString("voucherImage");
                String xdiscount = rs.getString("discount");
                String xstartedDate = rs.getString("startedDate");
                String xexpirationDate = rs.getString("expirationDate");
                String xcondition = rs.getString("condition");
                String xuser = rs.getString("userName");
                x = new VoucherClient(voucherId, xvoucherName, xId, xvoucherImage, xdiscount, xstartedDate, xexpirationDate, xcondition, xuser);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (x);
    }
    
    public List<VoucherClient> getListVoucher() {
        List<VoucherClient> t = new ArrayList<>();
        xSql = """
              	WITH x AS (
                   SELECT ROW_NUMBER() OVER (ORDER BY startedDate, expirationDate DESC) AS r, *
                   FROM Voucher WHERE voucherId > 1
               )
               SELECT voucherId, voucherName, voucherCode, voucherImage, discount, startedDate, expirationDate, condition, userName
               FROM x
               JOIN Users ON x.userId = Users.userId
               WHERE voucherId > 1;
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
                String xcondition = rs.getString("condition");
                String xuser = rs.getString("userName");
                VoucherClient x = new VoucherClient(xvoucherId, xvoucherName, xvoucherCode, xvoucherImage, xdiscount, xstartedDate, xexpirationDate, xcondition, xuser);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
        }
        return (t);
    }
    public VoucherClient getVoucher(int xId) {
        VoucherClient x = null;
        xSql = "select voucherId, voucherName, voucherCode, voucherImage, discount, startedDate, expirationDate, condition ,userName from Voucher join Users on Voucher.userId = Users.userId\n"
                + " where voucherId=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xId);
            rs = ps.executeQuery();
            if (rs.next()) {
                String xvoucherName = rs.getString("voucherName");
                String xvoucherCode = rs.getString("voucherCode");
                String xvoucherImage = rs.getString("voucherImage");
                String xdiscount = rs.getString("discount");
                String xstartedDate = rs.getString("startedDate");
                String xexpirationDate = rs.getString("expirationDate");
                String xcondition = rs.getString("condition");
                String xuser = rs.getString("userName");
                x = new VoucherClient(xId, xvoucherName, xvoucherCode, xvoucherImage, xdiscount, xstartedDate, xexpirationDate, xcondition,  xuser);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (x);
    }
}