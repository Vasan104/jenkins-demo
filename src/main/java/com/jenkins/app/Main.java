package com.jenkins.app;

public class Main {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("  Jenkins Java Application Build Demo");
        System.out.println("=========================================");

        Calculator calc = new Calculator();

        System.out.println("Addition      : 10 + 5 = " + calc.add(10, 5));
        System.out.println("Subtraction   : 10 - 5 = " + calc.subtract(10, 5));
        System.out.println("Multiplication: 10 * 5 = " + calc.multiply(10, 5));
        System.out.println("Division      : 10 / 5 = " + calc.divide(10, 5));
        System.out.println("Is 10 Even?   : " + calc.isEven(10));
        System.out.println("Is 7 Even?    : " + calc.isEven(7));

        System.out.println("=========================================");
        System.out.println("  Application ran successfully!");
        System.out.println("=========================================");
    }
}
