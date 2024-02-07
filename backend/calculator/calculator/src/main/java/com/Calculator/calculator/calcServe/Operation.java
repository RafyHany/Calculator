package com.Calculator.calculator.calcServe;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Operation {

//    private calcDTO dto ;
//    public Operation(calcDTO dto) {
//        this.dto.setFirstOperand(dto.getFirstOperand());
//        this.dto.setSecondOperand(dto.getSecondOperand());
//        this.dto.setOperator(dto.getOperator());
//    }
    private double first;
    private double second ;
    private String operator ;
    Operation(){}

    public String getOperator() {
        return operator;
    }

    public double getFirst() {
        return first;
    }

    public double getSecond() {
        return second;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setFirst(double first) {
        this.first = first;
    }

    public void setSecond(double second) {
        this.second = second;
    }

    public Double evaluate (){
        Calculations calc = new Calculations(getFirst(),getSecond()) ;
        String op = getOperator() ;
        Double res  = null;
        switch (op){
            case "+" :
                res = calc.add() ;
                break ;
            case "-":
                res = calc.sub() ;
                break ;
            case "*" :
                res = calc.mulu() ;
                break ;
            case "/" :
                res = calc.div() ;
                break ;
            case "root" :
                res = calc.root() ;
                break ;
            case "sqr" :
                res = calc.square() ;
                break ;
            case "inverse" :
                res = calc.inverse() ;
                break ;
            case "percent" :
                res = calc.percentage() ;
                break ;

        }
        return res ;
    }
}
