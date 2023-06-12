package com.cob.feedback.controller;


import com.cob.feedback.formula.AverageFormula;
import com.cob.feedback.formula.HappyIndexFormula;
import com.cob.feedback.formula.NPSFormula;

public class Main {
    public static void main(String[] args) {
        System.out.println("init");
        System.out.println(HappyIndexFormula.calculate(100, 126, 60, 16, 17));
        System.out.println(NPSFormula.calculate(219, 126, 16, 17));
        System.out.println(AverageFormula.calculate(51, 23, 12, 2));
    }

}
