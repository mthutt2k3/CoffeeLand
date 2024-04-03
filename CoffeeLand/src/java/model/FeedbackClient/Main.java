/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.FeedbackClient;

import java.util.List;

/**
 *
 * @author dell
 */
public class Main {
    public static void main(String[] args) {
        FeedbackDAO fbd = new FeedbackDAO();
        String image = fbd.getImageByUserId(5);
        System.out.println(image);
    }
}
