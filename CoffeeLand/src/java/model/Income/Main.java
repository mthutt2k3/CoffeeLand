/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Income;

import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Date;
import model.Orders.Orders;
import model.Users.Users;

/**
 *
 * @author dell
 */
public class Main {

    public static void main(String[] args) {
        IncomeDAO icd = new IncomeDAO();
        System.out.println(icd.getSalerIdBySalerName("Saler"));
        List<Orders> listS = icd.getOrderBySalerID(3);
        for(Orders name: listS){
            System.out.println(name);
        }
        
        Scanner sc = new Scanner(System.in);
        
        /*System.out.println("Enter day yyyy-MM-dd");
        String newS = sc.nextLine();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Chuyển đổi chuỗi thành đối tượng Date
            java.util.Date utilDate = dateFormat.parse(newS);

            // Chuyển đổi từ java.util.Date sang java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            List<String> list = icd.getTotalOrderByDay(1, sqlDate);
            double total = 0.0;
            for (String grand : list) {
                double grandN = Double.parseDouble(grand);
                total += grandN;
                System.out.println(grand);
            }
            System.out.println(total);

        } catch (Exception e) {
            System.out.println("e");
        }
        System.out.println("Enter month yyyy-MM");
        String month = sc.nextLine();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
            java.util.Date utilDate = dateFormat.parse(month);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            List<String> list = icd.getTotalOrderByMonth(1, sqlDate);
            double total = 0.0;
            for (String grand : list) {
                double grandN = Double.parseDouble(grand);
                total += grandN;
                System.out.println(grand);
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Enter year yyyy:");
        String year = sc.nextLine();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            java.util.Date utilDate = dateFormat.parse(year);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            List<String> list = icd.getTotalOrderByYear(1, sqlDate);
            double total = 0.0;
            for (String grand : list) {
                double grandN = Double.parseDouble(grand);
                total += grandN;
                System.out.println(grand);
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }
}
