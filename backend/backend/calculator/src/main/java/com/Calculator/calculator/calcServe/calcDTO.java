package com.Calculator.calculator.calcServe;

public class calcDTO {
    private double firstOperand ;
    private double secondOperand ;
    private String operator ;

    public double getFirstOperand() {
        return firstOperand;
    }

    public double getSecondOperand() {
        return secondOperand;
    }

    public String getOperator() {
        return operator;
    }

    public void setFirstOperand(double firstOperand) {
        this.firstOperand = firstOperand;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setSecondOperand(double secondOperand) {
        this.secondOperand = secondOperand;
    }
}
