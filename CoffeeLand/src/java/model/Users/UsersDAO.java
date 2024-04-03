/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Client.User;
import model.MyDAO;

/**
 *
 * @author lap21
 */
public class UsersDAO extends MyDAO {
    
    public void updatePassword(String email, String newpass) {
        String sql = "UPDATE Users SET password = ? WHERE email = ? and roleId != 4";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newpass);
            st.setString(2, email);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean checkUniqueEmail(String email) {
        String sql = "SELECT * FROM [dbo].[Users] WHERE email = ? AND roleID != 4";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            // Nếu rs.next() trả về true, tức là đã có người dùng có cùng email hoặc số điện thoại
            return !rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Users getLast() {

        xSql = """
               SELECT TOP 1 *
               FROM Users
               ORDER BY userId DESC;""";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                String xId = rs.getString("userId");
                String xEmail = rs.getString("email");
                String xUserName = rs.getString("userName");
                String xName = rs.getString("name");
                String xPhone = rs.getString("phoneNumber");
                String xRole = rs.getString("roleID");
                String xAddress = rs.getString("address");
                String xPassword = rs.getString("password");
                String xAvatar = rs.getString("avatar");
                String xStates = rs.getString("statusID");
                Users x = new Users(xId, xUserName, xName, xPhone, xEmail, xPassword, xAddress, xAvatar, xRole, xStates);
                return (x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int CountUsers(){
        xSql = """
               select Count(*) as x from Users where statusID = 1""";
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
    
    public int CountBannedUsers(){
        xSql = """
               select Count(*) as x from Users where statusID='2' """;
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

    public List<Users> getListUsers(int page, int PPP) {
        List<Users> t = new ArrayList<>();
        int start= PPP*(page-1)+1;
        int end= PPP*page;
        xSql = """
               WITH x AS(SELECT ROW_NUMBER() OVER(ORDER BY userId ASC) AS r
               ,* FROM Users where statusID !='2')
               SELECT *
                              FROM     x INNER JOIN Roles on Roles.roleId = x.roleID
               								  where r between ? and ?
               """;
              
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, start);
            ps.setInt(2, end);
            rs = ps.executeQuery();
            while (rs.next()) {
                String xId = rs.getString("userId");
                String xName = rs.getString("name");
                String xUserName = rs.getString("userName");
                String xPhone = rs.getString("phoneNumber");
                String xEmail = rs.getString("email");
                String xPassword = rs.getString("password");
                String xAddress = rs.getString("address");
                String xAvatar = rs.getString("avatar");
                String xRole = rs.getString("roleName");
                String xStates = rs.getString("statusID");
                Users x = new Users(xId, xUserName, xName, xPhone, xEmail, xPassword, xAddress, xAvatar, xRole, xStates);

                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<Users> getListBannedUsers(int page, int PPP) {
        List<Users> t = new ArrayList<>();
        int start= PPP*(page-1)+1;
        int end= PPP*page;
        xSql = """
               WITH x AS (
                   SELECT 
                       ROW_NUMBER() OVER (ORDER BY U.userId ASC) AS r,
                       *
                   FROM 
                       Users U
               		
                   where statusID='2'
               )
               SELECT 
                   * 
               FROM     
                   x   INNER JOIN 
                       Roles R ON x.roleID = R.roleId
               WHERE 
                   r BETWEEN ? AND ?;""";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, start);
            ps.setInt(2, end);
            rs = ps.executeQuery();
            while (rs.next()) {
                String xId = rs.getString("userId");
                String xName = rs.getString("name");
                String xUserName = rs.getString("userName");
                String xPhone = rs.getString("phoneNumber");
                String xEmail = rs.getString("email");
                String xPassword = rs.getString("password");
                String xAddress = rs.getString("address");
                String xAvatar = rs.getString("avatar");
                String xRole = rs.getString("roleName");
                String xStates = rs.getString("statusID");
                Users x = new Users(xId, xUserName, xName, xPhone, xEmail, xPassword, xAddress, xAvatar, xRole, xStates);

                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<Users> getListCustomer(int page, int PPP) {
        List<Users> t = new ArrayList<>();
        int start= PPP*(page-1)+1;
        int end= PPP*page;
        xSql = """
               WITH x AS(SELECT ROW_NUMBER() OVER(ORDER BY userId ASC) AS r
                              ,* FROM Users where statusID !='2' AND roleID='4')
                              SELECT *
                                             FROM     x INNER JOIN Roles on Roles.roleId = x.roleID
                              								  where r between ? and ?
               """;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, start);
            ps.setInt(2, end);
            rs = ps.executeQuery();
            while (rs.next()) {
                String xId = rs.getString("userId");
                String xUserName = rs.getString("userName");
                String xName = rs.getString("name");
                String xPhone = rs.getString("phoneNumber");
                String xEmail = rs.getString("email");
                String xAddress = rs.getString("address");
                String xPassword = rs.getString("password");
                String xAvatar = rs.getString("avatar");
                String xRole = rs.getString("roleName");
                String xStates = rs.getString("statusID");
                Users x = new Users(xId, xUserName, xName, xPhone, xEmail, xPassword, xAddress, xAvatar, xRole, xStates);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public Users getUsersById(int Id) {

        xSql = "select * from Users INNER JOIN \n" +
"        Roles R ON Users.roleID = R.roleId where userId= ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, Id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String xId = rs.getString("userId");
                String xUserName = rs.getString("userName");
                String xName = rs.getString("name");
                String xEmail = rs.getString("email");
                String xPhone = rs.getString("phoneNumber");
                String xAddress = rs.getString("address");
                String xPassword = rs.getString("password");
                String xAvatar = rs.getString("avatar");
                String xRole = rs.getString("roleId");
                String xRoleName = rs.getString("roleName");
                String xStates = rs.getString("statusID");
                Users x = new Users(xId, xUserName, xName, xPhone, xEmail, xPassword, xAddress, xAvatar, xRole, xRoleName, xStates);
                return (x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Users getUsersByEmail(String xEmail, String xRole) {

        xSql = "select * from Users where email= ? AND roleID =?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, xEmail);
            ps.setString(2, xRole);
            rs = ps.executeQuery();
            if (rs.next()) {
                String xId = rs.getString("userId");
                String xUserName = rs.getString("userName");
                String xName = rs.getString("name");
                String xPhone = rs.getString("phoneNumber");
                String xAddress = rs.getString("address");
                String xPassword = rs.getString("password");
                String xAvatar = rs.getString("avatar");
                String xStates = rs.getString("statusID");
                Users x = new Users(xId, xUserName, xName, xPhone, xEmail, xPassword, xAddress, xAvatar, xRole, xStates);
                return (x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    



    public void insert(Users x) {
        xSql = """
                               INSERT INTO Users (userName, name,phoneNumber, email, password, address, avatar, roleId, statusID) VALUES
                                                              (?,?,?,?,?,?,?,?,?);""";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.userName);
            ps.setString(2, x.getName());
            ps.setString(3, x.getPhoneNumber());
            ps.setString(4, x.getEmail());
            ps.setString(5, x.getPassword());
            ps.setString(6, x.getAddress());
            ps.setString(7, x.getAvatar());
            ps.setString(8, x.getRoleId());
            ps.setString(9, x.getStatusId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inactiveUser(String Id) {
        xSql = """
               UPDATE Users
               SET statusID = 2
               WHERE userId = ?;""";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, Id);
            ps.executeUpdate();
            //con.commit();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void activeUser(String Id) {
        xSql = """
               UPDATE Users
               SET statusID = 1
               WHERE userId = ?;""";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, Id);
            ps.executeUpdate();
            //con.commit();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   

    public List<Users> searchUsers(String xString, int page, int PPP) {
        String q = removeAccent(xString);
        int start= PPP*(page-1)+1;
        int end= PPP*page;
        List<Users> t = new ArrayList<>();
        xSql = "WITH x AS(SELECT ROW_NUMBER() OVER(ORDER BY userId ASC) AS r\n" +
"               ,* FROM Users where name LIKE N'%"+xString+"%' OR name LIKE '%"+q+"%' OR   phoneNumber LIKE N'%"+xString+"%' OR userName LIKE N'%"+xString+"%' OR email LIKE N'%"+xString+"%')\n" +
"               SELECT x.userId, x.userName, x.name, x.phoneNumber, x.email, x.password, x.address, x.avatar, Roles.roleName, x.statusID\n" +
"                              FROM     x INNER JOIN Roles on Roles.roleId = x.roleID\n" +
"               								  where r between ? and ?";
        try {
            ps = con.prepareStatement(xSql);
             ps.setInt(1, start);
            ps.setInt(2, end);
            rs = ps.executeQuery();
            while (rs.next()) {
                String xId = rs.getString("userId");
                String xUserName = rs.getString("userName");
                String xName = rs.getString("name");
                String xPhone = rs.getString("phoneNumber");
                String xEmail = rs.getString("email");
                String xAddress = rs.getString("address");
                String xPassword = rs.getString("password");
                String xAvatar = rs.getString("avatar");
                String xRole = rs.getString("roleName");
                String xStates = rs.getString("statusID");
                Users x = new Users(xId, xUserName, xName, xPhone, xEmail, xPassword, xAddress, xAvatar, xRole, xStates);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (t);
    }
    private static final char[] SOURCE_CHARACTERS = {'À', 'Á', 'Â', 'Ã', 'È', 'É',
            'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â',
            'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý',
            'Ă', 'ă', 'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ',
            'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ',
            'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ',
            'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ',
            'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ',
            'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ',
            'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ',
            'ữ', 'Ự', 'ự',};

    private static final char[] DESTINATION_CHARACTERS = {'A', 'A', 'A', 'A', 'E',
            'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a',
            'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u',
            'y', 'A', 'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u',
            'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
            'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e',
            'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
            'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
            'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
            'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
            'U', 'u', 'U', 'u',};

    public static char removeAccent(char ch) {
        int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
        if (index >= 0) {
            ch = DESTINATION_CHARACTERS[index];
        }
        return ch;
    }

    public static String removeAccent(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAccent(sb.charAt(i)));
        }
        return sb.toString();
    }

    public List<Users> searchCustomer(String xString) {
        List<Users> t = new ArrayList<>();
        String q = removeAccent(xString);
        xSql = """
               SELECT Users.userId, Users.userName, Users.name, Users.phoneNumber, Users.email, Users.password, Users.address, Users.avatar, Roles.roleName, Users.statusID
               
                            FROM     Roles INNER JOIN
                                                Users ON Roles.roleId = Users.roleID
               WHERE ((name LIKE N'%""" + xString + "%') OR (name LIKE N'%" + q + "%') OR (phoneNumber LIKE N'%" + xString + "%') OR (email LIKE N'%" + xString + "%') OR (email LIKE N'%" + q + "%'))  AND roleName = 'Customer'";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String xId = rs.getString("userId");
                String xUserName = rs.getString("userName");
                String xName = rs.getString("name");
                String xPhone = rs.getString("phoneNumber");
                String xEmail = rs.getString("email");
                String xAddress = rs.getString("address");
                String xPassword = rs.getString("password");
                String xAvatar = rs.getString("avatar");
                String xRole = rs.getString("roleName");
                String xStates = rs.getString("statusID");
                Users x = new Users(xId, xUserName, xName, xPhone, xEmail, xPassword, xAddress, xAvatar, xRole, xStates);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (t);
    }

    public List<Users> orderSaler(String xColName, String xSortType) {
        List<Users> t = new ArrayList<>();
        xSql = """
                              SELECT Users.userId, Users.userName, Users.name, Users.phoneNumber, Users.email, Users.password, Users.address, Users.avatar, Roles.roleName, Users.statusID
                            FROM     Roles INNER JOIN
                                                Users ON Roles.roleId = Users.roleID
               \t\twhere roleName='Customer'\t order by [""" + xColName + "] " + xSortType;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String xId = rs.getString("userId");
                String xUserName = rs.getString("userName");
                String xName = rs.getString("name");
                String xPhone = rs.getString("phoneNumber");
                String xEmail = rs.getString("email");
                String xAddress = rs.getString("address");
                String xPassword = rs.getString("password");
                String xAvatar = rs.getString("avatar");
                String xRole = rs.getString("roleName");
                String xStates = rs.getString("statusID");
                Users x = new Users(xId, xUserName, xName, xPhone, xEmail, xPassword, xAddress, xAvatar, xRole, xStates);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (t);
    }

    public List<Users> orderAdmin(String xColName, String xSortType) {
        List<Users> t = new ArrayList<>();
        xSql = """
                              SELECT Users.userId, Users.userName, Users.name, Users.phoneNumber, Users.email, Users.password, Users.address, Users.avatar, Roles.roleName, Users.statusID
                            FROM     Roles INNER JOIN
                                                Users ON Roles.roleId = Users.roleID
               \t\t\t\t\t\t\t\t order by [""" + xColName + "] " + xSortType;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String xId = rs.getString("userId");
                String xUserName = rs.getString("userName");
                String xName = rs.getString("name");
                String xPhone = rs.getString("phoneNumber");
                String xEmail = rs.getString("email");
                String xAddress = rs.getString("address");
                String xPassword = rs.getString("password");
                String xAvatar = rs.getString("avatar");
                String xRole = rs.getString("roleName");
                String xStates = rs.getString("statusID");
                Users x = new Users(xId, xUserName, xName, xPhone, xEmail, xPassword, xAddress, xAvatar, xRole, xStates);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (t);
    }

    public void update(String id, Users xUsers) {
        xSql = """
                               UPDATE Users
                               SET name = ?, 
                                   phoneNumber = ?, 
                                   email = ?, 
                                   address = ?, 
                                   roleID = ?
                               WHERE userId = ?;""";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, xUsers.getName());
            ps.setString(2, xUsers.getPhoneNumber());
            ps.setString(3, xUsers.getEmail());
            ps.setString(4, xUsers.getAddress());
            ps.setString(5, xUsers.getRoleId());
            ps.setString(6, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
        }
    }

    public List<Users> getListRoleName() {
        List<Users> t = new ArrayList<>();
        xSql = """
               SELECT top 5 * FROM Roles""";
        try {
            ps = con.prepareStatement(xSql);

            rs = ps.executeQuery();
            while (rs.next()) {
                String xRole = rs.getString("roleName");
                Users x = new Users(xRole);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<Users> getListUserByRole(String xRole) {
        List<Users> t = new ArrayList<>();
        xSql = """
                              SELECT Users.userId, Users.userName, Users.name, Users.phoneNumber, Users.email, Users.password, Users.address, Users.avatar, Roles.roleName, Users.statusID
               FROM     Roles INNER JOIN
                                 Users ON Roles.roleId = Users.roleID
               WHERE roleName= ?""";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, xRole);
            rs = ps.executeQuery();
            while (rs.next()) {
                String xId = rs.getString("userId");
                String xUserName = rs.getString("userName");
                String xName = rs.getString("name");
                String xPhone = rs.getString("phoneNumber");
                String xEmail = rs.getString("email");
                String xAddress = rs.getString("address");
                String xPassword = rs.getString("password");
                String xAvatar = rs.getString("avatar");
                String xStates = rs.getString("statusID");
                Users x = new Users(xId, xUserName, xName, xPhone, xEmail, xPassword, xAddress, xAvatar, xRole, xStates);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        return (t);
    }

    public boolean isExistEmail(String email, String role) {
        xSql = "select * from Users\n"
                + "Where email = ? AND roleID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, email);
            ps.setString(2, role);
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

    public boolean isExistPhone(String phone, String role) {
        xSql = "select * from Users\n"
                + "Where phoneNumber = " + phone + "AND roleID = " + role;
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
    
    public User checkNewPassword(int userId, String password){
        xSql = "select * from users where userId = ? and password = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userId);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User(rs.getString("name"), rs.getString("phoneNumber"),
                        rs.getString("email"), rs.getString("password"), rs.getString("address"), rs.getString("avatar"), rs.getInt("roleID"), rs.getInt("statusID"));
                return u;
            }
        } catch (Exception e) {

        }
        return null;
    }
    
    public void updatePassword1(int userID, String newpass) {
        xSql = "UPDATE Users SET password = ? WHERE userId =?";
        try {
            ps = connection.prepareStatement(xSql);
            ps.setString(1, newpass);
            ps.setInt(2, userID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        UsersDAO db = new UsersDAO();
        
        System.out.println(db.getListUserByRole("Customer"));
    }

    public List<Users> getListSaler() {
        List<Users> t = new ArrayList<>();
        xSql = """
               WITH x AS(SELECT ROW_NUMBER() OVER(ORDER BY userId ASC) AS r
                              ,* FROM Users where statusID !='2' AND roleID='3')
                              SELECT *
                                             FROM     x INNER JOIN Roles on Roles.roleId = x.roleID
               """;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String xId = rs.getString("userId");
                String xUserName = rs.getString("userName");
                String xName = rs.getString("name");
                String xPhone = rs.getString("phoneNumber");
                String xEmail = rs.getString("email");
                String xAddress = rs.getString("address");
                String xPassword = rs.getString("password");
                String xAvatar = rs.getString("avatar");
                String xRole = rs.getString("roleName");
                String xStates = rs.getString("statusID");
                Users x = new Users(xId, xUserName, xName, xPhone, xEmail, xPassword, xAddress, xAvatar, xRole, xStates);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }
}
