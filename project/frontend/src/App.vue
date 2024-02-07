<template>
  <div>
    <div class="calcbody">
      
    <div class="screen"> <div class="inner">{{ expression }} {{ result }}</div></div>
    <br>
    <div class="calcbuttons">
    <button class="but has"  @click="appendOperand('%')" >%</button>
    <button class="but has" @click="deleteAll">CE</button>
    <button class="but has" @click="deleteAll">C</button>
    <button class="but has" @click="deleteone">DEL</button>
    <button class="but" @click="appendOperand('²')">X <sup>2</sup></button>
    <button class="but" @click="appendOperand('√')">&radic;</button>
    <button class="but"  @click="appendOperand('⁻¹')">X <sup>-1</sup></button>
    <button class="but has"  @click="appendOperand('÷')">&divide;</button>
    <button class="but"  @click="appendOperand('1')">1</button>
    <button class="but"  @click="appendOperand('2')">2</button>
    <button class="but"  @click="appendOperand('3')">3</button>
    <button class="but has" @click="appendOperand('+')">+</button>
    <button class="but"  @click="appendOperand('4')">4</button>
    <button class="but"  @click="appendOperand('5')">5</button>
    <button class="but"  @click="appendOperand('6')">6</button>
    <button class="but has"  @click="appendOperand('-')">-</button>
    <button class="but"  @click="appendOperand('7')">7</button>
    <button class="but"  @click="appendOperand('8')">8</button>
    <button class="but"  @click="appendOperand('9')">9</button>
    <button class="but has" @click="appendOperand('×')">&times;</button>
    <button class="but"  @click="appendOperand('0')">0</button>
    <button class="but"  @click="appendOperand('.')">.</button>
    <button class="but"  @click="toggel">&#177;</button>
    <button class="but eq" @click="equal">=</button>
  </div>
  </div>
</div>
  
</template>

<script>



export default {
  name: 'App',
  data(){
    return {
    expression : '' ,
    result : '' ,
    expSend : {
      firstOperand : NaN,
      operator : "",
      secondOperand : NaN,
    },
    ended : 1 ,
    unary : false ,
    index : 0 ,

  }
},
  methods : {
    appendOperand(input) {
      
        this.expression=this.expression+input ;
        if((input!=='-' || (this.expSend.operator==='' && !isNaN(this.expSend.firstOperand)))){
        switch(input){
          case "+":
            this.expSend.operator=input;
            this.ended=2;
            this.index = this.expression.length-1 ;
            break;
          case "-" :
            this.expSend.operator=input;
            this.ended=2;
            this.index = this.expression.length-1 ;
            break ;
          case "×" :
            this.expSend.operator="*";
            this.ended=2;
            this.index = this.expression.length-1 ;
            break ;
          case "÷":
            this.expSend.operator='/';
            this.ended=2;
            this.index = this.expression.length-1 ;
            break ;
          case "√":
            this.expSend.operator="root";
            this.ended=2;
            this.unary= true ;
            this.index = this.expression.length-1 ;
            break ;
          case "²":
            this.expSend.operator="sqr";
            this.ended=2;
            this.unary=true;
            this.index = this.expression.length-1 ;
            break ;
          case "⁻¹" : 
            this.expSend.operator="inverse";
            this.ended=2;
            this.unary=true;
            this.index = this.expression.length-1 ;
            break ;
          case "%" :
            this.expSend.operator="percent";
            this.ended=2;
            this.unary=true;
            this.index = this.expression.length-1 ;
            break ;
        }}
        if(this.ended===1){
          this.expSend.firstOperand = parseFloat(this.expression);
        }
        else if(this.ended===2){
          this.expSend.secondOperand = parseFloat(this.expression.slice(this.index+1,this.expression.length));
        }
        this.result = "" ;
        console.log(this.expSend);
    },
    
    deleteAll(){
      this.expression='';
      this.expSend.firstOperand= NaN;
      this.expSend.secondOperand=NaN;
      this.expSend.operator="";
      this.ended = 1 ;
      this.unary = false ;
      this.index = 0 ;
    },
    deleteone(){
      if(this.expSend.operator===''){
        this.expression = this.expression.slice(0,-1);
        this.expSend.firstOperand = parseFloat(this.expression);
      }else if ( isNaN(this.expSend.secondOperand)){
        this.expression = this.expression.slice(0,this.index);
        this.expSend.operator = '' ;
        this.ended = 1 ;
        this.unary = false ;
        this.index = 0 ;

      }else {
        this.expression = this.expression.slice(0,-1);
        this.expSend.secondOperand = parseFloat(this.expression.slice(this.index+1,this.expression.length));
      }
    },
    equal(){
      this.expression='';
      fetch('http://localhost:8081/calc' ,{
        method : 'POST',
        headers : {
        ///  Accept : 'application.json',
          'Content-Type': 'application/json'
        },
        body : JSON.stringify (this.expSend) ,
      })
      .then((response)=>{
        console.log("first stage")
        if(response.status !== 200){
          return "Math Error!!!"
        }
        return response.text();
        // console.log(response.text)
        
      })
      .then((response)=>{
        console.log(response)
        console.log("second stage") ;
        this.expression = response ;
        this.ended = 1 ;
        this.expSend.firstOperand = parseFloat(response);
        this.expSend.secondOperand = 0 ;
        this.expSend.operator = '' ;
      }).catch((error) =>{
        console.log("thisrd stage")
        this.expression = 'Math error !' ;
        console.log(error);
      });
          
    },
    toggel(){
      if(this.ended===1){
        if(this.expression[0]!=='-'){
        this.expression = '-'+this.expression.slice(0,this.expression.length+1) ;
        }else{
          this.expression = this.expression.slice(1,this.expression.length+1) ;
        }
        this.expSend.firstOperand = this.expSend.firstOperand * -1 ;
      }else{
        if(this.expression[this.index+1]!=='-'){
          this.expression = this.expression.slice(0,this.index+1) + '-' +this.expression.slice(this.index + 1 ,this.expression.length+1)  ;
        }else{
          this.expression = this.expression.slice(0,this.index+1) +this.expression.slice(this.index + 2 ,this.expression.length+1)  ;
        }
        this.expSend.secondOperand = this.expSend.secondOperand * -1 ;
      }
    }
    

  }
  

}
</script>

<style>
#app {
  width: 500px;
  height: 500px;
  background-color:#ddd ;
  border: 6px solid rgb(89, 85, 85);
  border-radius: 14px;


}
.screen{
  margin-left: 7px;
  margin-top: 10px;
  width : 480px;
  height: 100px;
  background-color:#fff ;
  font-size: 1.5em;
  display: flex ;
  text-align: center;
  justify-content: center;
  border: 3px solid rgb(69, 69, 69);
  border-radius: 12px;
  position: relative;
}
.inner{
  position: relative;
  top: 30px;
  
}
.calcbuttons{

  display: flex ;
  flex-direction: row;
  flex-wrap: wrap;
  text-align:center;
  justify-content: space-evenly;
}
.but{
  width: 120px;
  height: 50px;
  margin-bottom: 10px;
  border: 3px solid #fff ;
  border-radius: 8px;
  font-size: 1.5em;
  text-align: center;
  background-color:  #5e5555; 
  color: #ddd;
}
.has,.eq{
  background-color: orange;
  color: #5e5555;
}

</style>
