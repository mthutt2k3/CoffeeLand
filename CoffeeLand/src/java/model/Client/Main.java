/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Client;
import java.sql.*;
import java.util.List;
/**
 *
 * @author dell
 */
public class Main {
    public static void main(String[] args) {
        ClientDAO cd = new ClientDAO();
        
        System.out.println(cd.getOrderStatusIDByOrderID(3006));
    }
}
