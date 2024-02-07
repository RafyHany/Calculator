package com.Calculator.calculator.calcServe;

public class Calculations {
    private double first ;
    private double second ;

    Calculations(double first , double second){
        this.first = first ;
        this.second = second ;
    }
    Calculations (){}
    public double add(){
        return this.first + this.second;
    }
    public double sub(){
        return this.first - this.second;
    }
    public double mulu(){
        return this.first * this.second;
    }
    public Double div(){
        if(this.second!=0)
            return this.first / this.second;
        return null ;
    }
    public Double root (){
        if(this.second >= 0)
            return Math.sqrt(this.second) ;
        return null ;
    }
    public Double inverse(){
        if(this.first!=0)
            return  1 /this.first ;
        return null ;
    }
    public double square(){
        return this.first * this.first;
    }
    public  double percentage(){
        return this.first / 100 ;
    }



}
