package com.Calculator.calculator.calcControl;


import com.Calculator.calculator.calcServe.Operation;
import com.Calculator.calculator.calcServe.calcDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@CrossOrigin("*")
public class controllerCalc {

    @Autowired
    Operation op ;

    @PostMapping(value ="/calc")

    public String getCalculation (@RequestBody calcDTO dto) throws Exception{
        //Operation op = new Operation(dto) ;

        System.out.println("arrive");
        op.setFirst(dto.getFirstOperand());
        op.setSecond(dto.getSecondOperand());
        op.setOperator(dto.getOperator()) ;
        Double res = 0.0  ;
        try {
            res = op.evaluate();
            System.out.println(res);
        }catch (Exception e) {
          return null ;
        }
        return res.toString();
    }
}
