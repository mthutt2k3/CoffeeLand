/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Slider;

import java.util.List;

/**
 *
 * @author dell
 */
public class Main {
    public static void main(String[] args) {
        SliderDAO sd = new SliderDAO();
        List<Integer> listSlider = sd.getOrderOfSlide();
        for(Integer slider: listSlider){
            System.out.println(slider);
        }
    }
}
