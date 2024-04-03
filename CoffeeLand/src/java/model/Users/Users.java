/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Users;

/**
 *
 * @author lap21
 */
public class Users {
    String userId, userName;
    String name, phoneNumber, email, password, address, avatar;
    String roleId, roleName, statusId;

    public Users() {
    }

    public Users(String userId, String userName, String name, String phoneNumber, String email, String password, String address, String avatar, String roleId, String roleName, String statusId) {
        this.userId = userId;
        this.userName = userName;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.address = address;
        this.avatar = avatar;
        this.roleId = roleId;
        this.roleName = roleName;
        this.statusId = statusId;
    }

    public Users(String userId, String userName, String name, String phoneNumber, String email, String password, String address, String avatar, String roleId, String statusId) {
        this.userId = userId;
        this.userName = userName;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.address = address;
        this.avatar = avatar;
        this.roleId = roleId;
        this.statusId = statusId;
    }
    
    public Users( String userName, String name, String phoneNumber, String email, String password, String address, String avatar, String roleId, String statusId) {
        
        this.userName = userName;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.address = address;
        this.avatar = avatar;
        this.roleId = roleId;
        this.statusId = statusId;
    }
    
    
    public Users(String roleId) {
        
        this.roleId = roleId;
    }
    public Users(String userId, String name, String phoneNumber, String email, String password, String address,String avatar, String roleId) {
        this.userId= userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.address = address;
        this.avatar= avatar;
        this.roleId = roleId;
    }
    
    public Users(String name, String phoneNumber, String email,  String address, String avatar, String roleId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.avatar = avatar;
        this.address = address;
        this.roleId = roleId;
    }

    public Users(String name, String phoneNumber, String email, String password, String address, String avatar, String roleId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.address = address;
        this.avatar = avatar;
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRoleId() {
        return roleId;
    }
    public boolean isAdmin(){
        return "1".equals(roleId);
    }
    public boolean isMarketer(){
        return "2".equals(roleId);
    }
    public boolean isSaler(){
        return "3".equals(roleId);
    }
    public boolean isSalerManager(){
        return "5".equals(roleId);
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    

    @Override
    public String toString() {
        return "Users{" + "userId=" + userId + "userName=" + userName + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email + ", password=" + password + ", address=" + address + ", avatar=" + avatar + ", roleId=" + roleId + "statusId=" + statusId + '}';
    }
}
