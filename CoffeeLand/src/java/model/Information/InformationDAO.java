/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Information;

import java.sql.SQLException;
import java.util.List;
import model.MyDAO;

/**
 *
 * @author acer
 */
public class InformationDAO extends MyDAO {

    public Informations getInformation(String xId) {
        Informations x = null;
        xSql = "select informationId, userName, description, nameStore, image, Informations.address, contactPhone, contactEmail, contactFacebook from Informations join Users on Informations.userId = Users.userId";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                String xuserName = rs.getString("userName");
                String xdescription = rs.getString("description");
                String xnameStore = rs.getString("nameStore");
                String ximage = rs.getString("image");
                String xaddress = rs.getString("address");
                String xcontactPhone = rs.getString("contactPhone");
                String xcontactEmail = rs.getString("contactEmail");
                String xcontactFacebook = rs.getString("contactFacebook");
                x = new Informations(Integer.parseInt(xId), xuserName, xdescription, xnameStore, ximage, xaddress, xcontactPhone, xcontactEmail, xcontactFacebook);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (x);
    }

    public void update(int xinformationId, Informations x) {
        xSql = "update Informations set [userId]=?, [description]=?, [nameStore]=?, [address]=?,[contactPhone]=?, [contactEmail]=?, [contactFacebook]=? where informationId = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getUser());
            ps.setString(2, x.getDescription());
            ps.setString(3, x.getNameStore());
            ps.setString(4, x.getAddress());
            ps.setString(5, x.getContactPhone());
            ps.setString(6, x.getContactEmail());
            ps.setString(7, x.getContactFacebook());
            ps.setInt(8, xinformationId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
        }
    }

    public static void main(String[] args) {
        InformationDAO d = new InformationDAO();
        Informations i = d.getInformation("1");
        // Tạo một đối tượng Informations
        Informations information = new Informations(
            1, // informationId
            "username", // user
            "This is a description", // description
            "Store Name", // nameStore
            "image.jpg", // image
            "Store Address", // address
            "123456789", // contactPhone
            "example@example.com", // contactEmail
            "facebook.com/example" // contactFacebook
        );
        d.update(1, information);
        System.out.println(d.getInformation("1").address);
        System.out.println(d.getInformation("1").contactFacebook);
    }
    public void updateImg(int i, Informations x) {
        xSql = "update Informations set [image]=? where informationId = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getImage());
            ps.setInt(2, i);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
        }
    }
}
