/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Client.Voucher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.MyDAO;
import model.NewsClient.News;

/**
 *
 * @author NguyenDucTruong
 */
public class VoucherClientDAO extends MyDAO {

    public List<VoucherClient> getListVoucher() {
        ArrayList<VoucherClient> list = new ArrayList<>();
        try {
            xSql = "SELECT voucherid, userid, vouchername, description,voucherCode,voucherImage, discount,startedDate,expirationDate, FORMAT(condition, '#,###') as condition\n"
                    + "                    FROM Voucher\n"
                    + "                    WHERE expirationDate >= GETDATE()\n"
                    + "                    ORDER BY discount DESC;";
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                VoucherClient s = new VoucherClient(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                list.add(s);

            }
        } catch (SQLException e) {
            System.err.println("erorr");
        }
        return list;
    }

    public List<VoucherClient> searchByName(String sName) {
        List<VoucherClient> t = new ArrayList<>();
        xSql = " SELECT *\n"
                + "FROM Voucher\n"
                + "WHERE expirationDate >= GETDATE() and voucherName LIKE '%" + sName + "%'";
        try {
            ps = con.prepareStatement(xSql);

            rs = ps.executeQuery();
            while (rs.next()) {
                VoucherClient s = new VoucherClient(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                t.add(s);

            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (t);
    }

    public int getTotalVoucher() {
        try {
            xSql = "SELECT COUNT(*) FROM voucher WHERE expirationDate >= GETDATE() ;";
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);

            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<VoucherClient> pagingVoucher(int index) {
        ArrayList<VoucherClient> list = new ArrayList<>();
        try {
            xSql = "SELECT voucherid, userid, vouchername, description,voucherCode,voucherImage, discount,startedDate,expirationDate, FORMAT(condition, '#,###') as condition\n"
                    + "FROM voucher\n"
                    + "WHERE expirationDate >= GETDATE() and voucherid>1\n"
                    + "ORDER BY discount DESC\n"
                    + "OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY;";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, (index - 1) * 3);
            rs = ps.executeQuery();

            while (rs.next()) {
                VoucherClient s = new VoucherClient(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public int count(String txtSearch) {
        try {
            xSql = "select count(*) from news where title like ?";
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public List<News> search(String txtSearch, int index, int size) {
        ArrayList<News> list = new ArrayList<>();
        try {
            xSql = "with x as (select ROW_NUMBER() over "
                    + "(order by postedTime desc) as r , * from "
                    + "news where title like ?) select * from x where\n"
                    + "r between ?*3-2 and ? * 3";
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + txtSearch + "%");
            ps.setInt(2, index);
            ps.setInt(3, index);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new News(rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void main(String[] args) {
        VoucherClientDAO voucherClient = new VoucherClientDAO();
//        int pageIndex = 1; // Trang mặc định là trang đầu tiên
        int vouchers = voucherClient.getTotalVoucher();
        System.out.println(voucherClient.getListVoucher());

        // Kiểm tra xem danh sách voucher có dữ liệu hay không
//        if (vouchers.isEmpty()) {
//            System.out.println("Không có voucher nào được tìm thấy.");
//        } else {
//            // In ra thông tin của từng voucher trong danh sách
//            for (VoucherClient voucher : vouchers) {
//                System.out.println(voucher);
//            }
//        }
    }
}
