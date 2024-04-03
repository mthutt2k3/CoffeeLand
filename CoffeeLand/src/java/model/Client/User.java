/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Client;

public class User {

    /*[userId] [int] IDENTITY(1,1) NOT NULL,
	[userName] [nvarchar](255) NOT NULL,
	[name] [nvarchar](255) NOT NULL,
	[phoneNumber] [nvarchar](255) NOT NULL,
	[email] [nvarchar](255) NOT NULL,
	[password] [nvarchar](255) NOT NULL,
	[address] [nvarchar](255) NOT NULL,
	[avatar] [nvarchar](255) NULL,
	[roleID] [int] NOT NULL,
	[statusID] [int] NOT NULL,*/
    public String name, phoneNumber, email, password, address, avatar;
    public int roleID;
    public int statusID;

    public User() {
    }

    public User(String name, String phoneNumber, String email, String password, String address, String avatar, int roleID, int statusID) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.address = address;
        this.avatar = avatar;
        this.roleID = roleID;
        this.statusID = statusID;
    }

    


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }


}
